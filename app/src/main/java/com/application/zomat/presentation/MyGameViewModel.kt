package com.application.zomat.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyGameViewModel: ViewModel() {
    private var gameLevel = MutableLiveData("Easy")
    private val _gameLevel : LiveData<String> get() = gameLevel

    private var userName = MutableLiveData("User Name")
    private val _userName : LiveData<String> get() = userName

    private var gameResult = MutableLiveData<String>()
    private val _gameResult : LiveData<String> get() = gameResult

    fun setGameLevel(level: String){
        gameLevel.value = level
    }

    fun getGameLevel() : LiveData<String>{
        return _gameLevel
    }

    fun setUserName(name: String){
        userName.value = name
    }

    fun getUserName() : LiveData<String>{
        return _userName
    }

    fun setGameResult(result: String){
        gameResult.value = result
    }

    fun getGameResult() : LiveData<String>{
        return _gameResult
    }

}