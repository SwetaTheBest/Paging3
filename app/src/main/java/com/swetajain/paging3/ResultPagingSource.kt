package com.swetajain.paging3

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.swetajain.paging3.network.Result
import com.swetajain.paging3.network.RetrofitService

class ResultPagingSource(val api: RetrofitService) : PagingSource<Int, Result>() {
    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = api.getDataFromApi(nextPage)
            var nextPageNumber: Int? = null
            if (response.info.next != null) {
                val uri = Uri.parse(response.info.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }
            LoadResult.Page(
                data = response.results,
                prevKey = null, nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}