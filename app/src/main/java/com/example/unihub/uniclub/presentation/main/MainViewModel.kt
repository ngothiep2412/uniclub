package com.example.unihub.uniclub.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.unihub.uniclub.util.AppPreferencesDataSource

class MainViewModel(
    private val appPreferencesDataSource: AppPreferencesDataSource
) : ViewModel() {
    fun getSession() : LiveData<String?> {
        return appPreferencesDataSource.getAuthToken().asLiveData()
    }
}