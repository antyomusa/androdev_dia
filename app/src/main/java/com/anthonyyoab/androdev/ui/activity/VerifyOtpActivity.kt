package com.anthonyyoab.androdev.ui.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.anthonyyoab.androdev.R
import com.anthonyyoab.androdev.databinding.ActivityVerifyOtpBinding
import com.anthonyyoab.androdev.ui.viewmodel.VerifyOtpViewModel
import com.anthonyyoab.androdev.utils.ResponseStatus
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(Build.VERSION_CODES.M)
@AndroidEntryPoint
class VerifyOtpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVerifyOtpBinding
    private val viewModel: VerifyOtpViewModel by viewModels()
    private var otp: String = ""
    private var phone: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVerify.setOnClickListener {
            binding.tvStatus.visibility = View.GONE

            phone = binding.etPhone.text.toString()
            otp = binding.etOtp.text.toString()

            viewModel.validateData(phone, otp)
        }
        setObserver()
    }

    private fun setObserver() {
        viewModel.getVerifyOtpResponseLiveData().observe(this, Observer {
            when (it.status){
                ResponseStatus.SUCCESS -> {
                    binding.tvStatus.text = "OTP Valid"
                    binding.tvStatus.setTextColor(getColor(R.color.green))
                    binding.tvStatus.visibility = View.VISIBLE
                }
                ResponseStatus.ERROR -> {
                    binding.tvStatus.text = it.message
                    binding.tvStatus.setTextColor(getColor(R.color.red))
                    binding.tvStatus.visibility = View.VISIBLE
                }
                ResponseStatus.LOADING -> {
                    binding.tvStatus.visibility = View.GONE
                }
                ResponseStatus.EMPTY -> {
                    //do somehting if data empty
                }
            }
        })

        viewModel.getValidationLiveData().observe(this, Observer {
            when(it.status){
                ResponseStatus.SUCCESS -> {
                    viewModel.verifyOtp(phone, otp)
                }
                ResponseStatus.ERROR -> {
                    binding.tvStatus.text = it.message
                    binding.tvStatus.setTextColor(getColor(R.color.red))
                    binding.tvStatus.visibility = View.VISIBLE
                }
                else -> {
                    binding.tvStatus.text = "Else"
                    binding.tvStatus.setTextColor(getColor(R.color.teal_200))
                    binding.tvStatus.visibility = View.VISIBLE
                }
            }
        })
    }
}