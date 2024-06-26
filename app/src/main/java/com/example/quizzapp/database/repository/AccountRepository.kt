package com.example.quizzapp.database.repository


import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData

import com.example.quizzapp.database.QuizSetDatabase
import com.example.quizzapp.database.dao.AccountDao
import com.example.quizzapp.models.Account
import kotlinx.coroutines.flow.Flow

class AccountRepository(app: Application) {
    private val accountDao: AccountDao
    init {
//        val accountDatabase: AccountDatabase = AccountDatabase.getInstance(app)
        val quizSetDatabase : QuizSetDatabase = QuizSetDatabase.getInstance(app)
        accountDao = quizSetDatabase.getAccountDao()
    }

    suspend fun insertAccount(account: Account) = accountDao.insertAccount(account)
    suspend fun updateAccount(account: Account) = accountDao.updateAccount(account)
    suspend fun deleteAccount(account: Account) = accountDao.deleteAccount(account)

    fun getAllAccount(): Flow<List<Account>> = accountDao.getAllAccount()

    fun getAllAccountWithUsernameAndPassword(username: String, password: String): Flow<Account> {
        Log.d("AccountRepository", "AccountRepository")
        return  accountDao.getAllAccountWithUsernameAndPassword(username, password)
    }

}