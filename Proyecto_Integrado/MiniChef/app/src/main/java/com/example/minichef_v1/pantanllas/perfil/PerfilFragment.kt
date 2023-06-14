package com.example.minichef_v1.pantanllas.perfil

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.databinding.FragmentPerfilBinding
import com.example.minichef_v1.pantanllas.perfil.rvPublicaciones.PublicacionesAdapter
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class PerfilFragment : Fragment() {

    private var _binding: FragmentPerfilBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val perfilViewModel:PerfilViewModel by viewModels { PerfilViewModelFactory((activity as MainActivity).usuario) }

    override fun onResume() {
        super.onResume()
        perfilViewModel.getPublicacionesPorUsuarios()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val idUsaurio=FirebaseAuth.getInstance().currentUser?.uid
        Log.d(":::Perfil",idUsaurio ?: "")
        val usuario=(activity as MainActivity).usuario

        binding.btnEditar.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_perfil_to_editarFragment)
        }

        binding.btnLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            (activity as MainActivity).goToAuthActivity()
        }

        binding.btnBorrarUsuario.setOnClickListener{
            val usuarioAuth=FirebaseAuth.getInstance().currentUser

            if (usuarioAuth != null){
                if (usuarioAuth.providerData.any{it.providerId==GoogleAuthProvider.PROVIDER_ID}){
                    //Log.d(":::Borrado","Es un usuario de google")
                    alertConfirmacion(usuarioAuth)
                }else if (usuarioAuth.providerData.any{it.providerId==EmailAuthProvider.PROVIDER_ID}){
                    //Log.d(":::Borrado","Es un usuario de email")
                    alertEliminarUsuarioPorEmail(usuarioAuth)
                }
            }
        }

        binding.tvNickname.text=usuario.nickname
        binding.tvNombre.text=usuario.nombre

        val seguidores=(resources.getText(R.string.seguirdores) as String)+"\n" + usuario.num_seguidores.toString()
        val publicaciones=resources.getString(R.string.publicaciones)+"\n"+usuario.num_publicacion.toString()
        val siguiendo=resources.getString(R.string.siguiendo)+"\n"+usuario.num_siguiendo.toString()

        //binding.tvSeguidores.text="Seguidores\n" + usuario.num_seguidores.toString()
        //binding.tvPublicacion.text="Publiciaciones\n" + usuario.num_publicacion.toString()
        //binding.tvSiguiendo.text="Siguiendo\n" + usuario.num_siguiendo.toString()

        binding.tvSeguidores.text=seguidores
        binding.tvPublicacion.text=publicaciones
        binding.tvSiguiendo.text=siguiendo

        binding.tvBiografia.text=usuario.biografia

        perfilViewModel.lista.observe(viewLifecycleOwner) {
            val publicacionesActualizadas=resources.getString(R.string.publicaciones)+"\n"+it.size.toString()
            binding.tvPublicacion.text=publicacionesActualizadas
            refreshRecyclerView(it, root)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun refreshRecyclerView(publicaciones:List<Publicacion>, view: View){
        val recyclerView=view.findViewById<RecyclerView>(R.id.rv_publicacionesPerfilPropio)
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        recyclerView.adapter= PublicacionesAdapter(publicaciones)
    }

    private fun alertEliminarUsuarioPorEmail(usuario:FirebaseUser){
        val editText = EditText(activity)
        editText.transformationMethod = PasswordTransformationMethod.getInstance()

        // Asegúrate de agregar el EditText al TextInputLayout o al AlertDialog según sea necesario
        val textInputLayout = TextInputLayout(requireContext())
        textInputLayout.addView(editText)

        var texto=""

        AlertDialog.Builder(activity)
            .setMessage("Vuelve a poner tu contraseña")
            .setView(textInputLayout)
            .setPositiveButton("OK") { _, _ ->
                texto=editText.text.toString()
                usuario.reauthenticate(EmailAuthProvider.getCredential(usuario.email!!, texto)).addOnCompleteListener {
                    if (it.isSuccessful){
                        Log.d(":::Es exitoso","")
                        usuario.delete()
                        perfilViewModel.eliminarUsuario()
                        (activity as MainActivity).goToAuthActivity()
                    }else{
                        alertContrasesaIncorrecta()
                    }
                }
            }
            .create()
            .show()
    }

    private fun alertContrasesaIncorrecta(){
        AlertDialog.Builder(activity)
            .setTitle("Error")
            .setMessage("Contraseña incorrecta")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }

    private fun alertConfirmacion(usuarioAuth:FirebaseUser){
        AlertDialog.Builder(activity)
            .setTitle("Error")
            .setMessage("¿Estas seguro que quieres borrar tu usuario?")
            .setPositiveButton("Sí"){ _,_->
                usuarioAuth.delete()
                perfilViewModel.eliminarUsuario()
                (activity as MainActivity).goToAuthActivity()
            }
            .setNegativeButton("No",null)
            .create()
            .show()
    }
}