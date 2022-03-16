package com.fourio.twynapp.repo.networking

import com.fourio.twynapp.model.EnrollPersonData
import com.fourio.twynapp.model.FaceScanInfo
import com.fourio.twynapp.model.FingerScanInfo
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {

//    @FormUrlEncoded
    @POST("nisttransactions")
    suspend fun enrollPerson(@Header("Content-Type") contentType: String,
        @Header("X-API-KEY") xApiKey: String,
        @Header("X-APP-ID") xAppId: String,
        @Body enrollPersonData: EnrollPersonData
    ): Response<EnrollPersonData>?

//    @FormUrlEncoded
//    @POST("nisttransactions")
//    suspend fun enrollPerson(
//        @Header("Content-Type") contentType: String,
//        @Header("X-API-KEY") xApiKey: String,
//        @Header("X-APP-ID") xAppId: String,
//        @Field("tcn") tcn: String,
//        @Field("tot") tot: String,
//        @Field("iag") iag: String,
//        @Field("tod") tod: String,
//        @Field("psBioId") psBioId: String,
//        @Field("psBioName") psBioName: String,
//        @Field("idn") idn: String,
//        @Field("transactionDate") transactionDate: String,
//        @Field("fullName") fullName: String,
//        @Field("nickName") nickName: String,
//        @Field("email") email: String,
//        @Field("mobileNumber") mobileNumber: String,
//        @Field("gender") gender: String,
//        @Field("doB") doB: String,
//        @Field("fingers[]") fingers: Array<FingerScanInfo>,
//        @Field("faces[]") faces: Array<FaceScanInfo>,
//    ): Response<String>?

}