package com.xh.roomtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.LogUtils
import com.xh.roomtest.bean.Address
import com.xh.roomtest.bean.Book
import com.xh.roomtest.bean.User
import com.xh.roomtest.database.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userDao = AppDatabase.getInstance(this).userDao()
        val bookDao = AppDatabase.getInstance(this).bookDao()

        userDao.getAllUser().observe(this, Observer {
            it.forEach {
                LogUtils.e(it.userId, it, it.address)
            }
        })
        var num = 20
        var bookNum = 0
        tv_add.setOnClickListener {
            val user = User("new name", "${num++}")
            user.userId = num.toLong()
            user.address = Address("cd", "detail${num}")

//            user.books?.add(Address("cd", "地址${num}"))
//            user.books?.add(Address("cd", "地址${num}"))
//            user.books?.add(Address("cd", "地址${num}"))
            userDao.addUser(user)
            val book = Book("name${bookNum++}", user.userId!!)
            val book1 = Book("name${bookNum++}", user.userId!!)
            val book2 = Book("name${bookNum++}", user.userId!!)
            bookDao.add(book)
            bookDao.add(book1)
            bookDao.add(book2)

            LogUtils.e(user.userId, user, user.address)
        }
        tv_remove.setOnClickListener {
            userDao.deleteUserById((num--).toLong())
        }


        //update数据
//        userDao.deleteUserById(2)
//        val updateUser = userDao.getUserById(2)
//        LogUtils.e("room", "==update==${updateUser.userId},${updateUser.userName},${updateUser.userPhone}")

        //Delete数据
//        val row = userDao.deleteUserById(10)
//        LogUtils.e("room", "删除了 $row 行")
    }
}