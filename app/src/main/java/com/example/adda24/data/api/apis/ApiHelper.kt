package com.example.adda24.data.api.apis

class ApiHelper(private val apiService: ApiService) {

    suspend fun getUsers() =
        apiService.getUsers()


}