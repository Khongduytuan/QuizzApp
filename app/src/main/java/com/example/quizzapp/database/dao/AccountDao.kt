package com.example.quizzapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.quizzapp.models.Account
@Dao
interface AccountDao {
    @Insert
    suspend fun insertAccount(account: Account)
    @Update
    suspend fun updateAccount(account: Account)
    @Delete
    suspend fun deleteAccount(account: Account)

    @Query("select * from tbl_account")
    fun getAllAccount():LiveData<List<Account>>

    @Query("select * from tbl_account where username_col=:username AND password_col=:password ")
    fun getAllAccountWithUsernameAndPassword(username: String, password: String):LiveData<Account>


    //    @Query("select * from tbl_quizset where name_col=:name")
//    fun getAllQuizSet(name: String):LiveData<List<QuizSet>>
}