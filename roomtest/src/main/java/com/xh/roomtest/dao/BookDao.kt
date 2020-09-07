package com.xh.roomtest.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.xh.roomtest.bean.Book
import com.xh.roomtest.bean.User

@Dao
interface BookDao {

    //大小写不敏感
    @Query("select * from book where userId = :id")
    fun getBookById(id: Long): List<Book>

    @Insert
    fun add(book: Book)
}