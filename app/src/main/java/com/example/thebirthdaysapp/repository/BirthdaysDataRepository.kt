package com.example.thebirthdaysapp.repository

import com.example.thebirthdaysapp.api.Result
import com.example.thebirthdaysapp.api.RetrofitInstance
import com.example.thebirthdaysapp.helpers.Resource
import java.io.IOException

class BirthdaysDataRepository {

    suspend fun getBirthdaysAndOtherDataFromAPI(): Resource<List<Result>> {

        return try {
            val response = RetrofitInstance.API.getLatestBirthdayAndOtherDetails()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    Resource.Success(response.body()!!.results) // Only return things the viewmodel needs and nothing else
                } else {
                    Resource.Error("Error encountered: response from the web is empty.") //
                }
            } else {
                Resource.Error(response.errorBody().toString())
            }
        } catch (e: IOException) {
            Resource.Error("Error encountered: couldn't connect to the internet. Check if there is an internet connection from the device.")
        } catch (e: Exception) {
            Resource.Error("Error encountered: "  + e.message.toString() + ".")
        }

    }

}