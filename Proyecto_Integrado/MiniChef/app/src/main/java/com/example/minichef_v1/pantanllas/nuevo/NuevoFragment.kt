package com.example.minichef_v1.pantanllas.nuevo

import android.app.ActionBar.LayoutParams
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.minichef_v1.BuildConfig
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.dao.DAOPublicacion
import com.example.minichef_v1.bd.dao.DAOUsuario
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.databinding.FragmentNuevoBinding
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException

class NuevoFragment : Fragment() {

    private var _binding: FragmentNuevoBinding? = null
    private var idxIngrediente:Int=13
    private var idxPasos:Int=17
    private var imagenReceta:Bitmap?=null
    private lateinit var currentPhotoPath:String
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

        //region $Cargar categorias
        val categoria1:Spinner=binding.categoria1
        categoria1.adapter= ArrayAdapter.createFromResource(this.requireContext(),R.array.categorias,android.R.layout.simple_spinner_dropdown_item)

        val categoria2:Spinner=binding.categoria2
        categoria2.adapter= ArrayAdapter.createFromResource(this.requireContext(),R.array.categorias,android.R.layout.simple_spinner_dropdown_item)
        //endregion

        //region $Botones para anadir ingredientes y pasos
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
        //endregion

        //region $Boton para publicar
        val btnPublicar: Button=binding.btnPublicar
        btnPublicar.setOnClickListener {
            //binding.progressBar.visibility=View.VISIBLE

            var publicacion:Publicacion=Publicacion()

            publicacion.titulo=binding.etTitulo.text.toString()
            publicacion.descripcion=binding.etDescripcion.text.toString()
            publicacion.imagen=""
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
                    if(etIngrediente.text.toString()!=""){
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
                    if(etPaso.text.toString()!=""){
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
                    if (imagenReceta!=null){
                        val baos=ByteArrayOutputStream()
                        imagenReceta!!.compress(Bitmap.CompressFormat.PNG,100,baos)
                        val data =baos.toByteArray()

                        val reference=
                            FirebaseStorage
                                .getInstance()
                                .reference
                                .child("publicaciones")
                                .child((
                                        activity as MainActivity).usuario.id_usuario+
                                        System.currentTimeMillis())
                        reference.putBytes(data).addOnCompleteListener{
                            reference.downloadUrl.addOnSuccessListener { uri ->
                                publicacion.imagen=uri.toString()
                                DAOPublicacion().crearPublicacion(publicacion,root)
                                Log.d(":::Publicacion con imagen",uri.toString())
                                binding.progressBar.visibility=View.GONE
                                limpiarFormulario()
                                DAOUsuario().anadeNuevaPublicacion((activity as MainActivity).usuario.id_usuario)
                            }
                        }
                    }
                    else{
                        DAOPublicacion().crearPublicacion(publicacion,root)
                        Log.d(":::Publicacion sin imagen","")
                        binding.progressBar.visibility=View.GONE
                        limpiarFormulario()
                        DAOUsuario().anadeNuevaPublicacion((activity as MainActivity).usuario.id_usuario)
                    }
                }

            }
        }
        //endregion

        //region $Botones para la imagen
        val btncamara:Button=binding.btnCamara
        btncamara.setOnClickListener {
            val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra("android.intent.extra.PICTURE_QUALITY", 100)
            try {
                val photoFile = createImageFile();
                val photoURI:Uri = FileProvider.getUriForFile(this.requireContext(),
                BuildConfig.APPLICATION_ID+".provider",
                photoFile);
                // Asigna la URI como salida del intent de la cámara
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startForResult.launch(intent)
            } catch (ex: IOException) {
                // Error al crear el archivo
                ex.printStackTrace();
            }
            // Continúa solo si el archivo fue creado con éxito
                // Obtiene la URI del archivo creado


        }

        val btnGaleria:Button=binding.btnGaleria
        btnGaleria.setOnClickListener {
            abrirGaleria()
        }
        //endregion

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //region $Gestión de imagenes
    private fun abrirGaleria() {
        val intent=Intent()
        intent.type="image/*"
        intent.action=Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,100)
    }

    //Result de la galería
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==100 && resultCode==RESULT_OK && data!=null){
            val imageUrl=data!!.data
            imagenReceta=MediaStore.Images.Media.getBitmap(requireContext().contentResolver,imageUrl)
            binding.ivFotoReceta.setImageURI(imageUrl)
        }
    }


    //Result de la camara
    private val startForResult=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result: ActivityResult ->
        if (result.resultCode== Activity.RESULT_OK){
            try {
                val imageFile = File(currentPhotoPath)
                if (imageFile.exists()) {
                    imagenReceta = BitmapFactory.decodeFile(imageFile.absolutePath)
                    this.binding.ivFotoReceta.setImageBitmap(imagenReceta)
                } else {
                    Log.e(":::Error", "El archivo de imagen no existe.")
                }
            }catch (e:Exception){

            }
        }
    }

    private fun createImageFile(): File {
        val imageFileName=(activity as MainActivity).usuario.id_usuario+
                System.currentTimeMillis()
        val storageDir: File? = this.requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile=File.createTempFile(imageFileName,".jpg",storageDir)
        imageFile.deleteOnExit()
        currentPhotoPath = imageFile.absolutePath
        return imageFile
    }
    //endregion

    //region $Alerts
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
    //endregion

    public fun limpiarFormulario(){
        binding.etTitulo.text=Editable.Factory.getInstance().newEditable("")
        binding.etDescripcion.text=Editable.Factory.getInstance().newEditable("")

        val idxEtIngrediente: Int =binding.linearLayoutPadre.indexOfChild(binding.etIngrediente)
        val idxBtnIngrediente: Int =binding.linearLayoutPadre.indexOfChild(binding.btnAnadirIngrediente)
        var arreglo:Int=0
        for (i in idxEtIngrediente+1..(idxBtnIngrediente - 1)) {
            val etIngrediente: EditText = binding.linearLayoutPadre.get(i - 0) as EditText
            binding.linearLayoutPadre.removeView(etIngrediente)
            arreglo++
        }
        binding.etIngrediente.text=Editable.Factory.getInstance().newEditable("")

        arreglo=0
        val idxEtPaso: Int = binding.linearLayoutPadre.indexOfChild(binding.etPaso)
        val idxBtnPaso: Int = binding.linearLayoutPadre.indexOfChild(binding.btnAnadirpaso)
        var hayPasos:Boolean=false
        for (i in idxEtPaso+1..(idxBtnPaso - 1)) {
            val etPaso: EditText = binding.linearLayoutPadre.get(i-arreglo) as EditText
            binding.linearLayoutPadre.removeView(etPaso)
            arreglo++
        }
        binding.etPaso.text=Editable.Factory.getInstance().newEditable("")

        binding.categoria1.setSelection(0)
        binding.categoria2.setSelection(0)

        binding.ivFotoReceta.setImageResource(R.mipmap.ic_launcher)
        imagenReceta=null
    }

}