package com.example.mma.mmhealthcare.data.vos

import com.google.gson.annotations.SerializedName

open class HealthCareInfoVO(healthCareId: String = "",
                             title: String = "",
                             image: String = "",
                             author: AuthorVO? = null,
                             shortDescription: String = "",
                             publishedDate: String = "",
                             completeUrl: String = "" ){
    @SerializedName("id")
    open var healthCareId: String = healthCareId

    @SerializedName("title")
    var title: String = title

    @SerializedName("image")
    var image: String = image

    @SerializedName("author")
    var author: AuthorVO? = author

    @SerializedName("short-description")
    var shortDescription: String = shortDescription

    @SerializedName("published-date")
    var publishedDate: String = publishedDate

    @SerializedName("complete-url")
    var completeUrl: String = completeUrl
}