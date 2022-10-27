package com.anthonyyoab.androdev.ui.model

import com.google.gson.annotations.SerializedName

data class VerifyOtpResponse(
    var code: Int,
    var status: String,
    @SerializedName("data")
    var tokenModel: TokenModel

)
