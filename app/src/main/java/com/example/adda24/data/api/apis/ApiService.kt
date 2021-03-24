package com.example.adda24.data.api.apis

import com.example.adda24.data.api.model.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response

}

