package com.anthonyyoab.androdev.data.repository

import com.anthonyyoab.androdev.data.remote.AuthenticationRemoteDataSource
import com.anthonyyoab.androdev.data.service.AuthenticationService
import com.anthonyyoab.androdev.ui.model.VerifyOtpResponse
import io.reactivex.Single
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val authenticationRemoteDataSource: AuthenticationRemoteDataSource
){
    fun verifyOtp(phone: String, otp: String): Single<VerifyOtpResponse>
    = authenticationRemoteDataSource.verifyOtp(phone, otp)
}