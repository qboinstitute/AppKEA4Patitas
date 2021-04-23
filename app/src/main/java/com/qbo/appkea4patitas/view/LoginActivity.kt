package com.qbo.appkea4patitas.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.qbo.appkea4patitas.R
import com.qbo.appkea4patitas.databinding.ActivityLoginBinding
import com.qbo.appkea4patitas.databinding.ActivityMainBinding
import com.qbo.appkea4patitas.retrofit.PatitasCliente
import com.qbo.appkea4patitas.retrofit.request.RequestLogin
import com.qbo.appkea4patitas.retrofit.response.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

    fun autenticarUsuario(vista: View, usuario: String, password: String){
        val requestLogin = RequestLogin(usuario, password)
        val call : Call<ResponseLogin> = PatitasCliente
            .retrofitServicio
            .login(requestLogin)
        call.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if(response.body()!!.rpta){
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