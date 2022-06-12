package com.example.ezetapassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.example.mynetworklibrary.ApiClient
import com.example.mynetworklibrary.CustomUi
import com.example.mynetworklibrary.NetworkServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class CustomUiPage : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var buttonClick: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_ui_page)
        buttonClick = findViewById(R.id.button_click)
        progressBar = findViewById(R.id.progress_bar)
        buttonClick.setOnClickListener {
            progressBar.isVisible = true
            buttonClick.isVisible = false
            networkCall()
        }
    }

    private fun networkCall() {
        ApiClient().retrofit.create(NetworkServiceInterface::class.java).fetchCustomUI()
            .enqueue(object :
                Callback<CustomUi> {
                override fun onResponse(call: Call<CustomUi>, response: Response<CustomUi>) {
                    Timber.d("onResponse ${response.code()}")
                    if (response.code() == 200) {
                        launchCustomUiPage(response.body())
                    }
                }

                override fun onFailure(call: Call<CustomUi>, t: Throwable) {
                    Timber.d("onResponse ${t.stackTrace}")
                    buttonClick.isVisible= false
                }
            })
    }

    private fun launchCustomUiPage(body: CustomUi?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("CUSTOM_UI",body)
        startActivity(intent)
        finish()
        progressBar.isVisible = false

    }

}