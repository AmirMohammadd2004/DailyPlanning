package com.amir.dailyplanning.model.net

import com.amir.dailyplanning.model.data.Data_Planing
import com.amir.dailyplanning.util.BASE_URL
import com.amir.dailyplanning.util.TOKEN
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface ApiService {


    @GET("ai/daily-plan")
    suspend fun dailyPlaning(
        @Query("schedule") schedule : String,
        @Query("token") token : String = TOKEN
    ): Data_Planing


}

fun createApiService(): ApiService {

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)   // زمان اتصال به سرور
        .readTimeout(30, TimeUnit.SECONDS)      // زمان خواندن پاسخ
        .writeTimeout(30, TimeUnit.SECONDS)     // زمان نوشتن درخواست
        .retryOnConnectionFailure(true)   // در صورت مشکل شبکه تلاش مجدد
        .build()


    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)
}


