package com.shaunhossain.dogsinformation.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shaunhossain.dogsinformation.model.dogs.DogsItem
import com.shaunhossain.dogsinformation.network.ApiService
import retrofit2.HttpException
import java.io.IOException

class DogsPagingSource constructor(private val apiService: ApiService) : PagingSource<Int,DogsItem>() {
    override fun getRefreshKey(state: PagingState<Int, DogsItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DogsItem> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getAllDogs(page,params.loadSize)
            LoadResult.Page(
                response,
                prevKey = if (page ==1 ) null else page-1 ,
                nextKey = if (response.isEmpty()) null else page+1
            )

        }catch (e:IOException){
            LoadResult.Error(e)
        }catch (e:HttpException){
            LoadResult.Error(e)
        }
    }
}