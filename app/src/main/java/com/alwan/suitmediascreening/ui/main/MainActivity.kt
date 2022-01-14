package com.alwan.suitmediascreening.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alwan.suitmediascreening.R
import com.alwan.suitmediascreening.databinding.ActivityMainBinding
import com.alwan.suitmediascreening.ui.user.UserActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val userContract = registerForActivityResult(UserActivity.Contract()) {
        mainViewModel.setUserName(it.result)
    }
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarMain.tvTitleToolbar.text = getString(R.string.second_screen)
        binding.btnChooseMain.setOnClickListener(this)
        binding.toolbarMain.imgBack.setOnClickListener(this)
        setupViewModel()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.loginName.observe(this, {
            binding.tvUsernameMain.text = it
        })

        intent.getStringExtra(LOGIN_NAME)?.let { mainViewModel.setLoginName(it) }

        mainViewModel.userName.observe(this, {
            binding.tvSelectedUsernameMain.text = it
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnChooseMain -> {
                userContract.launch(binding.tvSelectedUsernameMain.text.toString())
            }
            binding.toolbarMain.imgBack -> {
                onBackPressed()
            }
        }
    }

    companion object {
        const val LOGIN_NAME = "loginName"
    }
}