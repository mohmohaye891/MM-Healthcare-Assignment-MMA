package com.example.mma.mmhealthcare.events

import com.example.mma.mmhealthcare.data.vos.HealthCareInfoVO

class DataEvent {

    class HealthCareLoadedEvent( val loadedHealthCare: List<HealthCareInfoVO>)

    class EmptyDataLoadedEvent(val errorMsg: String? = "Empty Body Response")
}