package com.havebreak.nubank_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.havebreak.nubankapi.BackendClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val BASE_URL = "https://catfact.ninja/"

class SampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        GlobalScope.launch {
            val api: Api = BackendClient().invoke(Api::class.java, BASE_URL)
            val a = api.getEntries()
            print(a)
        }
    }
}