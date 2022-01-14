package com.alwan.suitmediascreening.ui.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alwan.suitmediascreening.data.remote.model.User
import com.alwan.suitmediascreening.data.remote.model.UserResponse
import com.alwan.suitmediascreening.network.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val PER_PAGE = 10

class UserViewModel : ViewModel() {
    private val _users = MutableLiveData<ArrayList<User>>()
    val users: LiveData<ArrayList<User>> = _users
    private val _totalPages = MutableLiveData<Int>()
    val totalPages: LiveData<Int> = _totalPages
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getUsers(page: Int) {
        _loading.postValue(true)
        val client = RetrofitConfig.apiInstance.getUsers(page, PER_PAGE)

        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    _users.postValue(response.body()?.data)
                    _totalPages.postValue(response.body()?.totalPages)
                }
                _loading.postValue(false)
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("UserViewModel", "getUsers onFailure : ${t.message}")
                _loading.postValue(false)
            }
        })
    }
}