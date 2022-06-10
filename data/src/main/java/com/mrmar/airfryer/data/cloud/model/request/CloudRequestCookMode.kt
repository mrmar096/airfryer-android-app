package com.mrmar.airfryer.data.cloud.model.request


import com.google.gson.annotations.SerializedName

internal data class CloudRequestCookMode(
    @SerializedName("appointmentTs")
    val appointmentTs: Int = 0,
    @SerializedName("cookSetTemp")
    val cookSetTemp: Int?,
    @SerializedName("cookSetTime")
    val cookSetTime: Int?,
    @SerializedName("cookStatus")
    val cookStatus: String?,
    @SerializedName("customRecipe")
    val customRecipe: String = "Manual",
    @SerializedName("mode")
    val mode: String = "custom",
    @SerializedName("readyStart")
    val readyStart: Boolean?,
    @SerializedName("recipeId")
    val recipeId: Int?,
    @SerializedName("recipeType")
    val recipeType: Int = 1,
    @SerializedName("tempUnit")
    val tempUnit: String = "celsius"
)