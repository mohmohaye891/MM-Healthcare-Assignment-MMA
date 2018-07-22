package com.example.mma.mmhealthcare.views.holders

import android.view.View
import com.bumptech.glide.Glide
import com.example.mma.mmhealthcare.data.vos.HealthCareInfoVO
import com.example.mma.mmhealthcare.delegates.HealthCareItemDelegate
import kotlinx.android.synthetic.main.view_item_healthcare.view.*

class HealthCareViewHolder(itemView: View, private val mDelegate: HealthCareItemDelegate) : BaseViewHolder<HealthCareInfoVO>(itemView) {

    override fun setData(data: HealthCareInfoVO) {
        mData = data

        itemView.tv_title!!.text = data.title

       Glide.with(itemView.context)
                .load(data.image)
                .into(itemView.iv_fee)


    }

    override fun onClick(v: View?) {
        mDelegate.onTapHealthCare(mData)
    }
}