package com.alwan.suitmediascreening.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _loginName = MutableLiveData<String>()
    val loginName: LiveData<String> = _loginName
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    fun setLoginName(name: String){
        _loginName.postValue(name)
    }

    fun setUserName(name: String){
        _userName.postValue(name)
    }
}