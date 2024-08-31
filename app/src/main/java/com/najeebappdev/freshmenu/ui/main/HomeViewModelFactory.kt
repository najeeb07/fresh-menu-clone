package com.najeebappdev.freshmenu.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.najeebappdev.freshmenu.data.source.remote.ApiClient
import com.najeebappdev.freshmenu.data.repository.HomeRepository

class HomeViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val apiService = ApiClient.apiService
        val repository = HomeRepository(apiService)
        return HomeViewModel(repository) as T
    }
}
