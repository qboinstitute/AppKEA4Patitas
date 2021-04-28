package com.qbo.appkea4patitas.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.qbo.appkea4patitas.R
import com.qbo.appkea4patitas.databinding.FragmentVoluntarioBinding
import com.qbo.appkea4patitas.db.entity.PersonaEntity
import com.qbo.appkea4patitas.retrofit.PatitasCliente
import com.qbo.appkea4patitas.retrofit.request.RequestVoluntario
import com.qbo.appkea4patitas.retrofit.response.ResponseRegistro
import com.qbo.appkea4patitas.viewmodel.PersonaViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class VoluntarioFragment : Fragment() {

    private var _binding: FragmentVoluntarioBinding? = null
    private val binding get() = _binding!!

    private lateinit var personaViewModel: PersonaViewModel
    private lateinit var personaEntity: PersonaEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVoluntarioBinding.inflate(inflater,
            container, false)

        personaViewModel = ViewModelProvider(requireActivity())
            .get(PersonaViewModel::class.java)
        personaViewModel.obtener()
            .observe(viewLifecycleOwner, Observer { persona ->
                persona.let {
                    if(persona.esvoluntario == "1"){
                        setearControles()
                    }else{
                        personaEntity = persona
                    }
                }
            })

        binding.btnregvoluntario.setOnClickListener {
            if(binding.cbaceptarvoluntario.isChecked){
                binding.btnregvoluntario.isEnabled = false
                registrarVoluntario(it)
            }else{
                mensaje(it, getString(R.string.msgerroregvoluntario))
            }
        }
        return binding.root
    }

    fun setearControles(){
        binding.tvtextovoluntario.visibility = View.GONE
        binding.btnregvoluntario.visibility = View.GONE
        binding.cbaceptarvoluntario.visibility = View.GONE
        binding.tvtitulovoluntario.text = getString(R.string.msgvoluntario)
    }

    private fun mensaje(vista: View, mensaje: String) {
        Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG).show()
    }

    private fun registrarVoluntario(vista: View) {
        val call: Call<ResponseRegistro> = PatitasCliente
            .retrofitServicio.registrarVoluntario(RequestVoluntario(personaEntity.id))
        call.enqueue(object : Callback<ResponseRegistro>{
            override fun onResponse(
                call: Call<ResponseRegistro>,
                response: Response<ResponseRegistro>
            ) {
                if(response.body()!!.rpta){
                    val nuevaPersonaEntity = PersonaEntity(
                        personaEntity.id,
                        personaEntity.nombres,
                        personaEntity.apellidos,
                        personaEntity.email,
                        personaEntity.celular,
                        personaEntity.usuario,
                        personaEntity.password,
                        "1"
                    )
                    personaViewModel.actualizar(nuevaPersonaEntity)
                    setearControles()
                }
                mensaje(vista, response.body()!!.mensaje)
                binding.btnregvoluntario.isEnabled = true
            }

            override fun onFailure(call: Call<ResponseRegistro>, t: Throwable) {
                binding.btnregvoluntario.isEnabled = true
            }

        })

    }

}