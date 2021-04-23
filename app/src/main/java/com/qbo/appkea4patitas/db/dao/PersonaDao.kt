package com.qbo.appkea4patitas.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.qbo.appkea4patitas.db.entity.PersonaEntity

@Dao
interface PersonaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(personaEntity: PersonaEntity)

    @Update
    suspend fun actualizar(personaEntity: PersonaEntity)

    @Query("DELETE FROM persona")
    suspend fun eliminartodo()

    @Query("SELECT * FROM persona LIMIT 1")
    fun obtener(): LiveData<PersonaEntity>

}