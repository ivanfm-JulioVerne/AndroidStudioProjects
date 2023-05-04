package com.example.minichef_v1.pantanllas.nuevo

import android.app.ActionBar.LayoutParams
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.dao.DAOPublicacion
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.databinding.FragmentNuevoBinding

class NuevoFragment : Fragment() {

    private var _binding: FragmentNuevoBinding? = null
    private var idxIngrediente:Int=11
    private var idxPasos:Int=15

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val nuevoViewModel =
            ViewModelProvider(this).get(NuevoViewModel::class.java)

        _binding = FragmentNuevoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val categoria1:Spinner=binding.categoria1
        categoria1.adapter= ArrayAdapter.createFromResource(this.requireContext(),R.array.categorias,android.R.layout.simple_spinner_dropdown_item)

        val categoria2:Spinner=binding.categoria2
        categoria2.adapter= ArrayAdapter.createFromResource(this.requireContext(),R.array.categorias,android.R.layout.simple_spinner_dropdown_item)

        val btnNuevoIngrediente: Button =binding.btnAnadirIngrediente
        btnNuevoIngrediente.setOnClickListener {
            var editTextIngrediente:EditText=EditText(this.context)
            editTextIngrediente.layoutParams=LayoutParams(965,LayoutParams.WRAP_CONTENT)
            editTextIngrediente.textSize=24f
            try {
                binding.linearLayoutPadre.addView(editTextIngrediente,idxIngrediente)
                idxIngrediente++
                idxPasos++
            }catch (e:java.lang.Exception){
                Log.d(":::Error",e.message ?: "")
            }
        }

        val btnNuevoPasos: Button=binding.btnAnadirpaso
        btnNuevoPasos.setOnClickListener {
            var editTextPaso:EditText= EditText(this.context)
            editTextPaso.layoutParams=LayoutParams(965,LayoutParams.WRAP_CONTENT)
            editTextPaso.textSize=24f
            try {
                binding.linearLayoutPadre.addView(editTextPaso,idxPasos)
                idxPasos++
            }catch (e:java.lang.Exception){
                Log.d(":::Error",e.message ?: "")
            }
        }

        val btnPublicar: Button=binding.btnPublicar
        btnPublicar.setOnClickListener {
            var publicacion:Publicacion=Publicacion()

            publicacion.titulo=binding.etTitulo.text.toString()
            publicacion.descripcion=binding.etDescripcion.text.toString()
            val categoria1:String=binding.categoria1.selectedItem.toString()
            val categoria2:String=binding.categoria2.selectedItem.toString()
            val categorias=resources.getStringArray(R.array.categorias)
            val idxCategoria1=categorias.indexOf(categoria1)
            val idxCategoria2=categorias.indexOf(categoria2)

            if (idxCategoria1==idxCategoria2){
                showAlertCategoria()
            }else {
                val categoriasElegidas=ArrayList<String>()
                categoriasElegidas.add(idxCategoria1.toString())
                categoriasElegidas.add(idxCategoria2.toString())
                publicacion.id_categoria=categoriasElegidas

                val ingredientes=ArrayList<String>()
                val idxEtIngrediente: Int =binding.linearLayoutPadre.indexOfChild(binding.etIngrediente)
                val idxBtnIngrediente: Int =binding.linearLayoutPadre.indexOfChild(binding.btnAnadirIngrediente)
                var hayIngrediente:Boolean=false
                for (i in idxEtIngrediente..(idxBtnIngrediente - 1)) {
                    val etIngrediente: EditText = binding.linearLayoutPadre.get(i) as EditText
                    if(etIngrediente.text.toString()!=null){
                        hayIngrediente=true
                        ingredientes.add(etIngrediente.text.toString())
                    }
                }
                if (!hayIngrediente){
                    showAlertIngrediente()
                }
                publicacion.ingredientes=ingredientes

                val pasos=ArrayList<String>()
                val idxEtPaso: Int = binding.linearLayoutPadre.indexOfChild(binding.etPaso)
                val idxBtnPaso: Int = binding.linearLayoutPadre.indexOfChild(binding.btnAnadirpaso)
                var hayPasos:Boolean=false
                for (i in idxEtPaso..(idxBtnPaso - 1)) {
                    val etPaso: EditText = binding.linearLayoutPadre.get(i) as EditText
                    if(etPaso.text.toString()!=null){
                        hayPasos=true
                        pasos.add(etPaso.text.toString())
                    }
                }
                if (!hayPasos){
                    showAlertPaso()
                }
                publicacion.pasos=pasos

                publicacion.id_usuario=(activity as MainActivity).usuario.id_usuario

                if (hayPasos && hayIngrediente){
                    DAOPublicacion().crearPublicacion(publicacion,root)
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    public fun showAlertCategoria(){
        AlertDialog.Builder(activity)
            .setTitle("Error")
            .setMessage("La receta tiene que tener sus 2 categorías diferentes")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }

    public fun showAlertIngrediente(){
        AlertDialog.Builder(activity)
            .setTitle("Error")
            .setMessage("La receta tiene que tener mínimo un ingrediente")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }
    public fun showAlertPaso(){
        AlertDialog.Builder(activity)
            .setTitle("Error")
            .setMessage("La receta tiene que tener mínimo un paso")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }

    public fun showAlertPublicacionCreada(){
        AlertDialog.Builder(activity)
            .setMessage("La publicacion se ha creado correctamente")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }
}