package com.example.moviedb.ui.screen.login

import androidx.lifecycle.viewModelScope
import com.example.moviedb.data.remote.request.LoginRequest
import com.example.moviedb.data.repository.UserRepository
import com.example.moviedb.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private var dataUserName = ""
    private var dataPassword = ""
    val loginRequest = LoginRequest(dataUserName, dataPassword)

    fun setDataForm(dataUserName: String, dataPassword: String) {
        this.dataUserName = dataUserName
        this.dataPassword = dataPassword
    }

    fun loginUser() {
        viewModelScope.launch {
            try {
                userRepository.getLoginUser(loginRequest)
            } catch (e: Throwable) {
                onError(e)
            }
        }
    }

}