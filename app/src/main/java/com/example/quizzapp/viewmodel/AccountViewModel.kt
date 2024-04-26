package com.example.quizzapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quizzapp.database.repository.AccountRepository
import com.example.quizzapp.models.Account
import kotlinx.coroutines.launch

class AccountViewModel(application: Application):ViewModel() {
    private val accountRepository: AccountRepository = AccountRepository(application)

    fun insertAccount(account: Account) = viewModelScope.launch { accountRepository.insertAccount(account) }
    fun updateAccount(account: Account) = viewModelScope.launch { accountRepository.updateAccount(account) }
    fun deleteAccount(account: Account) = viewModelScope.launch { accountRepository.deleteAccount(account) }



    fun getAllAccount(): LiveData<List<Account>> = accountRepository.getAllAccount()

    fun getAllAccountWithUsernameAndPassword(username: String, password: String): LiveData<Account> = accountRepository.getAllAccountWithUsernameAndPassword(username, password)


    class AccountViewModelFactory(private val application: Application) : ViewModelProvider.Factory{

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AccountViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return AccountViewModel(application) as T
            }
            throw IllegalArgumentException("Unable construct viewModel")
        }

    }
}