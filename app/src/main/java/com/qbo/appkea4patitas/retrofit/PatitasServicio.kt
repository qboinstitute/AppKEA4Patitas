package com.qbo.appkea4patitas.retrofit

import com.qbo.appkea4patitas.retrofit.request.RequestLogin
import com.qbo.appkea4patitas.retrofit.request.RequestRegistro
import com.qbo.appkea4patitas.retrofit.response.ResponseLogin
import com.qbo.appkea4patitas.retrofit.response.ResponseRegistro
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface PatitasServicio {

    @POST("login.php")
    fun login(@Body requestLogin: RequestLogin) : Call<ResponseLogin>

    @PUT
    fun registro(@Body requestRegistro: RequestRegistro): Call<ResponseRegistro>

}