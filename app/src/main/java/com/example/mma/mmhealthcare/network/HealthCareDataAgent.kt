package com.example.mma.mmhealthcare.network

import com.example.mma.mmhealthcare.events.DataEvent
import com.example.mma.mmhealthcare.events.ErrorEvent
import com.example.mma.mmhealthcare.network.responses.GetHealthCareResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HealthCareDataAgent {

    companion object {
        private var INSTANCE: HealthCareDataAgent? = null
        fun getInstance(): HealthCareDataAgent {
            if (INSTANCE == null) {
                INSTANCE = HealthCareDataAgent()
            }
            val i = INSTANCE
            return i!!
        }
    }

    private val mHealthCareApi : HealthCareApi

    private constructor() {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-5/mm-healthcare/")
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(okHttpClient)
                .build()

        mHealthCareApi = retrofit.create(HealthCareApi::class.java)
    }

    fun loadHealthCare(accessToken: String) {
        val newResponseCall = mHealthCareApi.loadMMHealthCare(accessToken)
        newResponseCall.enqueue(object : Callback<GetHealthCareResponse> {

            override fun onFailure(call: Call<GetHealthCareResponse>?, t: Throwable?) {
                EventBus.getDefault().post(ErrorEvent.ApiErrorEvent(t))
            }

            override fun onResponse(call: Call<GetHealthCareResponse>, response: Response<GetHealthCareResponse>) {
                val healthCareResponse: GetHealthCareResponse? = response.body()
                if (healthCareResponse != null
                        && healthCareResponse.getCode() == 200
                        && healthCareResponse.gethealthCareList().isNotEmpty()) {
                    val healthCareLoadedEvent = DataEvent.HealthCareLoadedEvent(healthCareResponse.gethealthCareList())
                    EventBus.getDefault().post(healthCareLoadedEvent)
                }else{
                    if (healthCareResponse != null)
                        EventBus.getDefault().post(DataEvent.EmptyDataLoadedEvent(healthCareResponse.getMessage()))
                    else
                        EventBus.getDefault().post(DataEvent.EmptyDataLoadedEvent())
                }
            }

        })
    }
}