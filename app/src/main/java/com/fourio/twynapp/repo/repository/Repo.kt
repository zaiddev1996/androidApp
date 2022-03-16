package com.fourio.twynapp.repo.repository

import android.util.Log
import com.fourio.twynapp.model.EnrollPersonData
import com.fourio.twynapp.model.FaceScanInfo
import com.fourio.twynapp.model.FingerScanInfo
import com.fourio.twynapp.repo.networking.ApiServices
import com.fourio.twynapp.repo.networking.Networking
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Repo {
    suspend fun enrollPerson(
        nickName: String,
        phone: String,
        fingerScanInfoArray: ArrayList<FingerScanInfo>,
        faceScanInfoArray: ArrayList<FaceScanInfo>,
    ): Result<EnrollPersonData?, String, String> {


        val standardService: ApiServices? = Networking.getRetrofitInstance()
        val enrollPersonData = EnrollPersonData()
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.'Z'", Locale.getDefault())
        val formattedDate: String = sdf.format(Date())
        Log.e("DATE", formattedDate)
        enrollPersonData.tcn = UUID.randomUUID().toString()
        enrollPersonData.tot = "ENR"
        enrollPersonData.iag = "99"
        enrollPersonData.tod = "99"
        enrollPersonData.psBioId = "T4ISB"
        enrollPersonData.psBioName = "T4ISB"
        enrollPersonData.idn =
            "NNN/RBKSswT70VKGxsRauYdZUfh4Br8KNTCgQqdZW7acBwNyzyS5MfJWEzsudmYFQLvNCgAIMmRqWYpp9W93UQ=="
        enrollPersonData.transactionDate = formattedDate
        enrollPersonData.fullName = nickName
        enrollPersonData.nickName = nickName
        enrollPersonData.email = "nizar.elouaer@t4isb.com"
        enrollPersonData.mobileNumber = phone
        enrollPersonData.gender = "M"
        enrollPersonData.doB = "01-01-2017"
        enrollPersonData.fingers = fingerScanInfoArray
        enrollPersonData.faces = faceScanInfoArray


        return try {
            val response =
                standardService?.enrollPerson(
                    "application/json", "TW-EhEGPjryHoggGgyz7fMfL0Wed8T7SO1qP", "Twyn-App",
                    enrollPersonData
                )
            if (response != null) {
                if (response.code() == 202) {
                    Result.Success(response.body())
                } else {
                    Result.Failure("Error")
                }
            } else {
                Result.Failure("Error")
            }
        } catch (exception: IOException) {
//            Log.e("API RESPONSE", "Network Error")
            Result.NetworkError("Network Error")
        }

    }


    sealed class Result<out Success, out Failure, out NetworkFailure> {
        data class Success<out Success>(val value: Success) : Result<Success, Nothing, Nothing>()
        data class Failure<out Failure>(val reason: Failure) : Result<Nothing, Failure, Nothing>()
        data class NetworkError<out NetworkFailure>(val reason: NetworkFailure) :
            Result<Nothing, Nothing, NetworkFailure>()
    }

}