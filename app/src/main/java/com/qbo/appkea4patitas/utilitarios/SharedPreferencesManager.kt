package com.qbo.appkea4patitas.utilitarios

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPreferencesManager() {

    fun getSharedPreferences(): SharedPreferences{
        return MiApp.applicationContext
            .getSharedPreferences(Constantes().APP_NOMBRE_PREFERENCIAS, MODE_PRIVATE)
    }

    fun setBooleanValue(nombre: String, valor: Boolean){
        val editor = getSharedPreferences().edit()
        editor.putBoolean(nombre, valor)
        editor.commit()
    }

    fun getBooleanValue(nombre: String): Boolean {
        return getSharedPreferences().getBoolean(nombre, false)
    }

    fun deletePreferences(nombre: String){
        getSharedPreferences().edit().remove(nombre).apply()
    }
}