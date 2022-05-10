package com.application.zomat.data

import com.application.zomat.data.dto.AllData
import com.application.zomat.data.dto.Container
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("BookofJungle1.php")
    suspend fun setDataServer(@Body user: AllData): Container

}