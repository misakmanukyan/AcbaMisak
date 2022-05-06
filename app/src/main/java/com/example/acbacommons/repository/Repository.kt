package com.example.acbacommons.repository

import com.example.acbacommons.api.RetrofitInstance
import com.example.acbacommons.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }
}