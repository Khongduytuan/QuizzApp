package com.example.quizzapp.state

import com.example.quizzapp.models.Account


sealed class RegisterAccountDataState{
    data object Loading: RegisterAccountDataState()
    class Failure(val msg: Throwable): RegisterAccountDataState()
    class Success(val data: Account): RegisterAccountDataState()


}