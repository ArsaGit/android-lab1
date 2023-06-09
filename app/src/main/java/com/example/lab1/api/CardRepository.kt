package com.example.lab1.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.lab1.model.SealedCard

class CardRepository {
    private val apiService = RetrofitManager.apiService

    val cardsLiveDataSuccess = MutableLiveData<MutableList<SealedCard>>()
    val cardsLiveDataFailure = MutableLiveData<Boolean>()

    suspend fun getCards() {
        try {
            val response = apiService.getCards()

            if (response.isSuccessful) {
                cardsLiveDataSuccess.postValue(response.body() as MutableList<SealedCard>)
            }
            else{
                cardsLiveDataFailure.postValue(true)
            }
        } catch (e: java.lang.Exception) {
            Log.e(CardRepository::class.java.simpleName, e.message.toString())
        }
    }
}