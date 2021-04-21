package com.qbo.appkea4patitas.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.qbo.appkea4patitas.R
import com.qbo.appkea4patitas.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvregresarlogin.setOnClickListener {
            startActivity(Intent(applicationContext,
                LoginActivity::class.java))
        }
        binding.btnregistrar.setOnClickListener {
            binding.btnregistrar.isEnabled = false
            if(validarFormulario(it)){
                registrarUsuario(it)
            }else{
                mensaje(it, getString(R.string.msgerroregistro))
                binding.btnregistrar.isEnabled = true
            }
        }
    }

    private fun registrarUsuario(vista: View) {
        //val requestRegistro = RequestR

    }

    fun validarFormulario(vista: View): Boolean{
        var respuesta = true
        when{
            binding.etnombrereg.text.toString().trim().isEmpty() ->
            {
                binding.etnombrereg.isFocusableInTouchMode = true
                binding.etnombrereg.requestFocus()
                respuesta = false
            }
            binding.etapellidoreg.text.toString().trim().isEmpty() ->
            {
                binding.etapellidoreg.isFocusableInTouchMode = true
                binding.etapellidoreg.requestFocus()
                respuesta = false
            }
            binding.etemailreg.text.toString().trim().isEmpty() ->
            {
                binding.etemailreg.isFocusableInTouchMode = true
                binding.etemailreg.requestFocus()
                respuesta = false
            }
            binding.etcelularreg.text.toString().trim().isEmpty() ->
            {
                binding.etcelularreg.isFocusableInTouchMode = true
                binding.etcelularreg.requestFocus()
                respuesta = false
            }
            binding.etusuarioreg.text.toString().trim().isEmpty() ->
            {
                binding.etusuarioreg.isFocusableInTouchMode = true
                binding.etusuarioreg.requestFocus()
                respuesta = false
            }
            binding.etpasswordreg.text.toString().trim().isEmpty() ->
            {
                binding.etpasswordreg.isFocusableInTouchMode = true
                binding.etpasswordreg.requestFocus()
                respuesta = false
            }
            binding.etpasswordreg.text.toString().trim().isNotEmpty() ->{
                if(binding.etpasswordreg.text.toString()
                    != binding.etpasswordrep.text.toString()){
                    binding.etpasswordreg.isFocusableInTouchMode = true
                    binding.etpasswordreg.requestFocus()
                    respuesta = false
                }
            }

        }
        return respuesta
    }
    private fun setearControles(){
        binding.etnombrereg.setText("")
        binding.etapellidoreg.setText("")
        binding.etemailreg.setText("")
        binding.etcelularreg.setText("")
        binding.etusuarioreg.setText("")
        binding.etpasswordreg.setText("")
        binding.etpasswordrep.setText("")
    }

    fun mensaje (vista: View, mensaje: String){
        Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG).show()
    }

}