package com.example.quizzapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quizzapp.database.repository.QuizSetRepository
import com.example.quizzapp.models.Question
import com.example.quizzapp.models.QuizSet
import com.example.quizzapp.models.relationship.QuizSetWithQuestion
import com.example.quizzapp.state.QuizSetDataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class QuizSetViewModel(application: Application) : ViewModel() {
    private val quizSetRepository: QuizSetRepository = QuizSetRepository(application)

    private val quizSetStateFlow: MutableStateFlow<QuizSetDataState> = MutableStateFlow(
        QuizSetDataState.Empty(
            emptyList()
        )
    )
    val _quizSetStateFlow: StateFlow<QuizSetDataState> = quizSetStateFlow
    fun insertQuizSet(quizSet: QuizSet) =
        viewModelScope.launch { quizSetRepository.insertQuizSet(quizSet) }

    fun updateQuizSet(quizSet: QuizSet) =
        viewModelScope.launch { quizSetRepository.updateQuizSet(quizSet) }

    fun deleteQuizSet(quizSet: QuizSet) =
        viewModelScope.launch { quizSetRepository.deleteQuizSet(quizSet) }


    // Livedata
    //fun getAllQuizSet():Flow<List<QuizSet>> = quizSetRepository.getAllQuizSet()
    // StateFlow
    fun getAllQuizSet() = viewModelScope.launch {
        quizSetStateFlow.value = QuizSetDataState.Loading
        quizSetRepository.getAllQuizSet()
            .catch { e ->
                quizSetStateFlow.value = QuizSetDataState.Failure(e)
            }.collect { data ->
                if (data.isEmpty()) {
                    quizSetStateFlow.value = QuizSetDataState.Empty(data)
                } else {
                    quizSetStateFlow.value = QuizSetDataState.Success(data)
                }
            }
    }


    // Live data của question
    fun getQuestionByIDQuizSet(idQuizSet: Int): Flow<List<QuizSetWithQuestion>> =
        quizSetRepository.getQuestionByIDQuizSet(idQuizSet)

    // Live data của question
    fun insertQuestion(question: Question) =
        viewModelScope.launch { quizSetRepository.insertQuestion(question) }


    class QuizSetViewModelFactory(private val application: Application) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(QuizSetViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return QuizSetViewModel(application) as T
            }
            throw IllegalArgumentException("Unable construct viewModel")
        }

    }

}