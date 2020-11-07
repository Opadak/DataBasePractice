package com.example.sharedpreference

import io.realm.RealmObject


open class School : RealmObject() {  //open 을 해줘야 한다!!!!   **********
    var name : String? = null
    var location : String? = null
}