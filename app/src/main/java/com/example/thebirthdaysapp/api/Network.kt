package com.example.thebirthdaysapp.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// https://randomuser.me/api/?results=1000&seed=chalkboard&inc=name,dob

interface BirthdaysAPI {

    //@GET(value = "/?results=1000&seed=chalkboard&inc=name,dob")
    @GET(value = "?results=1000&seed=chalkboard&inc=name,dob")
    suspend fun getLatestBirthdayAndOtherDetails(): Response<BirthdaysOfPeople>

}

object RetrofitInstance {

    val API: BirthdaysAPI by lazy { // so that this is created at first use and the result is stored.
        Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BirthdaysAPI::class.java)
    }

}