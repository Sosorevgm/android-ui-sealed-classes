package com.sosorevgm.android_ui_sealed_classes

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var successLayout: View
    private lateinit var tvUserName: TextView
    private lateinit var tvUserAge: TextView
    private lateinit var tvUserEmail: TextView
    private lateinit var errorLayout: View
    private lateinit var tvError: TextView
    private lateinit var btnRetry: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.screenState.observe(this) {
            when (it) {
                is MainScreenState.Success -> {
                    successLayout.visibility = View.VISIBLE
                    errorLayout.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    tvUserName.text = it.data.name
                    tvUserAge.text = it.data.age.toString()
                    tvUserEmail.text = it.data.email
                }
                is MainScreenState.Error -> {
                    successLayout.visibility = View.GONE
                    errorLayout.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    tvError.text = it.message
                }
                is MainScreenState.Loading -> {
                    successLayout.visibility = View.GONE
                    errorLayout.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }
            }
        }
        viewModel.fetchData()
    }

    private fun initView() {
        successLayout = findViewById(R.id.success_layout)
        tvUserName = findViewById(R.id.tv_user_name)
        tvUserAge = findViewById(R.id.tv_user_age)
        tvUserEmail = findViewById(R.id.tv_user_email)
        errorLayout = findViewById(R.id.error_layout)
        tvError = findViewById(R.id.tv_error)
        btnRetry = findViewById(R.id.btn_retry)
        progressBar = findViewById(R.id.progress_bar)

        btnRetry.setOnClickListener {
            viewModel.fetchData()
        }
    }
}