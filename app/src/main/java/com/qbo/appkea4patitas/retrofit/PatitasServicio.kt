package com.qbo.appkea4patitas.retrofit

import com.qbo.appkea4patitas.retrofit.request.RequestLogin
import com.qbo.appkea4patitas.retrofit.response.ResponseLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PatitasServicio {

    @POST("login.php")
    fun login(@Body requestLogin: RequestLogin) : Call<ResponseLogin>

}