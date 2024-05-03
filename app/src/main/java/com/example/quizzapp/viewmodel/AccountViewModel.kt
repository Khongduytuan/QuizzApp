package com.example.quizzapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quizzapp.database.repository.AccountRepository
import com.example.quizzapp.models.Account
import com.example.quizzapp.state.LoginAccountDataState
import com.example.quizzapp.state.RegisterAccountDataState
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AccountViewModel(application: Application) : ViewModel() {
    private val accountRepository: AccountRepository = AccountRepository(application)

    // State của data account khi đăng ký
    private val accountRegisterStateFlow: MutableStateFlow<RegisterAccountDataState> =
        MutableStateFlow(RegisterAccountDataState.Loading)
    val _accountRegisterStateFlow: StateFlow<RegisterAccountDataState> = accountRegisterStateFlow

    // State của data account khi đăng nhập
    private val accountLoginStateFlow: MutableStateFlow<LoginAccountDataState> =
        MutableStateFlow(LoginAccountDataState.Loading)
    val _accountLoginStateFlow: StateFlow<LoginAccountDataState> = accountLoginStateFlow


    fun insertAccount(account: Account) =
        viewModelScope.async {
            try {
                accountRegisterStateFlow.value = RegisterAccountDataState.Loading
                accountRepository.insertAccount(account)
                accountRegisterStateFlow.value = RegisterAccountDataState.Success(account)
            } catch (e: Exception) {
                accountRegisterStateFlow.value = RegisterAccountDataState.Failure(e)
            }
        }


    fun updateAccount(account: Account) =
        viewModelScope.launch { accountRepository.updateAccount(account) }

    fun deleteAccount(account: Account) =
        viewModelScope.launch { accountRepository.deleteAccount(account) }


    // Live data
    fun getAllAccount(): Flow<List<Account>> = accountRepository.getAllAccount()


    // Live data
//    fun getAllAccountWithUsernameAndPassword(username: String, password: String): Flow<Account> =
//        accountRepository.getAllAccountWithUsernameAndPassword(username, password)

    // State Flow
    fun getAllAccountWithUsernameAndPassword(username: String, password: String) =
        viewModelScope.launch {
            accountLoginStateFlow.value = LoginAccountDataState.Loading
            accountRepository.getAllAccountWithUsernameAndPassword(username, password)
                .catch { e ->
                    accountLoginStateFlow.value = LoginAccountDataState.Failure(e)
                }.collect { data ->
                    accountLoginStateFlow.value = LoginAccountDataState.Success(data)
                }
        }


    class AccountViewModelFactory(private val application: Application) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AccountViewModel(application) as T
            }
            throw IllegalArgumentException("Unable construct viewModel")
        }

    }
}