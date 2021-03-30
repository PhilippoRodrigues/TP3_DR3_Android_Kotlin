package com.philippo.tp3.api.service

import com.philippo.tp3.model.Reminder
import com.philippo.tp3.model.ReminderDetail
import retrofit2.http.*

interface ReminderService {
    @Headers("Authorization: Bearer 99cf4cf33e7f3b65da540e59e3f7da1794219aa6")
    @GET("rest/v1/projects")
    suspend fun all(): List<Reminder>

    @Headers("Authorization: Bearer 99cf4cf33e7f3b65da540e59e3f7da1794219aa6")
    @GET("rest/v1/projects/{id}")
    suspend fun show(@Path("id") id: Long): ReminderDetail  //detail

    @Headers("Authorization: Bearer 99cf4cf33e7f3b65da540e59e3f7da1794219aa6")
    @GET("rest/v1/projects")
    suspend fun allByReminder(@Query("member") id: Long): List<Reminder>

    @Headers("Authorization: Bearer 99cf4cf33e7f3b65da540e59e3f7da1794219aa6")
    @DELETE("rest/v1/projects/{id}")
    suspend fun deleteReminder(@Path("id") id: Long)

    @Headers("Authorization: Bearer 99cf4cf33e7f3b65da540e59e3f7da1794219aa6")
    @POST("rest/v1/projects")
    suspend fun createReminder(@Body reminder: Reminder)

    @Headers("Authorization: Bearer 99cf4cf33e7f3b65da540e59e3f7da1794219aa6")
    @PUT("rest/v1/projects/{id}")
    suspend fun updateReminder(@Path("id") id: Long, @Body reminder: Reminder)
}