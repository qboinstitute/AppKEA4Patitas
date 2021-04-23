package com.qbo.appkea4patitas.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persona")
data class PersonaEntity(
    @PrimaryKey
    val id: Int,
    val nombres: String,
    val apellidos: String,
    val email: String,
    val celular: String,
    val password: String,
    val esvoluntario: String
)