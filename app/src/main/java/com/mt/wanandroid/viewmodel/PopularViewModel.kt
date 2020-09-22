package com.mt.wanandroid.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mt.wanandroid.model.Article
import com.mt.wanandroid.model.Repo
import com.mt.wanandroid.repository.SearchSource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * ================================================
 *  @author : MaoTong
 *  @date : 2020/9/16 16:06
 *  description :
 * <a href="mailto:ytumaotong@gmail.com">Contact me</a>
 * <a href="https://github.com/Crazy-MT">Follow me</a>
 * ================================================
 */
class PopularViewModel : ViewModel() {
    val source = SearchSource()
    val articleList: MutableLiveData<MutableList<Article>> = MutableLiveData()

    init {
//        repoSearchResp.postValue(arrayListOf(Repo(1, "name", "22", "desc", null, 1)))
    }

    fun refreshArticleList() {
        viewModelScope.launch {
            runCatching {
                val topArticleListDefferd = async {
                    source.getTopArticleList()
                }

                val paginationDefferd = async {
                    source.getArticleList(INITIAL_PAGE)
                }

                val topArticleList = topArticleListDefferd.await()
                    .apply { forEach { it.top = true } }
                val pagination = paginationDefferd.await()

                articleList.value = mutableListOf<Article>().apply {
                    addAll(topArticleList)
                    addAll(pagination.datas)
                }
            }.onSuccess {
            }.onFailure {
                Log.e(TAG, ": MTMTMT " + it.message + Thread.currentThread().name)
            }
        }
    }

    companion object {
        private const val TAG = "RepoViewModel"
        const val INITIAL_PAGE = 0
    }
}