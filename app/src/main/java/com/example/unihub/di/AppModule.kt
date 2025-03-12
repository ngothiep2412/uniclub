package com.example.unihub.di

import com.example.unihub.uniclub.presentation.authen.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
    viewModelOf(::LoginViewModel)
}

