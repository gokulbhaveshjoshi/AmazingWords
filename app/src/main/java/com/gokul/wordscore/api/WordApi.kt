package com.gokul.wordscore.api

import com.gokul.wordscore.model.Words
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface WordApi {
    @GET("words")
    fun getWords(@Query("ml") query: String) : Call<Words>

}