package com.fourio.twynapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class FingerScanInfo {
    @SerializedName("imageData")
    @Expose
    var imageData: String? = null

    @SerializedName("position")
    @Expose
    var position: Int? = null

    @SerializedName("height")
    @Expose
    var height: Int? = null

    @SerializedName("width")
    @Expose
    var width: Int? = null

    @SerializedName("internalQuality")
    @Expose
    var internalQuality: Int? = null

    @SerializedName("isoquality")
    @Expose
    var isoquality: Int? = null
}