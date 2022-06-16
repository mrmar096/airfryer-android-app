package com.mrmar.airfryer.data.datasources.cloud.model.request


import com.google.gson.annotations.SerializedName
import com.mrmar.airfryer.data.datasources.cloud.model.mixed.cook.CookStatusMixed

internal data class CloudRequestCookMode(
    @SerializedName("appointmentTs")
    val appointmentTs: Int = 0,
    @SerializedName("cookSetTemp")
    val cookSetTemp: Int? = null,
    @SerializedName("cookSetTime")
    val cookSetTime: Int? = null,
    @SerializedName("cookStatus")
    val cookStatus: CookStatusMixed?,
    @SerializedName("customRecipe")
    val customRecipe: String = "Manual",
    @SerializedName("mode")
    val mode: String = "custom",
    @SerializedName("readyStart")
    val readyStart: Boolean = false,
    @SerializedName("recipeId")
    val recipeId: Int = 1,
    @SerializedName("recipeType")
    val recipeType: Int = 1,
    @SerializedName("tempUnit")
    val tempUnit: String = "celsius"
)