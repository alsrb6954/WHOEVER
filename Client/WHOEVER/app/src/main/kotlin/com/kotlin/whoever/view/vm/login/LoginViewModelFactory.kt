package com.kotlin.whoever.view.vm.login

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class LoginViewModelFactory:ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return LoginViewModel() as T
    }
}