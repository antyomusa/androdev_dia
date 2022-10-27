package com.anthonyyoab.androdev.data.remote

import com.anthonyyoab.androdev.data.service.AuthenticationService
import com.anthonyyoab.androdev.ui.model.VerifyOtpResponse
import io.reactivex.Single
import javax.inject.Inject

class AuthenticationRemoteDataSource @Inject constructor(
private val authenticationService: AuthenticationService){
    fun verifyOtp(phone: String, otp: String): Single<VerifyOtpResponse>
    = authenticationService.verifyOtp(phone, otp)
}