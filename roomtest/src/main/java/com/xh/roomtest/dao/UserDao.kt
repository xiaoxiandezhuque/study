package com.xh.roomtest.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.xh.roomtest.bean.User

@Dao
interface UserDao {

    @Query("select * from user")
    fun getAllUser(): LiveData<List<User>>

    @Query("select * from user where userId = :id")
    fun getUserById(id: Long): User

    //REPLACE、ABORT和IGNORE。如果不指定则默认为ABORT终止插入数据。这里我们将其指定为REPLACE替换原有数据。
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Delete
    fun deleteUserByUser(user: User)

    @Update
    fun updateUserByUser(user: User)


    @Query("delete  from user where userId = :id ")
    fun deleteUserById(id: Long)

    @Query("update  user set user_name = :updateName where userID =  :id")
    fun update(id: Long, updateName: String)
}