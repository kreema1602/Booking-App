package com.example.bookingapp.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookingapp.models.Account
import com.example.bookingapp.repository.AccountRepository
import com.example.bookingapp.services.AccountService
import com.example.bookingapp.services.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AccountRepository) : ViewModel() {
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated get() = _isAuthenticated.asStateFlow()

    private val _authToken = MutableStateFlow("")
    val authToken get() = _authToken.asStateFlow()
    private val _account = MutableStateFlow<Account?>(Account())
    val account get() = _account.asStateFlow()
    private val _loginStatus = MutableStateFlow<String>("")
    val loginStatus get() = _loginStatus.asStateFlow()

    private val _validOTP = MutableStateFlow<Boolean?>(null)
    val validOTP get() = _validOTP.asStateFlow()

    private val _isForgot = MutableStateFlow<Boolean?>(null)
    val isForgot get() = _isForgot.asStateFlow()

    private val _isReset = MutableStateFlow<Boolean?>(null)
    val isReset get() = _isReset.asStateFlow()

    init {
        loadAccount()
    }

    private fun loadAccount() {
        val (account, token) = repository.loadAccount()

        if (!token.isNullOrEmpty() && account != null) {
            _authToken.value = token
            _account.value = account
            _isAuthenticated.value = true
            RetrofitClient.setAuthToken(token)
        }
    }

    fun logout() {
        viewModelScope.launch {
            _isAuthenticated.value = false
            _authToken.value = ""
            _account.value = Account()

            clearAccount()
            RetrofitClient.clearAuthToken()
            _loginStatus.emit("Logged out")
        }
    }

    fun login(email: String, password: String, isBio: Boolean): Boolean {
        viewModelScope.launch {
            try {
                Log.d("AuthViewModel", "login: $email, $password")
                val result = AccountService.login(email, password, isBio)
                _account.value = result.first
                _authToken.value = result.second
                _isAuthenticated.value = true
                saveAccount(result.first, result.second)
                RetrofitClient.setAuthToken(result.second)
                _loginStatus.emit("Login successful")
            } catch (e: Exception) {
                _isAuthenticated.value = false
                _loginStatus.emit("Login error: ${e.message}")
            }
        }
        return isAuthenticated.value
    }

    private fun saveAccount(account: Account, token: String) {
        repository.saveAccount(account, token)
    }

    private fun clearAccount() {
        repository.clearAccount()
    }

    fun register(fields: Map<String, String>, role: String): Boolean {
        viewModelScope.launch {
            try {
                _isAuthenticated.value = AccountService.register(fields, role)
            } catch (e: Exception) {
                throw Exception("${e.message}")
            }
        }
        return isAuthenticated.value
    }

    fun getCredentials(): Pair<String, String> {
        return repository.getCredentials()
    }

    fun verifyOTP(username: String, otp: String): Boolean? {
        viewModelScope.launch {

            try {
                _validOTP.value = AccountService.verifyOTP(username, otp)
            } catch (e: Exception) {
                throw Exception("${e.message}")
            }
        }
        return _validOTP.value
    }

    fun forgotPassword(username: String): Boolean? {
        viewModelScope.launch {

            try {
                _isForgot.value = AccountService.forgotPassword(username)
            } catch (e: Exception) {
                throw Exception("${e.message}")
            }
        }
        return _isForgot.value
    }

    fun resetPassword(username: String, password: String): Boolean? {
        viewModelScope.launch {

            try {
                _isReset.value = AccountService.resetPassword(username, password)
            } catch (e: Exception) {
                throw Exception("${e.message}")
            }
        }
        return _isReset.value
    }
}