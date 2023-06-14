package com.example.minichef_v1.pantanllas.nuevo

import android.app.ActionBar.LayoutParams
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.ImageDecoder
import android.net.Uri
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
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.view.get
import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
import com.example.minichef_v1.BuildConfig
import com.example.minichef_v1.MainActivity
import com.example.minichef_v1.R
import com.example.minichef_v1.bd.dao.publicacion.DAOPublicacion
import com.example.minichef_v1.bd.dao.usuario.DAOUsuario
import com.example.minichef_v1.bd.modelo.Publicacion
import com.example.minichef_v1.databinding.FragmentNuevoBinding
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.*
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
        //val nuevoViewModel =
        //    ViewModelProvider(this).get(NuevoViewModel::class.java)

        _binding = FragmentNuevoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //region $Cargar categorias
        val categoria1:Spinner=binding.categoria1
        //categoria1.adapter= ArrayAdapter.createFromResource(this.requireContext(),R.array.categorias,android.R.layout.simple_spinner_dropdown_item)
        categoria1.adapter=ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,(activity as MainActivity).categoriasTexto)

        val categoria2:Spinner=binding.categoria2
        //categoria2.adapter= ArrayAdapter.createFromResource(this.requireContext(),R.array.categorias,android.R.layout.simple_spinner_dropdown_item)
        categoria2.adapter=ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,(activity as MainActivity).categoriasTexto)
        //endregion

        //region $Botones para anadir ingredientes y pasos
        val btnNuevoIngrediente: Button =binding.btnAnadirIngrediente
        btnNuevoIngrediente.setOnClickListener {
            val linearLayout= LinearLayout(this.context)
            linearLayout.layoutParams=LayoutParams(965,LayoutParams.WRAP_CONTENT)
            linearLayout.orientation=LinearLayout.HORIZONTAL

            val boton=Button(this.context)
            boton.text="—"

            boton.layoutParams=LayoutParams(150,LayoutParams.WRAP_CONTENT)
            boton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#000000"))
            boton.setTextColor(Color.parseColor("#FCF2DB"))
            boton.setOnClickListener {
                binding.linearLayoutPadre.removeView(linearLayout)
                idxIngrediente--
                idxPasos--
            }
            val editTextIngrediente=EditText(this.context)
            val layoutParams=LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
            layoutParams.weight=1f
            editTextIngrediente.layoutParams=layoutParams
            editTextIngrediente.textSize=24f
            try {
                linearLayout.addView(editTextIngrediente)
                linearLayout.addView(boton)
                binding.linearLayoutPadre.addView(linearLayout,idxIngrediente)
                idxIngrediente++
                idxPasos++
            }catch (e:java.lang.Exception){
                Log.d(":::Error",e.message ?: "")
            }
        }

        val btnNuevoPasos: Button=binding.btnAnadirpaso
        btnNuevoPasos.setOnClickListener {
            val linearLayout= LinearLayout(this.context)
            linearLayout.layoutParams=LayoutParams(965,LayoutParams.WRAP_CONTENT)
            linearLayout.orientation=LinearLayout.HORIZONTAL

            val boton=Button(this.context)
            boton.text="—"

            boton.layoutParams=LayoutParams(150,LayoutParams.WRAP_CONTENT)
            boton.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#000000"))
            boton.setTextColor(Color.parseColor("#FCF2DB"))
            boton.setOnClickListener {
                binding.linearLayoutPadre.removeView(linearLayout)
                idxPasos--
            }
            val editTextPaso= EditText(this.context)
            val layoutParams=LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
            layoutParams.weight=1f
            editTextPaso.layoutParams=layoutParams
            editTextPaso.textSize=24f
            try {
                linearLayout.addView(editTextPaso)
                linearLayout.addView(boton)
                binding.linearLayoutPadre.addView(linearLayout,idxPasos)
                idxPasos++
            }catch (e:java.lang.Exception){
                Log.d(":::Error",e.message ?: "")
            }
        }
        //endregion

        //region $Boton para publicar
        val btnPublicar: Button = binding.btnPublicar
        btnPublicar.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            desHabilitarFormulario()

            val publicacion = Publicacion()

            publicacion.titulo=binding.etTitulo.text.toString()
            publicacion.descripcion=binding.etDescripcion.text.toString()
            publicacion.imagen=""
            val categoriaPrimero:String=binding.categoria1.selectedItem.toString()
            val categoriaSegundo:String=binding.categoria2.selectedItem.toString()
            //val categorias=resources.getStringArray(R.array.categorias)
            //val idxCategoria1=categorias.indexOf(categoria1)
            //val idxCategoria2=categorias.indexOf(categoria2)

            val idxCategoria1=(activity as MainActivity).categoriasTexto.indexOf(categoriaPrimero)
            val idxCategoria2=(activity as MainActivity).categoriasTexto.indexOf(categoriaSegundo)
            if (publicacion.titulo!=null && publicacion.titulo!=""){
                if (idxCategoria1==idxCategoria2){
                    showAlertCategoria()
                    habilitarFormulario()
                }else {
                    val categoriasElegidas = ArrayList<String>()
                    //categoriasElegidas.add(idxCategoria1.toString())
                    //categoriasElegidas.add(idxCategoria2.toString())

                    categoriasElegidas.add((activity as MainActivity).categorias[idxCategoria1].idCategoria)
                    categoriasElegidas.add((activity as MainActivity).categorias[idxCategoria2].idCategoria)
                    publicacion.id_categoria = categoriasElegidas

                    val ingredientes = ArrayList<String>()
                    val idxEtIngrediente: Int =
                        binding.linearLayoutPadre.indexOfChild(binding.etIngrediente)
                    val idxBtnIngrediente: Int =
                        binding.linearLayoutPadre.indexOfChild(binding.btnAnadirIngrediente)
                    var hayIngrediente = false
                    for (i in idxEtIngrediente until idxBtnIngrediente) {
                        val etIngrediente: EditText = if (i!=idxEtIngrediente){
                            val linearLayout: LinearLayout = binding.linearLayoutPadre[i] as LinearLayout
                            linearLayout[0] as EditText
                        }else{
                            binding.linearLayoutPadre[i] as EditText
                        }

                        if (etIngrediente.text.toString() != "") {
                            hayIngrediente = true
                            ingredientes.add(etIngrediente.text.toString())
                        }
                    }
                    if (!hayIngrediente) {
                        showAlertIngrediente()
                    }
                    publicacion.ingredientes = ingredientes

                    val pasos = ArrayList<String>()
                    val idxEtPaso: Int = binding.linearLayoutPadre.indexOfChild(binding.etPaso)
                    val idxBtnPaso: Int = binding.linearLayoutPadre.indexOfChild(binding.btnAnadirpaso)
                    var hayPasos = false
                    for (i in idxEtPaso until idxBtnPaso) {
                        val etPaso: EditText = if (i!=idxEtPaso){
                            val linearLayout: LinearLayout = binding.linearLayoutPadre[i] as LinearLayout
                            linearLayout[0] as EditText
                        }else{
                            binding.linearLayoutPadre[i] as EditText
                        }
                        if (etPaso.text.toString() != "") {
                            hayPasos = true
                            pasos.add(etPaso.text.toString())
                        }
                    }
                    if (!hayPasos) {
                        showAlertPaso()
                    }
                    publicacion.pasos = pasos

                    publicacion.id_usuario = (activity as MainActivity).usuario.id_usuario

                    if (hayPasos && hayIngrediente) {
                        CoroutineScope(Dispatchers.IO).launch {
                            // Carga de imágenes y operaciones en Firebase Storage
                            if (imagenReceta != null) {
                                val baos = ByteArrayOutputStream()
                                imagenReceta!!.compress(Bitmap.CompressFormat.PNG, 100, baos)
                                val data = baos.toByteArray()

                                val reference =
                                    FirebaseStorage.getInstance().reference.child("publicaciones")
                                        .child(
                                            (activity as MainActivity).usuario.id_usuario + System.currentTimeMillis()
                                        )
                                reference.putBytes(data).addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        reference.downloadUrl.addOnSuccessListener { uri ->
                                            publicacion.imagen = uri.toString()
                                            crearPublicacionConImagen(publicacion,root)
                                        }.addOnFailureListener {
                                            // Manejar el error al obtener la URL de descarga
                                        }
                                    } else {
                                        // Manejar el error al cargar la imagen
                                    }
                                }
                                //crearPublicacion(publicacion, root)
                            } else {
                                crearPublicacion(publicacion, root)
                            }
                        }
                    }else{
                        habilitarFormulario()
                    }
                }
            }else{
                showAlertTitulo()
                habilitarFormulario()
            }
        }
        //endregion

        //region $Botones para la imagen
        val btncamara:Button=binding.btnCamara
        btncamara.setOnClickListener {
            val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra("android.intent.extra.PICTURE_QUALITY", 100)
            try {
                val photoFile = createImageFile()
                val photoURI:Uri = FileProvider.getUriForFile(this.requireContext(),
                BuildConfig.APPLICATION_ID+".provider",
                photoFile)
                // Asigna la URI como salida del intent de la cámara
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startForResult.launch(intent)
            } catch (ex: IOException) {
                // Error al crear el archivo
                ex.printStackTrace()
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

        //startActivityForResult(intent,100)
        openGalleryLaucher.launch(intent.type)
    }

    //Result de la galería
/*    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==100 && resultCode==RESULT_OK && data!=null){
            val imageUrl=data.data
            imagenReceta=MediaStore.Images.Media.getBitmap(requireContext().contentResolver,imageUrl)
            binding.ivFotoReceta.setImageURI(imageUrl)
        }
    }*/

    private val openGalleryLaucher = registerForActivityResult(ActivityResultContracts.GetContent()) { imageUrl ->
        // Lógica para manejar el resultado de la galería aquí
        if (imageUrl != null) {
            val source=ImageDecoder.createSource(requireContext().contentResolver,imageUrl)
            //imagenReceta = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUrl)
            imagenReceta = ImageDecoder.decodeBitmap(source)
            binding.ivFotoReceta.setImageURI(imageUrl)
        }
    }


    //Result de la camara
    private val startForResult=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result: ActivityResult ->
        if (result.resultCode== RESULT_OK){
            try {
                val imageFile = File(currentPhotoPath)
                if (imageFile.exists()) {
                    imagenReceta = BitmapFactory.decodeFile(imageFile.absolutePath)
                    this.binding.ivFotoReceta.setImageBitmap(imagenReceta)
                } else {
                    Log.e(":::Error", "El archivo de imagen no existe.")
                }
            }catch (e:Exception){
                Log.e(":::Error", e.message.toString())
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

    fun showAlertPublicacionCreada(){
        AlertDialog.Builder(activity)
            .setMessage("La publicacion se ha creado correctamente")
            .setPositiveButton("OK",null)
            .create()
            .show()
    }
    //endregion

    private fun limpiarFormulario(){
        binding.etTitulo.text=Editable.Factory.getInstance().newEditable("")
        binding.etDescripcion.text=Editable.Factory.getInstance().newEditable("")

        val idxEtIngrediente: Int =binding.linearLayoutPadre.indexOfChild(binding.etIngrediente)
        val idxBtnIngrediente: Int =binding.linearLayoutPadre.indexOfChild(binding.btnAnadirIngrediente)
        var arreglo=0
        for (i in idxEtIngrediente+1 until idxBtnIngrediente) {
            val linearLayout: LinearLayout = binding.linearLayoutPadre[i - arreglo] as LinearLayout
            binding.linearLayoutPadre.removeView(linearLayout)
            arreglo++
        }
        binding.etIngrediente.text=Editable.Factory.getInstance().newEditable("")

        arreglo=0
        val idxEtPaso: Int = binding.linearLayoutPadre.indexOfChild(binding.etPaso)
        val idxBtnPaso: Int = binding.linearLayoutPadre.indexOfChild(binding.btnAnadirpaso)
        for (i in idxEtPaso+1 until idxBtnPaso) {
            val linearLayout: LinearLayout = binding.linearLayoutPadre[i - arreglo] as LinearLayout
            binding.linearLayoutPadre.removeView(linearLayout)
            arreglo++
        }
        binding.etPaso.text=Editable.Factory.getInstance().newEditable("")

        binding.categoria1.setSelection(0)
        binding.categoria2.setSelection(0)

        binding.ivFotoReceta.setImageResource(R.mipmap.ic_launcher)
        imagenReceta=null
    }

    private fun desHabilitarFormulario(){
        binding.progressBar.visibility=View.VISIBLE

        binding.etTitulo.isEnabled=false
        binding.etDescripcion.isEnabled=false
        binding.btnCamara.isEnabled=false
        binding.btnGaleria.isEnabled=false
        binding.categoria1.isEnabled=false
        binding.categoria2.isEnabled=false

        val idxEtIngrediente: Int =binding.linearLayoutPadre.indexOfChild(binding.etIngrediente)
        val idxBtnIngrediente: Int =binding.linearLayoutPadre.indexOfChild(binding.btnAnadirIngrediente)
        for (i in idxEtIngrediente+1 until idxBtnIngrediente) {
            val etIngrediente: EditText = if (i!=idxEtIngrediente){
                val linearLayout: LinearLayout = binding.linearLayoutPadre[i] as LinearLayout
                (linearLayout[1] as Button).isEnabled=false
                linearLayout[0] as EditText
            }else{
                binding.linearLayoutPadre[i] as EditText
            }
            //val etIngrediente: EditText = binding.linearLayoutPadre[i] as EditText
            etIngrediente.isEnabled=false
        }
        binding.etIngrediente.isEnabled=false

        binding.btnAnadirIngrediente.isEnabled=false

        val idxEtPaso: Int = binding.linearLayoutPadre.indexOfChild(binding.etPaso)
        val idxBtnPaso: Int = binding.linearLayoutPadre.indexOfChild(binding.btnAnadirpaso)
        for (i in idxEtPaso+1 until idxBtnPaso) {
            val etPaso: EditText = if (i!=idxEtPaso){
                val linearLayout: LinearLayout = binding.linearLayoutPadre[i] as LinearLayout
                (linearLayout[1] as Button).isEnabled=false
                linearLayout[0] as EditText
            }else{
                binding.linearLayoutPadre[i] as EditText
            }
            etPaso.isEnabled=false
        }
        binding.etPaso.isEnabled=false

        binding.btnAnadirpaso.isEnabled=false
        binding.btnPublicar.isEnabled=false

        (activity as MainActivity).deshabilitarNavBar()
    }

    private fun habilitarFormulario(){
        binding.progressBar.visibility=View.GONE

        binding.etTitulo.isEnabled=true
        binding.etDescripcion.isEnabled=true
        binding.btnCamara.isEnabled=true
        binding.btnGaleria.isEnabled=true
        binding.categoria1.isEnabled=true
        binding.categoria2.isEnabled=true

        val idxEtIngrediente: Int =binding.linearLayoutPadre.indexOfChild(binding.etIngrediente)
        val idxBtnIngrediente: Int =binding.linearLayoutPadre.indexOfChild(binding.btnAnadirIngrediente)
        for (i in idxEtIngrediente+1 until idxBtnIngrediente) {
            val etIngrediente: EditText = if (i!=idxEtIngrediente){
                val linearLayout: LinearLayout = binding.linearLayoutPadre[i] as LinearLayout
                (linearLayout[1] as Button).isEnabled=true
                linearLayout[0] as EditText
            }else{
                binding.linearLayoutPadre[i] as EditText
            }
            //val etIngrediente: EditText = binding.linearLayoutPadre[i] as EditText
            etIngrediente.isEnabled=true
        }
        binding.etIngrediente.isEnabled=true

        binding.btnAnadirIngrediente.isEnabled=true

        val idxEtPaso: Int = binding.linearLayoutPadre.indexOfChild(binding.etPaso)
        val idxBtnPaso: Int = binding.linearLayoutPadre.indexOfChild(binding.btnAnadirpaso)
        for (i in idxEtPaso+1 until idxBtnPaso) {
            val etPaso: EditText = if (i!=idxEtPaso){
                val linearLayout: LinearLayout = binding.linearLayoutPadre[i] as LinearLayout
                (linearLayout[1] as Button).isEnabled=true
                linearLayout[0] as EditText
            }else{
                binding.linearLayoutPadre[i] as EditText
            }
            etPaso.isEnabled=true
        }
        binding.etPaso.isEnabled=true

        binding.btnAnadirpaso.isEnabled=true
        binding.btnPublicar.isEnabled=true

        (activity as MainActivity).habilitarNavBar()
    }

    private suspend fun crearPublicacion(publicacion: Publicacion,root:View) {
        // Realizar la creación de la publicación en Firebase Database o cualquier otra operación necesaria

        withContext(Dispatchers.Main) {
            binding.progressBar.visibility = View.GONE
            limpiarFormulario()
            DAOPublicacion().crearPublicacion(publicacion,root)
            DAOUsuario().anadeNuevaPublicacion((activity as MainActivity).usuario.id_usuario)
            (activity as MainActivity).usuario.num_publicacion++

            habilitarFormulario()
        }
    }

    private fun crearPublicacionConImagen(publicacion: Publicacion,root:View) {
        // Realizar la creación de la publicación en Firebase Database o cualquier otra operación necesaria

            binding.progressBar.visibility = View.GONE
            limpiarFormulario()
            DAOPublicacion().crearPublicacion(publicacion,root)
            DAOUsuario().anadeNuevaPublicacion((activity as MainActivity).usuario.id_usuario)

            // Realizar cualquier otra acción necesaria en el hilo principal

            habilitarFormulario()
    }
}