package com.qbo.appkea4patitas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.qbo.appkea4patitas.R
import com.qbo.appkea4patitas.databinding.ActivityLoginBinding
import com.qbo.appkea4patitas.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
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