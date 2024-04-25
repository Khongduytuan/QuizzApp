package com.example.quizzapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quizzapp.database.repository.QuizSetRepository
import com.example.quizzapp.models.QuizSet
import kotlinx.coroutines.launch


class QuizSetViewModel(application: Application) : ViewModel() {
    private val quizSetRepository: QuizSetRepository = QuizSetRepository(application)
    fun insertQuizSet(quizSet: QuizSet) =
        viewModelScope.launch { quizSetRepository.insertQuizSet(quizSet) }

    fun updateQuizSet(quizSet: QuizSet) =
        viewModelScope.launch { quizSetRepository.updateQuizSet(quizSet) }

    fun deleteQuizSet(quizSet: QuizSet) =
        viewModelScope.launch { quizSetRepository.deleteQuizSet(quizSet) }


    fun getAllQuizSet():LiveData<List<QuizSet>> = quizSetRepository.getAllQuizSet()


    class QuizSetViewModelFactory(private val application: Application) : ViewModelProvider.Factory{

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(QuizSetViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return QuizSetViewModel(application) as T
            }
            throw IllegalArgumentException("Unable construct viewModel")
        }

    }

}