package com.media.profilecheck

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.media.profilecheck.api.ApiParam.USER_DATA
import com.media.profilecheck.databinding.UserDetailsBinding
import com.media.profilecheck.models.UserResponse
import com.media.profilecheck.models.UsersResult
import com.media.profilecheck.utils.loadImage
import java.util.*

class UserDetails : AppCompatActivity() {

    private lateinit var binding: UserDetailsBinding
    private var userResult: UsersResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindUiViews()
    }

    private fun bindUiViews() {

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        userResult.let {
            userResult = intent.getParcelableExtra(USER_DATA)
        }

        binding.ivDetailsImage.loadImage(userResult?.picture?.large)
        binding.tvDetailsName.text = "${userResult?.name?.title} ${userResult?.name?.first} ${userResult?.name?.last}"
        binding.tvDetailsEmail.text = userResult?.email
        binding.tvGender.text = userResult?.gender?.substring(0, 1)?.uppercase(Locale.ROOT) + userResult?.gender?.substring(1)?.lowercase()
        binding.tvPhoneNumber.text = userResult?.phone
        binding.tvDob.text = userResult?.dob?.date?.substring(0,10)

    }


}