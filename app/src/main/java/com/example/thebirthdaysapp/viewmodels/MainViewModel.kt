package com.example.thebirthdaysapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thebirthdaysapp.helpers.Resource
import com.example.thebirthdaysapp.repository.BirthdaysDataRepository
import kotlinx.coroutines.launch
import com.example.thebirthdaysapp.api.Result

class MainViewModel : ViewModel() {

    private val repository = BirthdaysDataRepository()

    private var originalBirthdayResultsFetched: List<Result>? = null

    private val _birthdayResultsResource = MutableLiveData<Resource<List<Result>>>()
    val birthdayResultsResource: LiveData<Resource<List<Result>>> = _birthdayResultsResource

    fun getBirthdaysAndOtherData() {

        viewModelScope.launch {
            val originalFetchResult = repository.getBirthdaysAndOtherDataFromAPI()
            if (originalFetchResult is Resource.Success) { // if the fetch was a success, the original list is saved in the viewmodel
                originalBirthdayResultsFetched = originalFetchResult.data
            }

            _birthdayResultsResource.value = originalFetchResult

        }

    }

}