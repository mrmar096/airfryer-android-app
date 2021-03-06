package com.mrmar.airfryer.data.datasources.cloud.model.request

import com.google.gson.annotations.SerializedName

internal open class CloudRequestModel(
    @SerializedName("acceptLanguage")
    val acceptLanguage: String = "es",
    @SerializedName("accountID")
    val accountID: String? = "",
    @SerializedName("appVersion")
    val appVersion: String = "VeSync 3.1.52 build9",
    @SerializedName("cid")
    val cid: String? = null,
    @SerializedName("debugMode")
    val debugMode: Boolean = false,
    @SerializedName("jsonCmd")
    val jsonCmd: CloudRequestCookCommand? = null,
    @SerializedName("deviceList")
    val deviceList: List<CloudRequestDevice>? = null,
    @SerializedName("method")
    val method: CloudRequestMethods,
    @SerializedName("phoneBrand")
    val phoneBrand: String = "Google Pixel 2XL",
    @SerializedName("phoneOS")
    val phoneOS: String = "Android 11",
    @SerializedName("timeZone")
    val timeZone: String = "Europe/Madrid",
    @SerializedName("token")
    val token: String? = null,
    @SerializedName("traceId")
    val traceId: String? = null,
    @SerializedName("userCountryCode")
    val userCountryCode: String? = "ES",
)


