package com.example.quizzapp.database.repository


import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.quizzapp.database.AccountDatabase
import com.example.quizzapp.database.dao.AccountDao
import com.example.quizzapp.models.Account

class AccountRepository(app: Application) {
    private val accountDao: AccountDao
    init {
        val accountDatabase: AccountDatabase = AccountDatabase.getInstance(app)
        accountDao = accountDatabase.getAccountDao()
    }

    suspend fun insertAccount(account: Account) = accountDao.insertAccount(account)
    suspend fun updateAccount(account: Account) = accountDao.updateAccount(account)
    suspend fun deleteAccount(account: Account) = accountDao.deleteAccount(account)

    fun getAllAccount(): LiveData<List<Account>> = accountDao.getAllAccount()

    fun getAllAccountWithUsernameAndPassword(username: String, password: String): LiveData<Account> {
        Log.d("AccountRepository", "AccountRepository")
        return  accountDao.getAllAccountWithUsernameAndPassword(username, password)
    }

}