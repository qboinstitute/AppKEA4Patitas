package com.qbo.appkea4patitas.utilitarios

import android.app.Application
import android.content.Context

class MiApp : Application() {

    init {
        instancia = this
    }

    companion object{
        lateinit var instancia: MiApp
        private set

        val applicationContext: Context get() {
            return instancia.applicationContext
        }
    }

}