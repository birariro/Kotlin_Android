package com.example.kotlin_android

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

    var todos :LiveData<List<Todo>>
    var newTodo:String
    init{
        todos = getAll()
        newTodo = "";
    }
    fun getAll():LiveData<List<Todo>>{
        return db.TodoDAO().GetAll()
    }

    //suspend 는 해당 메소드는 코루틴 안에서만 실행하도록하게한다.
    //만약 코루틴 밖에서 사용하면 에러가 난다.
    fun insert(todo:String){
        viewModelScope.launch(Dispatchers.IO) {
            db.TodoDAO().Insert(Todo(todo))
        }

    }
}
