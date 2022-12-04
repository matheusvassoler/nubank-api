package com.havebreak.nubank_api

import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("fact")
    suspend fun getEntries(): Response<Fact>
}