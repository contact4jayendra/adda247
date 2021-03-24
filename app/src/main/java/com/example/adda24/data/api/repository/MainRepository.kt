package com.example.adda24.data.api.repository

import com.example.adda24.data.api.apis.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() =
        apiHelper.getUsers()

}