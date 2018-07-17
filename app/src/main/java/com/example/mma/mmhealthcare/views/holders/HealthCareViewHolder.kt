package com.example.mma.mmhealthcare.views.holders

import android.view.View
import com.example.mma.mmhealthcare.data.vos.HealthCareInfoVO
import com.example.mma.mmhealthcare.delegates.HealthCareItemDelegate
import kotlinx.android.synthetic.main.view_item_healthcare.view.*

class HealthCareViewHolder(itemView: View, private val mDelegate: HealthCareItemDelegate) : BaseViewHolder<HealthCareInfoVO>(itemView) {

    override fun setData(data: HealthCareInfoVO) {
        mData = data

        itemView.tv_title!!.text = data.title
    }


    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        mDelegate.onTapHealthCare(mData)
    }


}