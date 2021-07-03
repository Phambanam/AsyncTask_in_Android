package com.phamnam.asynctask

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isRunning = false
    private val maxSec = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDownload.setOnClickListener{
            tvPercent.visibility = View.VISIBLE
            progressBar.visibility =View.VISIBLE
         val downLoadData = DownLoadData()
            downLoadData.execute()

        }
        btnComment.setOnClickListener{
            etComment.visibility = View.GONE
            btnComment.visibility = View.GONE
            tvComment.visibility = View.GONE
        }
    }
    private inner class DownLoadData : AsyncTask<Void,Int,String>(){

        override fun doInBackground(vararg params: Void?): String{
           for (i in 0..maxSec){
               //thuc hien chay nen
               SystemClock.sleep(100)
               //gui du lieu cho progress
               publishProgress(i)
           }
            return ""
        }


        //ham dung de update UI
        @SuppressLint("SetTextI18n")
        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            val number = values[0]
            progressBar.progress = number!!
            tvPercent.text = "$number %"
        }

        //ham chay khi synctask bat dau
        override fun onPreExecute() {
            super.onPreExecute()
            Toast.makeText(this@MainActivity,"start",Toast.LENGTH_SHORT).show()
        }


        //ham chay khi syncTask ket thuc
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Toast.makeText(this@MainActivity,"DownLoaded",Toast.LENGTH_SHORT).show()
        }

    }
}