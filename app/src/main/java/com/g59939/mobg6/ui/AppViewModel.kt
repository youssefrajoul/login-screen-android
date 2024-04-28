package com.g59939.mobg6.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g59939.mobg6.network.TokenResponse
import com.g59939.mobg6.network.UserResponse
import com.g59939.mobg6.network.UserService
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AppViewModel : ViewModel() {

    var email by mutableStateOf(value = "")
    var password by mutableStateOf(value = "")
    var show_bottom_bar by mutableStateOf(value = true)
    var tokenResponse: TokenResponse = TokenResponse()
    var homeButton by mutableStateOf(value = true)
    var aboutButton by mutableStateOf(value = true)

    /**
     * Checks the email address, and calls next screen if the email is valid
     */
    fun checkEmail(
        onOkButtonClicked: () -> Unit,
        errorLogin: (httpStatusCode: String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val user = UserResponse(email, password)
                tokenResponse = UserService.UserClient.userLogin(user)
                Log.i("MainViewModel", "Success Login, Token: ${tokenResponse.access_token}")
            } catch (httpException: HttpException) {
                when (httpException.code()) {
                    400 -> {errorLogin("Wrong email or password")}
                    401 -> {errorLogin("401")}
                    404 -> {errorLogin("404")}
                    500 -> {errorLogin("Server side error")}
                    else -> {}
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error Login: " + e.message)
                errorLogin("Check your Internet Connection")
            }
            if (checkToken()) {
                Log.d("MainViewModel", "check token success")
                onOkButtonClicked()
            }
        }
    }

    private fun checkToken(): Boolean {
        return if (tokenResponse.access_token.isEmpty()) {
            Log.d("MainViewModel", "access_token_empty")
            false
        } else {
            Log.d("MainViewModel", "access_token_not_empty")
            true
        }
    }
}