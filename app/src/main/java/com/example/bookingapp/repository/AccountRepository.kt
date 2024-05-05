package com.example.bookingapp.repository

import android.content.Context
import android.util.Log
import com.example.bookingapp.models.Account
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.Gson

class AccountRepository(context: Context) {
    private val masterKey = MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
    private val prefs = EncryptedSharedPreferences.create(
        context,
        "account",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveAccount(account: Account, token: String) {
        with(prefs.edit()) {
            putString("account", Gson().toJson(account))
            putString("token", token)
            apply()
        }
    }

    fun loadAccount(): Pair<Account?, String?> {
        val token = prefs.getString("token", null)
        val accountJson = prefs.getString("account", null)
        val account = accountJson?.let{ Gson().fromJson(it, Account::class.java) }
        return account to token
    }

    fun clearAccount() {
        with(prefs.edit()) {
            remove("token")
            apply()
        }
    }

    fun getCredentials(): Pair<String, String> {
        val accountJson = prefs.getString("account", null)
        val account = accountJson.let{ Gson().fromJson(it, Account::class.java) }
        Log.d("GetCredentials", "Account: ${account.username}, ${account.password}")
        if (account == null) {
            Log.e("AccountRepository", "Account is null")
            return "" to ""
        }
        return account.username to account.password
    }
}