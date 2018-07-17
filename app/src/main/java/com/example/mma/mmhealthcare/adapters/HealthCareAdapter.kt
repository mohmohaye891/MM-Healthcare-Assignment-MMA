package com.example.mma.mmhealthcare.adapters

import android.content.Context
import android.view.ViewGroup
import com.example.mma.mmhealthcare.R
import com.example.mma.mmhealthcare.data.vos.HealthCareInfoVO
import com.example.mma.mmhealthcare.delegates.HealthCareItemDelegate
import com.example.mma.mmhealthcare.views.holders.BaseViewHolder
import com.example.mma.mmhealthcare.views.holders.HealthCareViewHolder

class HealthCareAdapter(context: Context,
                        private val mHealthCareItemDelegate: HealthCareItemDelegate): BaseRecyclerAdapter<HealthCareViewHolder, HealthCareInfoVO>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthCareViewHolder {
        val healthcareItemView = mLayoutInflator.inflate(R.layout.view_item_healthcare, parent, false)
        return HealthCareViewHolder(healthcareItemView, mHealthCareItemDelegate)

    }

}