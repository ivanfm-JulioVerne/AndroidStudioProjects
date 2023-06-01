package com.example.minichef_v1.pantanllas.home.detallePublicacion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.minichef_v1.bd.dao.comentario.DAOComentario
import com.example.minichef_v1.bd.dao.comentario.IDAOComentario
import com.example.minichef_v1.bd.dao.like.DAOLike
import com.example.minichef_v1.bd.dao.like.IDAOLike
import com.example.minichef_v1.bd.dao.publicacion.DAOPublicacion
import com.example.minichef_v1.bd.dao.publicacion.IDAOPublicacion
import com.example.minichef_v1.bd.dao.usuario.DAOUsuario
import com.example.minichef_v1.bd.modelo.Comentario
import com.example.minichef_v1.bd.modelo.Usuario
import com.example.minichef_v1.pantanllas.perfil.PerfilViewModel

class DetallePublicacionViewModel(val idUsuario: String,val idPublicacion: String,val idAutor:String,val admin:Boolean) : ViewModel() {
    private val daoPublicacion: IDAOPublicacion = DAOPublicacion()
    private val daoLike: IDAOLike = DAOLike()
    private val daoComentario: IDAOComentario = DAOComentario()
    private val daoUsuario: DAOUsuario = DAOUsuario()

    private val _meGusto = MutableLiveData<Boolean>().apply {
        value = false
    }
    val meGusto: LiveData<Boolean> = _meGusto

    private val _comentarios=MutableLiveData<List<Comentario>>().apply {
        value= emptyList()
    }
    val comentarios: LiveData<List<Comentario>> = _comentarios

    private val _autor=MutableLiveData<Usuario>().apply {
        value=Usuario()
    }
    val autor:LiveData<Usuario> = _autor

    init {
        comprobarMeGusta(idUsuario, idPublicacion)
        mostrarComentarios(idPublicacion)
        buscarAutor(idAutor)
    }

    fun autorIsUsuario() :Boolean=(idUsuario==idAutor)

    fun comprobarMeGusta(idUsuario: String, idPublicacion: String) {
        daoLike.comprobarLike(this, idPublicacion, idUsuario)
    }

    fun setMeGusto(meGusto: Boolean) {
        this._meGusto.value = meGusto
    }

    fun pulsaMeGusta(idUsuario: String, idPublicacion: String) {
        if (idPublicacion != "" && idUsuario != "")
            if (_meGusto.value!!) {
                _meGusto.value = false
                daoPublicacion.quitarLike(idPublicacion, idUsuario)
            } else {
                _meGusto.value = true
                daoPublicacion.darLike(idPublicacion, idUsuario)
            }
    }

    fun mostrarComentarios(idPublicacion:String){
        daoComentario.mostrarComentariosByPublicacion(this,idPublicacion,admin)
    }

    fun setComentarios(comentarios:List<Comentario>){
        _comentarios.value=comentarios
    }

    fun anadirComentario(idPublicacion: String,idUsuario: String,texto:String,nickname:String){
        daoComentario.anadirComentario(idPublicacion,idUsuario,texto,nickname)
        mostrarComentarios(idPublicacion)
    }

    fun buscarAutor(idUsuario: String){
        daoUsuario.getUsuarioByIdFromDetallePublicion(idUsuario,this)
    }

    fun setAutor(usuario: Usuario){
        _autor.value=usuario
    }

    fun banearPublicacion(idPublicacion:String){
        daoPublicacion.banPublicacion(idPublicacion)
    }

    fun unbanearPublicacion(idPublicacion:String){
        daoPublicacion.unbanPublicacion(idPublicacion)
    }

    fun banearComentario(idComentario: String){
        daoComentario.banComentario(idComentario)
    }

    fun unbanearComentario(idComentario: String){
        daoComentario.unbanComentario(idComentario)
    }

}

@Suppress("UNCHECKED_CAST")
class DetallePublicacionViewModelFactory(private val idUsuario:String,private val idPublicacion:String,private val idAutor:String,private val admin:Boolean): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetallePublicacionViewModel(idUsuario,idPublicacion,idAutor,admin) as T
    }
}