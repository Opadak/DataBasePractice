package com.example.sharedpreference

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import java.lang.Exception

class AsyncActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)
    }
}

class BackgroundAsyncTask(
    val progressbar : ProgressBar ,
    val progressText : TextView
) : AsyncTask<Int, Int , Int>() {
    //params -> doInBackground에서 사용할 타입
    //progress -> onProgressUpdate 에서 사용할 타입
    //result -> onPostExecute 에서 사용할 타입
    var percent: Int = 0
    override fun onPreExecute() {
        percent = 0
        progressbar.setProgress(percent)
    }
    override fun doInBackground(vararg params: Int?): Int {
        //vararg => Int가 여러개 올 수 있다.
        while (isCancelled() == false) //내작업이 실행이 되었는지 호출하는 것.
        {    percent++
        if (percent > 100) {
            break
        } else {
            publishProgress(percent)
        }
            try{ //시간을 연장하는 방법
            Thread.sleep(100)
        }catch (e: Exception) {
            e.printStackTrace() //왜 오류가 나는지 찍어보는 것.
        }
    }
        return percent
    }

    override fun onProgressUpdate(vararg values: Int?) { //publishProgress(percent)에서 percent에 1이 들어오면 values에 1이 들어가는 것!
        progressbar.setProgress(values[0] ?: 0)
        progressText.setText("퍼센트 : "+ values[0])
        super.onProgressUpdate(*values)
    }


    override fun onPostExecute(result: Int?) {
        progressText.setText("작업이 완료되었습니다.")
    }

    override fun onCancelled() {
        progressbar.setProgress(0)
        progressText.setText("작업이 취소되었습니다.")
    }
}