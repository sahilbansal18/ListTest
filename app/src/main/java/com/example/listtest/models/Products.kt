package com.example.listtest.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.*
import kotlinx.parcelize.Parcelize

@Parcelize
data class Products (

    @SerializedName("id"          ) var id          : Int?    = null,
    @SerializedName("title"       ) var title       : String? = null,
    @SerializedName("price"       ) var price       : Double? = null,
    @SerializedName("description" ) var description : String? = null,
    @SerializedName("category"    ) var category    : String? = null,
    @SerializedName("image"       ) var image       : String? = null,
    @SerializedName("rating"      ) var rating      : Rating? = Rating(),

    var favorite : Boolean? = false,
): Parcelable

