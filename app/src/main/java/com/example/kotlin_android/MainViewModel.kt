package com.example.kotlin_android

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room

class MainViewModel(application:Application) : AndroidViewModel(application) {


    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(application) as T
        }
    }

    private val db = Room.databaseBuilder(
            application,
            AppDatabase::class.java, "todo-db")
            .build()

    lateinit var todos :LiveData<List<Todo>>
    init{
        todos = getAll()
    }
    fun getAll():LiveData<List<Todo>>{
        return db.TodoDAO().GetAll()
    }

    //suspend 는 해당 메소드는 코루틴 안에서만 실행하도록하게한다.
    //만약 코루틴 밖에서 사용하면 에러가 난다.
    suspend fun insert(todo:Todo){
        db.TodoDAO().Insert(todo)
    }
}
