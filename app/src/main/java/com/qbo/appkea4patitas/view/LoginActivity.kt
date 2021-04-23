package com.qbo.appkea4patitas.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.qbo.appkea4patitas.R
import com.qbo.appkea4patitas.databinding.ActivityLoginBinding
import com.qbo.appkea4patitas.databinding.ActivityMainBinding
import com.qbo.appkea4patitas.db.entity.PersonaEntity
import com.qbo.appkea4patitas.retrofit.PatitasCliente
import com.qbo.appkea4patitas.retrofit.request.RequestLogin
import com.qbo.appkea4patitas.retrofit.response.ResponseLogin
import com.qbo.appkea4patitas.utilitarios.Constantes
import com.qbo.appkea4patitas.utilitarios.SharedPreferencesManager
import com.qbo.appkea4patitas.viewmodel.PersonaViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var personaViewModel: PersonaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        personaViewModel = ViewModelProvider(this)
            .get(PersonaViewModel::class.java)

        if(verificarValorSharedPreferences()){
            binding.cbrecordar.isChecked = true
            binding.etusuariologin.isEnabled = false
            binding.etpasswordlogin.isEnabled = false
            binding.cbrecordar.text = getString(R.string.valcbrecordarcheck)
            personaViewModel.obtener()
                .observe(this, Observer { persona->
                    persona?.let {
                        binding.etusuariologin.setText(persona.usuario)
                        binding.etpasswordlogin.setText(persona.password)
                    }
                })
        }else{
            personaViewModel.eliminartodo()
        }

        binding.btnlogin.setOnClickListener {
            binding.btnlogin.isEnabled = false
            if(validarUsuarioPassword()){
                autenticarUsuario(it, binding.etusuariologin.text.toString(),
                binding.etpasswordlogin.text.toString())
            }else{
                binding.btnlogin.isEnabled = true
                mensaje(it, getString(R.string.msgusupassword))
            }
        }
        binding.tvregistrarusuario.setOnClickListener {
            startActivity(Intent(applicationContext,
            RegistroActivity::class.java))
        }
    }

    fun verificarValorSharedPreferences(): Boolean{
        return SharedPreferencesManager().getBooleanValue(Constantes().PREF_RECORDAR)
    }
    fun autenticarUsuario(vista: View, usuario: String, password: String){
        val requestLogin = RequestLogin(usuario, password)
        val call : Call<ResponseLogin> = PatitasCliente
            .retrofitServicio
            .login(requestLogin)
        call.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                val respuesta = response.body()!!
                if(respuesta.rpta){
                    val personaEntity = PersonaEntity(
                        respuesta.idpersona.toInt(),
                        respuesta.nombres,
                        respuesta.apellidos,
                        respuesta.email,
                        respuesta.celular,
                        respuesta.usuario,
                        respuesta.password,
                        respuesta.esvoluntario
                    )
                    if(verificarValorSharedPreferences()){
                        personaViewModel.actualizar(personaEntity)
                    }else{
                        personaViewModel.insertar(personaEntity)
                        if(binding.cbrecordar.isChecked){
                            SharedPreferencesManager()
                                .setBooleanValue(Constantes().PREF_RECORDAR, true)
                        }
                    }

                    startActivity(Intent(applicationContext,
                        HomeActivity::class.java))
                    finish()
                }else{
                    mensaje(vista, getString(R.string.msgerrorlogin))
                }
                binding.btnlogin.isEnabled = true
            }
            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                mensaje(vista, getString(R.string.errorapirest))
                binding.btnlogin.isEnabled = true
            }

        })

    }

    fun validarUsuarioPassword() : Boolean {
        var respuesta = true
        if(binding.etusuariologin.text.toString().trim().isEmpty()){
            binding.etusuariologin.isFocusableInTouchMode = true
            binding.etusuariologin.requestFocus()
            respuesta = false
        }else if(binding.etpasswordlogin.text.toString().trim().isEmpty()){
            binding.etpasswordlogin.isFocusableInTouchMode = true
            binding.etpasswordlogin.requestFocus()
            respuesta = false
        }
        return respuesta
    }

    fun mensaje(vista: View, mensaje : String){
        Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG).show()
    }
}