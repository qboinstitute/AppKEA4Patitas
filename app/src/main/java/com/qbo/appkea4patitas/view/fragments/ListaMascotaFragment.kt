package com.qbo.appkea4patitas.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.qbo.appkea4patitas.R
import com.qbo.appkea4patitas.databinding.FragmentListaMascotaBinding
import com.qbo.appkea4patitas.retrofit.PatitasCliente
import com.qbo.appkea4patitas.retrofit.response.ResponseMascota
import com.qbo.appkea4patitas.view.adapters.MascotaAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListaMascotaFragment : Fragment() {

    private var _binding: FragmentListaMascotaBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListaMascotaBinding.inflate(inflater,
        container, false)
        binding.rvmascotas.layoutManager = LinearLayoutManager(requireActivity())
        listarMascotas()
        return binding.root
    }

    private fun listarMascotas() {
        val call: Call<List<ResponseMascota>> = PatitasCliente
            .retrofitServicio.listarMascota()
        call.enqueue(object: Callback<List<ResponseMascota>>{
            override fun onResponse(
                call: Call<List<ResponseMascota>>,
                response: Response<List<ResponseMascota>>
            ) {
                binding.rvmascotas.adapter = MascotaAdapter(response.body()!!)
            }

            override fun onFailure(call: Call<List<ResponseMascota>>, t: Throwable) {

            }

        })

    }

}