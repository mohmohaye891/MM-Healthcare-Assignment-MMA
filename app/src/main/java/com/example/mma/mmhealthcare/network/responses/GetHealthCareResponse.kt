package com.example.mma.mmhealthcare.network.responses

import com.example.mma.mmhealthcare.data.vos.HealthCareInfoVO
import com.google.gson.annotations.SerializedName

class GetHealthCareResponse {

    @SerializedName("code")
    private val code: Int = 0

    @SerializedName("message")
    private val message: String? = null

    @SerializedName("healthcare-info")
    private var healthCareList: List<HealthCareInfoVO>? = null

    fun getCode(): Int {
        return code
    }

    fun getMessage(): String? {
        return message
    }

    fun gethealthCareList(): List<HealthCareInfoVO> {
        if (healthCareList == null) {
            healthCareList = ArrayList<HealthCareInfoVO>()
        }
        val healthCareListVal = healthCareList
        return healthCareListVal!!
    }
}