package com.swetajain.paging3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.swetajain.paging3.ResultPagingSource
import com.swetajain.paging3.network.Result
import com.swetajain.paging3.network.RetrofitInstance
import com.swetajain.paging3.network.RetrofitService
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {
    private lateinit var retrofitService: RetrofitService

    init {
        retrofitService = RetrofitInstance.getRetrofitInstance().create(
            RetrofitService::class.java
        )
    }

    fun getListData(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = 34, maxSize = 200),
            pagingSourceFactory = { ResultPagingSource(retrofitService) })
            .flow.cachedIn(viewModelScope)
    }
}