package com.shaunhossain.dogsinformation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shaunhossain.dogsinformation.model.dogs.DogsItem
import com.shaunhossain.dogsinformation.network.ApiService
import com.shaunhossain.dogsinformation.repository.DogsPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(private val apiService: ApiService): ViewModel() {
        fun getAllDogsInfo() : Flow<PagingData<DogsItem>> = Pager(
            config = PagingConfig(20,enablePlaceholders = false)
        ){
            DogsPagingSource(apiService)
        }.flow.cachedIn(viewModelScope)

}