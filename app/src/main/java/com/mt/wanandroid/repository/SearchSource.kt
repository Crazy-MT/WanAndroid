package com.mt.wanandroid.repository

import com.mt.wanandroid.api.Api
import com.mt.wanandroid.model.RepoSearchResponse

/**
 * ================================================
 *  @author : MaoTong
 *  @date : 2020/9/16 18:01
 *  description :
 * <a href="mailto:ytumaotong@gmail.com">Contact me</a>
 * <a href="https://github.com/Crazy-MT">Follow me</a>
 * ================================================
 */
class SearchSource{
    private val api= Api.create()

//    suspend fun searchRepo(query: String) : MutableLiveData<ApiResponse<RepoSearchResponse>> {
        suspend fun getTopArticleList() = api.getTopArticleList().apiData()
//    }
}