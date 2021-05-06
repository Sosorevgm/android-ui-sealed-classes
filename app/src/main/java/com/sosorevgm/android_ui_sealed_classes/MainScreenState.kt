package com.sosorevgm.android_ui_sealed_classes

sealed class MainScreenState {
    data class Success(val data: UserModel) : MainScreenState()
    data class Error(val message: String) : MainScreenState()
    object Loading : MainScreenState()
}