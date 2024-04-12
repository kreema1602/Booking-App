package com.example.bookingapp

import com.example.bookingapp.models.Account

object CurrentAccount {
    private var account: Account? = null;

    fun getAccount(): Account? {
        return account
    }

    fun setAccount(account: Account) {
        CurrentAccount.account = account
    }

    fun clearAccount() {
        account = null
    }
}