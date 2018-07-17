package com.example.mma.mmhealthcare.network

import com.example.mma.mmhealthcare.network.responses.GetHealthCareResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface HealthCareApi {

    @FormUrlEncoded
    @POST("GetHealthcareInfo.php")
    fun loadMMHealthCare(
        @Field("access_token") accessToken: String) : Call<GetHealthCareResponse>
}