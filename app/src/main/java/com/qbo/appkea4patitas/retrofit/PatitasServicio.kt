package com.qbo.appkea4patitas.retrofit

import com.qbo.appkea4patitas.retrofit.request.RequestLogin
import com.qbo.appkea4patitas.retrofit.request.RequestRegistro
import com.qbo.appkea4patitas.retrofit.request.RequestVoluntario
import com.qbo.appkea4patitas.retrofit.response.ResponseLogin
import com.qbo.appkea4patitas.retrofit.response.ResponseMascota
import com.qbo.appkea4patitas.retrofit.response.ResponseRegistro
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface PatitasServicio {

    @POST("login.php")
    fun login(@Body requestLogin: RequestLogin) : Call<ResponseLogin>

    @PUT("persona.php")
    fun registro(@Body requestRegistro: RequestRegistro): Call<ResponseRegistro>

    @GET("mascotaperdida.php")
    fun listarMascota(): Call<List<ResponseMascota>>

    @POST("personavoluntaria.php")
    fun registrarVoluntario(@Body requestVoluntario: RequestVoluntario) :
            Call<ResponseRegistro>

}