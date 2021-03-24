package com.philippo.api.dao

import com.philippo.api.model.ReminderApi
import com.philippo.api.model.ReminderDetail
import retrofit2.http.*

interface ReminderApiService {

    @GET("rest/v1/projects")
    suspend fun all(@Header("Authorization: Bearer ") token: String): List<ReminderApi>

    @GET("rest/v1/projects/{id}")
    //Pode haver várias variáveis dentro do recurso (vários @Paths)
    suspend fun show(@Path("id") id: Long): ReminderDetail

    @GET("rest/v1/projects")
    //Uma variável que não faz parte da URI da requisição (quando vem após o "?"), é utilizada a anotação @Query
    suspend fun allByReminder(@Query("member") id: Long): List<ReminderApi>

    @DELETE("rest/v1/projects/{id}")
    //Pode haver várias variáveis dentro do recurso (vários @Paths)
    suspend fun deleteReminder(
        @Header("Authorization: Bearer ") token: String,
        @Path("id") id: Long)

    @POST("rest/v1/projects")
    suspend fun createReminder(
        @Header("Authorization: Bearer ") token: String,
        @Body reminderApi: ReminderApi)

    @PUT("rest/v1/projects/{id}")
    //Pode haver várias variáveis dentro do recurso (vários @Paths)
    suspend fun updateReminder(
        @Header("Authorization: Bearer ") token: String,
        @Path("id") id: Long,
        @Body reminderApi: ReminderApi)
}