package com.qbo.appkea4patitas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.qbo.appkea4patitas.db.PatitasRoomDatabase
import com.qbo.appkea4patitas.db.entity.PersonaEntity
import com.qbo.appkea4patitas.repository.PersonaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonaViewModel(application: Application)
    :AndroidViewModel(application)
{
    private val repository: PersonaRepository
        init {
            val personaDao = PatitasRoomDatabase.getDatabase(application)
                .personaDao()
            repository = PersonaRepository(personaDao)
        }
    fun insertar(personaEntity: PersonaEntity)
    = viewModelScope.launch(Dispatchers.IO){
        repository.insertar(personaEntity)
    }

    fun actualizar(personaEntity: PersonaEntity)
    = viewModelScope.launch(Dispatchers.IO){
        repository.actualizar(personaEntity)
    }

    fun eliminartodo() = viewModelScope.launch(Dispatchers.IO){
        repository.eliminartodo()
    }

    fun obtener(): LiveData<PersonaEntity>{
        return repository.obtener()
    }

}