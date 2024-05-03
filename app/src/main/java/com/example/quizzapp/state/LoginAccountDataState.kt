package com.example.quizzapp.state

import com.example.quizzapp.models.Account

sealed class LoginAccountDataState{
    data object Loading: LoginAccountDataState()
    class Failure(val msg: Throwable): LoginAccountDataState()
    class Success(val data: Account): LoginAccountDataState()
}
