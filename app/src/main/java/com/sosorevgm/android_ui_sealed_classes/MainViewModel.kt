package com.sosorevgm.android_ui_sealed_classes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository: MainRepository = MainRepositoryImpl()
    private val defaultError = "Something went wrong. Please try again later."
    val screenState = MutableLiveData<MainScreenState>()

    fun fetchData() {
        if (screenState.value is MainScreenState.Success) return
        screenState.value = MainScreenState.Loading
        viewModelScope.launch {
            delay(1000)
            try {
                screenState.value = MainScreenState.Success(repository.getData())
            } catch (e: Exception) {
                screenState.value = MainScreenState.Error(e.message ?: defaultError)
            }
        }
    }
}