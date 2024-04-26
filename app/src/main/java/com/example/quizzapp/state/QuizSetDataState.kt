package com.example.quizzapp.state

import com.example.quizzapp.models.QuizSet

sealed class QuizSetDataState{
    object Loading: QuizSetDataState()
    class Failure(val msg: Throwable): QuizSetDataState()
    class Success(val data: List<QuizSet>): QuizSetDataState()
    object Empty: QuizSetDataState()
}
