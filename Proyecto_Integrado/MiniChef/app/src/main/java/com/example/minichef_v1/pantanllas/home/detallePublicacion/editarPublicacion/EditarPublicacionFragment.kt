package com.example.minichef_v1.pantanllas.home.detallePublicacion.editarPublicacion

import android.app.ActionBar
import android.app.AlertDialog
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.dao.publicacion.DAOPublicacion
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.databinding.FragmentEditarPublicacionBinding
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class EditarPublicacionFragment : Fragment() {

    var _binding:FragmentEditarPublicacionBinding?=null
    private var idxIngrediente:Int=12
    private var idxPasos:Int=16
    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentEditarPublicacionBinding.inflate(inflater,container,false)
        val root=binding.root

        val bundle=requireArguments()
        val publicacion=Publicacion(
            bundle.getString("idPublicacion"),
            bundle.getString("titulo"),
            bundle.getString("descripcion"),
            bundle.getStringArrayList("ingredientes"),
            bundle.getStringArrayList("pasos"),
            bundle.getString("imagen"),
            bundle.getLong("num_likes"),
            bundle.getBoolean("baneado"),
            bundle.getString("id_usuario"),
            bundle.getStringArrayList("id_categoria"),
        )

        binding.etTituloEditar.setText(publicacion.titulo)
        binding.etDescripcionEditar.setText(publicacion.descripcion)

        val idxCategoria1Publicacion=(activity as MainActivity).categoriasId.indexOf(publicacion.id_categoria!![0])
        val idxCategoria2Publicacion=(activity as MainActivity).categoriasId.indexOf(publicacion.id_categoria!![1])

        binding.categoria1Editar.adapter=
            ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,(activity as MainActivity).categoriasTexto)
        binding.categoria1Editar.setSelection(idxCategoria1Publicacion)

        binding.categoriaEditar2.adapter=
            ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,(activity as MainActivity).categoriasTexto)
        binding.categoriaEditar2.setSelection(idxCategoria2Publicacion)

        binding.etIngredienteEditar.setText(publicacion.ingredientes!![0])
        binding.etPasoEditar.setText(publicacion.pasos!![0])

        Glide.with(requireContext()).load(publicacion.imagen!!).into(binding.ivFotoRecetaEditar)

        if (publicacion.ingredientes!!.size>1){
            for (i in 1 until publicacion.ingredientes!!.size){
                val linearLayout= LinearLayout(this.context)
                linearLayout.layoutParams=
                    ActionBar.LayoutParams(965, ActionBar.LayoutParams.WRAP_CONTENT)
                linearLayout.orientation= LinearLayout.HORIZONTAL

                val boton= Button(this.context)
                boton.text="—"

                boton.layoutParams= ActionBar.LayoutParams(150, ActionBar.LayoutParams.WRAP_CONTENT)
                boton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#000000"))
                boton.setTextColor(Color.parseColor("#FCF2DB"))
                boton.setOnClickListener {
                    binding.linearLayoutPadreEditar.removeView(linearLayout)
                    idxIngrediente--
                    idxPasos--
                }
                val editTextIngrediente= EditText(this.context)
                editTextIngrediente.setText(publicacion.ingredientes!![i])
                val layoutParams= LinearLayout.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT)
                layoutParams.weight=1f
                editTextIngrediente.layoutParams=layoutParams
                editTextIngrediente.textSize=24f
                try {
                    linearLayout.addView(editTextIngrediente)
                    linearLayout.addView(boton)
                    binding.linearLayoutPadreEditar.addView(linearLayout,idxIngrediente)
                    idxIngrediente++
                    idxPasos++
                }catch (e:java.lang.Exception){
                    Log.d(":::Error",e.message ?: "")
                }
            }
        }

        if (publicacion.pasos!!.size>1){
            for (i in 1 until publicacion.pasos!!.size){
                val linearLayout= LinearLayout(this.context)
                linearLayout.layoutParams=
                    ActionBar.LayoutParams(965, ActionBar.LayoutParams.WRAP_CONTENT)
                linearLayout.orientation= LinearLayout.HORIZONTAL

                val boton= Button(this.context)
                boton.text="—"

                boton.layoutParams= ActionBar.LayoutParams(150, ActionBar.LayoutParams.WRAP_CONTENT)
                boton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#000000"))
                boton.setTextColor(Color.parseColor("#FCF2DB"))
                boton.setOnClickListener {
                    binding.linearLayoutPadreEditar.removeView(linearLayout)
                    idxPasos--
                }
                val editTextPaso= EditText(this.context)
                editTextPaso.setText(publicacion.pasos!![i])
                val layoutParams= LinearLayout.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT)
                layoutParams.weight=1f
                editTextPaso.layoutParams=layoutParams
                editTextPaso.textSize=24f
                try {
                    linearLayout.addView(editTextPaso)
                    linearLayout.addView(boton)
                    binding.linearLayoutPadreEditar.addView(linearLayout,idxPasos)
                    idxPasos++
                }catch (e:java.lang.Exception){
                    Log.d(":::Error",e.message ?: "")
                }
            }
        }

        val btnNuevoIngrediente: Button =binding.btnAnadirIngredienteEditar
        btnNuevoIngrediente.setOnClickListener {
            val linearLayout= LinearLayout(this.context)
            linearLayout.layoutParams=
                ActionBar.LayoutParams(965, ActionBar.LayoutParams.WRAP_CONTENT)
            linearLayout.orientation= LinearLayout.HORIZONTAL

            val boton= Button(this.context)
            boton.text="—"

            boton.layoutParams= ActionBar.LayoutParams(150, ActionBar.LayoutParams.WRAP_CONTENT)
            boton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#000000"))
            boton.setTextColor(Color.parseColor("#FCF2DB"))
            boton.setOnClickListener {
                binding.linearLayoutPadreEditar.removeView(linearLayout)
                idxIngrediente--
                idxPasos--
            }
            val editTextIngrediente= EditText(this.context)
            val layoutParams= LinearLayout.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT)
            layoutParams.weight=1f
            editTextIngrediente.layoutParams=layoutParams
            editTextIngrediente.textSize=24f
            try {
                linearLayout.addView(editTextIngrediente)
                linearLayout.addView(boton)
                binding.linearLayoutPadreEditar.addView(linearLayout,idxIngrediente)
                idxIngrediente++
                idxPasos++
            }catch (e:java.lang.Exception){
                Log.d(":::Error",e.message ?: "")
            }
        }

        val btnNuevoPasos: Button =binding.btnAnadirpasoEditar
        btnNuevoPasos.setOnClickListener {
            val linearLayout= LinearLayout(this.context)
            linearLayout.layoutParams=
                ActionBar.LayoutParams(965, ActionBar.LayoutParams.WRAP_CONTENT)
            linearLayout.orientation= LinearLayout.HORIZONTAL

            val boton= Button(this.context)
            boton.text="—"

            boton.layoutParams= ActionBar.LayoutParams(150, ActionBar.LayoutParams.WRAP_CONTENT)
            boton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#000000"))
            boton.setTextColor(Color.parseColor("#FCF2DB"))
            boton.setOnClickListener {
                binding.linearLayoutPadreEditar.removeView(linearLayout)
                idxPasos--
            }
            val editTextPaso= EditText(this.context)
            val layoutParams= LinearLayout.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT)
            layoutParams.weight=1f
            editTextPaso.layoutParams=layoutParams
            editTextPaso.textSize=24f
            try {
                linearLayout.addView(editTextPaso)
                linearLayout.addView(boton)
                binding.linearLayoutPadreEditar.addView(linearLayout,idxPasos)
                idxPasos++
            }catch (e:java.lang.Exception){
                Log.d(":::Error",e.message ?: "")
            }
        }

        val btnPublicar: Button = binding.btnPublicarEditar
        btnPublicar.setOnClickListener {

            val publicacionNueva = Publicacion()

            publicacionNueva.titulo=binding.etTituloEditar.text.toString()
            publicacionNueva.descripcion=binding.etDescripcionEditar.text.toString()
            publicacionNueva.imagen=publicacion.imagen
            val categoriaPrimero:String=binding.categoria1Editar.selectedItem.toString()
            val categoriaSegundo:String=binding.categoriaEditar2.selectedItem.toString()
            //val categorias=resources.getStringArray(R.array.categorias)
            //val idxCategoria1=categorias.indexOf(categoria1)
            //val idxCategoria2=categorias.indexOf(categoria2)

            val idxCategoria1=(activity as MainActivity).categoriasTexto.indexOf(categoriaPrimero)
            val idxCategoria2=(activity as MainActivity).categoriasTexto.indexOf(categoriaSegundo)
            if (publicacionNueva.titulo!=null && publicacionNueva.titulo!=""){
                if (idxCategoria1==idxCategoria2){
                    showAlertCategoria()
                }else {
                    val categoriasElegidas = ArrayList<String>()
                    //categoriasElegidas.add(idxCategoria1.toString())
                    //categoriasElegidas.add(idxCategoria2.toString())

                    categoriasElegidas.add((activity as MainActivity).categorias[idxCategoria1].idCategoria)
                    categoriasElegidas.add((activity as MainActivity).categorias[idxCategoria2].idCategoria)
                    publicacionNueva.id_categoria = categoriasElegidas

                    val ingredientes = ArrayList<String>()
                    val idxEtIngrediente: Int =
                        binding.linearLayoutPadreEditar.indexOfChild(binding.etIngredienteEditar)
                    val idxBtnIngrediente: Int =
                        binding.linearLayoutPadreEditar.indexOfChild(binding.btnAnadirIngredienteEditar)
                    var hayIngrediente = false
                    for (i in idxEtIngrediente until idxBtnIngrediente) {
                        val etIngrediente: EditText = if (i!=idxEtIngrediente){
                            val linearLayout: LinearLayout = binding.linearLayoutPadreEditar[i] as LinearLayout
                            linearLayout[0] as EditText
                        }else{
                            binding.linearLayoutPadreEditar[i] as EditText
                        }

                        if (etIngrediente.text.toString() != "") {
                            hayIngrediente = true
                            ingredientes.add(etIngrediente.text.toString())
                        }
                    }
                    if (!hayIngrediente) {
                        showAlertIngrediente()
                    }
                    publicacionNueva.ingredientes = ingredientes

                    val pasos = ArrayList<String>()
                    val idxEtPaso: Int = binding.linearLayoutPadreEditar.indexOfChild(binding.etPasoEditar)
                    val idxBtnPaso: Int = binding.linearLayoutPadreEditar.indexOfChild(binding.btnAnadirpasoEditar)
                    var hayPasos = false
                    for (i in idxEtPaso until idxBtnPaso) {
                        val etPaso: EditText = if (i!=idxEtPaso){
                            val linearLayout: LinearLayout = binding.linearLayoutPadreEditar[i] as LinearLayout
                            linearLayout[0] as EditText
                        }else{
                            binding.linearLayoutPadreEditar[i] as EditText
                        }
                        if (etPaso.text.toString() != "") {
                            hayPasos = true
                            pasos.add(etPaso.text.toString())
                        }
                    }
                    if (!hayPasos) {
                        showAlertPaso()
                    }
                    publicacionNueva.pasos = pasos
                    publicacionNueva.id_usuario = (activity as MainActivity).usuario.id_usuario
                    publicacionNueva.id_publicacion=publicacion.id_publicacion
                    publicacionNueva.num_likes=publicacion.num_likes
                    publicacionNueva.baneado=publicacion.baneado

                    if (hayPasos && hayIngrediente) {
                        DAOPublicacion().editarPublicacion(publicacionNueva)
                        (activity as MainActivity).publicacionSeleccionada=publicacionNueva
                        findNavController().navigate(R.id.action_editarPublicacionFragment_to_detallePublicacionFragment)
                    }
                }
            }else{
                showAlertTitulo()
            }
        }

        // Inflate the layout for this fragment
        return root
    }

    private fun showAlertCategoria(){
        AlertDialog.Builder(activity)
            .setTitle("Error")
            .setMessage("La receta tiene que tener sus 2 categorías diferentes")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }

    private fun showAlertIngrediente(){
        AlertDialog.Builder(activity)
            .setTitle("Error")
            .setMessage("La receta tiene que tener mínimo un ingrediente")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }
    private fun showAlertPaso(){
        AlertDialog.Builder(activity)
            .setTitle("Error")
            .setMessage("La receta tiene que tener mínimo un paso")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }

    private fun showAlertTitulo(){
        AlertDialog.Builder(activity)
            .setMessage("La receta debe tener un titulo")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }
}