package com.example.mma.mmhealthcare.data.vos

import com.google.gson.annotations.SerializedName

open class AuthorVO(@SerializedName("author-id") var authorId: String = "",
                    @SerializedName("author-name") var authorName: String = "",
                    @SerializedName("author-picture") var authorPicture: String = ""){

}