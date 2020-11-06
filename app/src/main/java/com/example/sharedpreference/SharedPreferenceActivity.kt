package com.example.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_shared_preference.*

class SharedPreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)


        //SharedPreference 를 불러오는 방법
        val sharedPreference = getSharedPreferences("sp1", Context.MODE_PRIVATE) //name = 각각의 구분
        //Mode
        // 1. MODE_PRIVATE = 생성한 어플리케이션에서만 사용 가능*
        // 2. MODE_WORLD_READABLE = 다른 어플리케이션에서 사용 가능 => 읽기만 가능
        // 3. MODE_WORLD_WRITABLE = 다른 어플리케이션에서 사용 가능 => 기록도 가능
        // 4. MODE_MULTI_PROCESS = 이미 호출되어 사용중인지 체크
        // 5. MODE_APPEND = 기존 preference에 신규로 추가

        //데이터를 넣어줄 수 있는 것 = editer (sharedpreference에서 가지고 온다)
        val editor = sharedPreference.edit()
        editor.putString("hello", "안녕하세요")
        editor.commit() //이 구문을 꼭 해야 데이터의 삽입이 완료가 된다 *********

        button.setOnClickListener {
            //sharedpreference에서 데이터를 불러오는 방법
            val sharedPreferences = getSharedPreferences("sp1", Context.MODE_PRIVATE)
            val value = sharedPreference.getString("hello", "데이터 없음")
            Log.d("key-value", "Value : "+ value)
        }
    }
}
//우리가 데이터베이스를 사용하는 이유는 한번 저장된 것이 계속 저장되어있게끔 하는 것이라
//데이터를 삽입하는 코드를 작성하고 지워도 남아있다.
// 지우고 싶으면? -> ADV 앱 설정 들어가서  Storage를 클릭 후 CLEAR DATA와 CLEAR CACH를 해주면 된다.
