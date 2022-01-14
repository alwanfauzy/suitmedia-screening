package com.alwan.suitmediascreening.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.alwan.suitmediascreening.R
import com.alwan.suitmediascreening.databinding.ActivityLoginBinding
import com.alwan.suitmediascreening.ui.main.MainActivity
import com.alwan.suitmediascreening.util.isPalindrome

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheckLogin.setOnClickListener(this)
        binding.btnNextLogin.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnCheckLogin -> {
                val check = binding.editPalindromeLogin.text.toString()
                if (check.isBlank()) {
                    binding.editPalindromeLogin.error = getString(R.string.input_error)
                } else {
                    if(check.isPalindrome()){
                        showToast(getString(R.string.is_palindrome))
                    }else{
                        showToast(getString(R.string.not_palindrome))
                    }
                }
            }
            binding.btnNextLogin -> {
                val loginName = binding.editNameLogin.text.toString()
                if (loginName.isBlank()) {
                    binding.editNameLogin.error = getString(R.string.input_error)
                } else {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra(MainActivity.LOGIN_NAME, loginName)
                    startActivity(intent)
                }
            }
        }
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}