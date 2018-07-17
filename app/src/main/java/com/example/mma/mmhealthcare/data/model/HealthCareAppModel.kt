package com.example.mma.mmhealthcare.data.model

import com.example.mma.mmhealthcare.data.vos.HealthCareInfoVO
import com.example.mma.mmhealthcare.events.DataEvent
import com.example.mma.mmhealthcare.network.HealthCareDataAgent
import com.example.mma.mmhealthcare.utils.AppConstants
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class HealthCareAppModel {
    companion object {
        private var INSTANCE: HealthCareAppModel? = null
        fun getInstance(): HealthCareAppModel {
            if (INSTANCE == null) {
                INSTANCE = HealthCareAppModel()
            }

            val i = INSTANCE
            return i!!
        }
    }

    private constructor() {
        EventBus.getDefault().register(this)
    }

    private var mHealthCareData: HashMap<String, HealthCareInfoVO> = HashMap()

    fun loadHealthCare() {
        HealthCareDataAgent.getInstance().loadHealthCare(AppConstants.ACCESS_TOKEN)
    }

    fun forceLoadHealthCare() {
        mHealthCareData = HashMap()
        HealthCareDataAgent.getInstance().loadHealthCare(AppConstants.ACCESS_TOKEN)
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onHealthloadedEvent(healthCareLoadedEvent: DataEvent.HealthCareLoadedEvent) {
        for (healthcare: HealthCareInfoVO in healthCareLoadedEvent.loadedHealthCare) {
            mHealthCareData[healthcare.healthCareId] = healthcare
        }
    }

}