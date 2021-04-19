package com.example.retrofitapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.retrofitapp.utils.Constants.TAG
import com.example.retrofitapp.utils.SEARCH_TYPE
import com.example.retrofitapp.utils.onMyTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private var currentSearchType:SEARCH_TYPE = SEARCH_TYPE.PHOTO
    private val search_term_radio_group : RadioGroup by lazy {
        findViewById(R.id.search_term_radio_group)
    }
    private val photo_search_radio_btn : RadioGroup by lazy {
        findViewById(R.id.photo_search_radio_btn)
    }
    private val user_search_radio_btn : RadioGroup by lazy {
        findViewById(R.id.user_search_radio_btn)
    }
    private val search_term_text_layout : TextInputLayout by lazy {
        findViewById(R.id.search_term_text_layout)
    }
    private val search_term_edit_text : TextInputEditText by lazy {
        findViewById(R.id.search_term_edit_text)
    }
    private val frame_search_btn : FrameLayout by lazy {
        findViewById(R.id.frame_search_btn)
    }
    private val main_scrollview : ScrollView by lazy {
        findViewById(R.id.main_scrollview)
    }
    private val btn_search : Button by lazy {
        findViewById(R.id.btn_search)
    }
    private val btn_progress : ProgressBar by lazy {
        findViewById(R.id.btn_progress)
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: ")

        search_term_radio_group.setOnCheckedChangeListener{ _,checkedId ->
            when(checkedId){
                R.id.photo_search_radio_btn -> {
                    Log.d(TAG, "onCreate: 사진 선택")
                    search_term_text_layout.hint = "사진 검색"
                    search_term_text_layout.startIconDrawable = resources.getDrawable(R.drawable.ic_baseline_photo_library_24,resources.newTheme())
                    this.currentSearchType = SEARCH_TYPE.PHOTO
                }
                R.id.user_search_radio_btn ->{
                    Log.d(TAG, "onCreate: 유저 선택")
                    search_term_text_layout.hint = "사용자 검색"
                    search_term_text_layout.startIconDrawable = resources.getDrawable(R.drawable.ic_baseline_photo_library_24,resources.newTheme())
                    this.currentSearchType = SEARCH_TYPE.USER
                }
            }
            Log.d(TAG, "onCreate: currentSearchType : $currentSearchType")
        }
        //테스트가 변경이되었을때
        search_term_edit_text.onMyTextChanged {
            //글자 입력
            if(it.toString().count() >0){
                //검색 버튼을 보여줘라
                frame_search_btn.visibility = View.VISIBLE
                //스크롤뷰를 올린다.
                main_scrollview.scrollTo(0,400)
                //헬퍼 테스트 제거
                search_term_text_layout.helperText=""
            }
            else{
                frame_search_btn.visibility = View.INVISIBLE
                search_term_text_layout.helperText="검색어를 입력해 주세요"
            }
            if(it.toString().count()==12){
                Toast.makeText(this,"검색어는 12자 까지만 입력이 가능합니다.",Toast.LENGTH_SHORT).show()
            }
        }
        //버튼 클릭시
        btn_search.setOnClickListener {
            Log.d(TAG, "onCreate: 검색 버튼 클릭 / currentSearchType : $currentSearchType")
            handleSearchButtonUi()
        }


        search_term_edit_text.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }

        })
    }


    private fun handleSearchButtonUi(){
        btn_progress.visibility = View.VISIBLE
        btn_search.visibility = View.INVISIBLE

        Handler().postDelayed({
            btn_progress.visibility = View.INVISIBLE
            btn_search.visibility = View.VISIBLE
        },1500)
    }
}