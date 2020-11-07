package com.example.sharedpreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_realm.*

class RealmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm)



         //관계를 형성 (table과 table 과의 관계)
        Realm.init(this@RealmActivity)
        // = 초기화 한다 = 내가 사용을 해야 한다.
        // "이런 방식으로 초기화를 해줘!! = config
        val config : RealmConfiguration = RealmConfiguration.Builder() //메소드 체이닝 = .뭐.뭐 >> 이어가는 것.
            .deleteRealmIfMigrationNeeded()  //Migration => 데이터 베이스를 동기화 시켜주겠다. (제일 중요)
            //데이터베이스의 틀에 변경이 생기게 되면 이미 저장되어 있는 내용들을 지우겠다는 옵션이다.
            .build()
        Realm.setDefaultConfiguration(config)
        val realm = Realm.getDefaultInstance() //realm을 얻는 방법


        button_save.setOnClickListener {
            realm.executeTransaction {
               //Transaction = > 작업단위를 묶는 것!  {}안에 있는 작업들을 하나의 작업단위로 보겠다. 이 말이다.
                with(it.createObject(School::class.java)) {
                    this.name = "어떤 대학교"
                    this.location = "서울"
                } //데이터를 삽입, 생성
            }
        }
        button_load.setOnClickListener {
            realm.executeTransaction {
                val data = it.where(School::class.java).findFirst()
                Log.d("data","data : "+ data)
            }
        }
        button_delete.setOnClickListener {
            realm.executeTransaction {
                it.where(School::class.java).findAll().deleteAllFromRealm()
               // it.where(School::class.java).findFirst().deleteFromRealm()

            }
        }

    }

}


