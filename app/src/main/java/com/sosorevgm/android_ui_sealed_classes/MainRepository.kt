package com.sosorevgm.android_ui_sealed_classes

interface MainRepository {
    suspend fun getData(): UserModel
}

class MainRepositoryImpl : MainRepository {

    private val api: MainApi = object : MainApi {
        override suspend fun getData(): UserModel {
            return UserModel("Mike", 25, "super_mike@gmail.com")
        }
    }

    override suspend fun getData(): UserModel {
        val random = (0..1).random()
        if (random == 0) {
            return api.getData()
        } else {
            throw Exception()
        }
    }
}