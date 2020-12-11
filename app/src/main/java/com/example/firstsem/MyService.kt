package com.example.firstsem

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import  retrofit2.http.Headers
import retrofit2.http.POST

interface MyService {

    //회원가입
    @Headers("Content-Type:application/json")
    @POST("/users/signup")
    fun postSignup(
        @Body body: RequestSignupData
    ):Call<ResponseSignupData>

    @Headers("Content-Type:application/json")
    @POST("/users/signin")
    fun postSignin(
        @Body body: RequestSigninData
    ):Call<ResponseSigninData>
}

object MyServiceImpl {
    private const val BASE_URL = "http://15.164.83.210:3000/"
    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service : MyService = retrofit.create(MyService::class.java)
}