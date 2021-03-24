package com.example.adda24.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.adda24.R
import com.example.adda24.common.Resource
import com.example.adda24.data.api.repository.MainRepository
import com.example.adda24.ui.main.view.activity.UsersActivity
import kotlinx.coroutines.Dispatchers

class UsersViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getUsers(usersActivity: UsersActivity) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = mainRepository.getUsers(
                        )
                    )
                )
            } catch (exception: Exception) {
                emit(
                    Resource.error(
                        data = null,
                        message = exception.message
                            ?: usersActivity.resources.getString(R.string.error_occurred),
                        exception = exception
                    )
                )
            }
        }
}