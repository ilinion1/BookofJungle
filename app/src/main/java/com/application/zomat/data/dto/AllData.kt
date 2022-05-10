package com.application.zomat.data.dto

import com.google.gson.annotations.SerializedName

data class AllData(
    @SerializedName("googleId") var googleId: String? = null,
    @SerializedName("appsFlyerUserId") var appsFlyerUserId: String? = null,
    @SerializedName("deepLink") var deepLink: String? = null,
    @SerializedName("devTmz") var devTmz: String? = null,
    @SerializedName("adb") var adb: Boolean? = null,
    @SerializedName("apps_flyer_params") var appsFlyerParams: Map<String, Any>? = null
)
