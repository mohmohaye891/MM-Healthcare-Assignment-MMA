package com.example.mma.mmhealthcare.activities

import android.os.Bundle
import com.example.mma.mmhealthcare.R
import com.example.mma.mmhealthcare.data.vos.HealthCareInfoVO
import kotlinx.android.synthetic.main.activity_healthcare_detail.*

class HealthCareDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_healthcare_detail)

       val healthCareId = intent.getStringExtra("healthCareId")

       /* HealthCareInfoVO healthCare = HealthCareAppModel.getObjectInstance().getHealthById(healthCareId)

       bindData()*/
    }

    fun bindData(data: HealthCareInfoVO) {

        tv_healthcare_title!!.text = data.title
    }
}