package com.example.mma.mmhealthcare.delegates

import com.example.mma.mmhealthcare.data.vos.HealthCareInfoVO

interface HealthCareItemDelegate {
    fun onTapHealthCare(healthCare: HealthCareInfoVO?)
    fun onTapComment()
    fun onTapLike()

}