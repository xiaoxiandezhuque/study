package com.xh.roomtest.bean

import androidx.room.*

@Entity(
    tableName = "user",
    indices = [Index("userPhone")]
)
data class User(
    @ColumnInfo(name = "user_name") var userName: String,
    @ColumnInfo(defaultValue = "") var userPhone: String,
) {
    @PrimaryKey(autoGenerate = true)
    var userId: Long? = null

    @Embedded
    var address: Address? = null

    constructor() : this("", "")
}

data class Address(var city: String, var detail: String) {
    constructor() : this("", "")
}

@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["userId"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE //当主表删除的时候，字表也会删除
    )]
)
data class Book(var name: String, var userId: Long) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    constructor() : this("", 0L)
}