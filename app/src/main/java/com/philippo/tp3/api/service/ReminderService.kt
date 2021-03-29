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
    //Pode haver várias variáveis dentro do recurso (vários @Paths)
    suspend fun show(@Path("id") id: Long): ReminderDetail  //detail

    @Headers("Authorization: Bearer 99cf4cf33e7f3b65da540e59e3f7da1794219aa6")
    @GET("rest/v1/projects")
    //Uma variável que não faz parte da URI da requisição (quando vem após o "?"), é utilizada a anotação @Query
    suspend fun allByReminder(@Query("member") id: Long): List<Reminder>

    @Headers("Authorization: Bearer 99cf4cf33e7f3b65da540e59e3f7da1794219aa6")
    @DELETE("rest/v1/projects/{id}")
    //Pode haver várias variáveis dentro do recurso (vários @Paths)
    suspend fun deleteReminder(@Path("id") id: Long)

    @Headers("Authorization: Bearer 99cf4cf33e7f3b65da540e59e3f7da1794219aa6")
    @POST("rest/v1/projects")
    suspend fun createReminder(@Body reminderApi: Reminder)

    @Headers("Authorization: Bearer 99cf4cf33e7f3b65da540e59e3f7da1794219aa6")
    @PUT("rest/v1/projects/{id}")
    //Pode haver várias variáveis dentro do recurso (vários @Paths)
    suspend fun updateReminder(@Path("id") id: Long, @Body reminderApi: Reminder)
}