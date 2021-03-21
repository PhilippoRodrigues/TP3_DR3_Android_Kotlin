package com.philippo.api.dao

import com.philippo.api.model.ReminderApi
import retrofit2.http.GET

interface ReminderApiService {

    @GET("api/v1/projects")
    suspend fun all(): List<ReminderApi>
}