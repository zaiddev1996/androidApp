package com.fourio.twynapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class EnrollTransactionResponse {
    @SerializedName("workflowInstanceId")
    @Expose
    var workflowInstanceId: String? = null
}