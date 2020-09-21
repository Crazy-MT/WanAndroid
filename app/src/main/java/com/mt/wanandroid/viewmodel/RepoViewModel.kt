package com.mt.wanandroid.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mt.wanandroid.model.Repo
import com.mt.wanandroid.repository.SearchSource
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
class RepoViewModel : ViewModel() {
    val source = SearchSource()
    var repoSearchResp: MutableLiveData<List<Repo>> = MutableLiveData<List<Repo>>()

    init {
        repoSearchResp.postValue(arrayListOf(Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1),
            Repo(1, "name", "22", "desc", null, 1))
        )
    }

    fun search(query: String) {
        viewModelScope.launch {
            runCatching {
                repoSearchResp.postValue(source.searchRepo(query).items)
            }.onSuccess {
            }.onFailure {
                Log.e(TAG, ": MTMTMT " + it.message + Thread.currentThread().name)
            }
        }
    }

    companion object {
        private const val TAG = "RepoViewModel"
    }
}