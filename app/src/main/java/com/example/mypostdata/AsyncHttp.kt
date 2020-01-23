package com.example.mypostdata

import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.io.OutputStream
import java.io.PrintStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import kotlin.properties.Delegates

public class AsyncHttp : AsyncTask<String, Int, Boolean>{
    //フィールド
    //HTTP通信用URL及び接続フラグ
    private var urlConection:HttpURLConnection by Delegates.notNull<HttpURLConnection>()
    private var flg:Boolean = false
    //POST用変数
    private var name:String = ""
    private var value:Double = 0.0

    //コンストラクタ
    public constructor(name:String, value: Double) {
        this.name = name
        this.value = value
    }

    //非同期通信用メソッド
    override fun doInBackground(vararg params: String?): Boolean {
        //通信先URL
        var urlInput:String = "http://10.206.0.18/upload/post.php"
        //非同期通信
        try {
            //HTTP通信用パラメータ
            var url:URL = URL(urlInput)
            urlConection = url.openConnection() as HttpURLConnection
            urlConection.requestMethod = "POST"
            urlConection.doInput = true

            //POST用パラメータ
            var postDataSample:String = "name=" + name + "&text=" + value
            //POST用パラメータ設定
            var out:OutputStream = urlConection.outputStream
            var print:PrintStream = PrintStream(out)
            //postデータを送信する準備
            print.print(postDataSample)
            print.close()
            //ここで送信
            urlConection.inputStream
            Log.d("HTTP",postDataSample)
            //完了したらflgをtrueへ
            flg = true

        }catch (e:MalformedURLException){
            e.printStackTrace()
        }catch (e:IOException){
            e.printStackTrace()
        }
        return flg
    }

}