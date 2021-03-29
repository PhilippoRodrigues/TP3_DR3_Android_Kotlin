package com.philippo.tp3.api

import com.philippo.tp3.api.service.ReminderService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private var instance: Retrofit? = null
    private fun getInstance(): Retrofit {
        if (instance == null)
            instance = Retrofit
                .Builder()
                .baseUrl("https://api.todoist.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return instance as Retrofit
    }

    fun getReminderService(): ReminderService {
        return getInstance().create(ReminderService::class.java)
    }
}