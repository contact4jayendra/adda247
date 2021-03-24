package com.example.adda24.ui.main.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adda24.R
import com.example.adda24.common.Constants
import com.example.adda24.data.api.apis.ApiHelper
import com.example.adda24.data.api.apis.RetrofitBuilder
import com.example.adda24.data.api.model.Data
import com.example.adda24.data.api.model.Response
import com.example.adda24.data.api.model.Status
import com.example.adda24.ui.base.ViewModelFactory
import com.example.adda24.ui.main.adapter.UsersAdapter
import com.example.adda24.ui.main.viewmodel.UsersViewModel

class UsersActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var rv_users: RecyclerView
    private lateinit var tv_error: TextView
    private lateinit var pb_loader: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel() {
        usersViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(UsersViewModel::class.java)
    }


    @SuppressLint("SetTextI18n")
    private fun setupUI() {
        rv_users = findViewById(R.id.rv_users)
        tv_error = findViewById(R.id.tv_error)
        pb_loader = findViewById(R.id.pb_loader)

        val viewManager = LinearLayoutManager(this)
        usersAdapter = UsersAdapter(ArrayList<Data>(), this)

        rv_users.apply {
            setHasFixedSize(false)
            isNestedScrollingEnabled = true
            layoutManager = viewManager
            adapter = usersAdapter
        }
    }


    fun setupObservers() {
        usersViewModel.getUsers(this).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { response ->
                            pb_loader.visibility = View.GONE
                            try {
                                if (response.code.toString() == Constants.SUCCESS_CODE) {
                                    responseHandler(response)
                                } else {
                                    showError(resources.getString(R.string.something_went_wrong))
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                    Status.ERROR -> {
                        pb_loader.visibility = View.GONE
                        showError(it.message ?: resources.getString(R.string.error_occurred))
                    }
                    Status.LOADING -> {
                        pb_loader.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun responseHandler(response: Response) {
        if (response.data != null
            && !response.data.isEmpty()
        ) {
            hideError()
            usersAdapter.refreshList(response.data)
        } else {
            showError(resources.getString(R.string.no_data_found))
        }
    }


    private fun showError(errorMsg: String) {
        rv_users.visibility = View.GONE
        tv_error.visibility = View.VISIBLE
        tv_error.text = errorMsg
    }

    private fun hideError() {
        rv_users.visibility = View.VISIBLE
        tv_error.visibility = View.GONE
    }

}