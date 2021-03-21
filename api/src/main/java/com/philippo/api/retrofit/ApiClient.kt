package com.philippo.api.retrofit

import com.philippo.api.dao.ReminderApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private var instance: Retrofit? = null
    private fun getInstance(): Retrofit {
        if (instance == null)
            instance = Retrofit
                .Builder()
                .baseUrl("https://taiga.tic.ufrj.br")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return instance as Retrofit
    }

    fun getReminderService(): ReminderApiService {
        return getInstance().create(ReminderApiService::class.java)
    }
}