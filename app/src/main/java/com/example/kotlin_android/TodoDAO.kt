package com.example.kotlin_android

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TodoDAO {
    @Query("SELECT * FROM Todo")
    fun GetAll(): LiveData<List<Todo>>

    @Insert
    fun Insert(todo: Todo)

    @Update
    fun Update(todo: Todo)

    @Delete
    fun Delete(todo: Todo)
}