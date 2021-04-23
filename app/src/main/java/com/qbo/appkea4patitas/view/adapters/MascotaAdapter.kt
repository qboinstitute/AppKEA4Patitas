package com.qbo.appkea4patitas.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.qbo.appkea4patitas.databinding.ItemMascotaBinding
import com.qbo.appkea4patitas.retrofit.response.ResponseMascota

class MascotaAdapter (private var lstmascotas: List<ResponseMascota>)
    : RecyclerView.Adapter<MascotaAdapter.ViewHolder>()
{
    inner class ViewHolder(val binding: ItemMascotaBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaAdapter.ViewHolder {
        val binding = ItemMascotaBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MascotaAdapter.ViewHolder, position: Int) {
        with(holder){
            with(lstmascotas[position]){
                binding.tvnommascota.text = nommascota
                binding.tvcontacto.text = contacto
                binding.tvfechaperdida.text = fechaperdida
                binding.tvlugar.text = lugar
                Glide.with(itemView.context)
                    .load(urlimagen)
                    .into(binding.ivmascota)
            }
        }
    }

    override fun getItemCount() = lstmascotas.size
}