package com.fourio.twynapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class EnrollPersonData {
    @SerializedName("tcn")
    @Expose
    var tcn: String? = null

    @SerializedName("tot")
    @Expose
    var tot: String? = null

    @SerializedName("iag")
    @Expose
    var iag: String? = null

    @SerializedName("tod")
    @Expose
    var tod: String? = null

    @SerializedName("psBioId")
    @Expose
    var psBioId: String? = null

    @SerializedName("psBioName")
    @Expose
    var psBioName: String? = null

    @SerializedName("idn")
    @Expose
    var idn: String? = null

    @SerializedName("transactionDate")
    @Expose
    var transactionDate: String? = null

    @SerializedName("fingers")
    @Expose
    var fingers: List<FingerScanInfo>? = null

    @SerializedName("faces")
    @Expose
    var faces: List<FaceScanInfo>? = null

    @SerializedName("fullName")
    @Expose
    var fullName: String? = null

    @SerializedName("nickName")
    @Expose
    var nickName: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("mobileNumber")
    @Expose
    var mobileNumber: String? = null

    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("doB")
    @Expose
    var doB: String? = null
}