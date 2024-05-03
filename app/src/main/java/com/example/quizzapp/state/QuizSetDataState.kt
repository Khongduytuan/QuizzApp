package com.example.quizzapp.state

import com.example.quizzapp.models.QuizSet

sealed class QuizSetDataState{
    data object Loading: QuizSetDataState()
    class Failure(val msg: Throwable): QuizSetDataState()
    class Success(val data: List<QuizSet>): QuizSetDataState()
    class Empty(val data: List<QuizSet>): QuizSetDataState()
}
