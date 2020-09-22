package com.mt.wanandroid

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mt.wanandroid.adapter.RepoItemAdapter
import com.mt.wanandroid.databinding.ActivityMainBinding
import com.mt.wanandroid.viewmodel.RepoViewModel

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    var viewModel: RepoViewModel = RepoViewModel()
    var adapter: RepoItemAdapter? = null

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.viewmodel = viewModel
        binding?.lifecycleOwner = this

        initRepoRecyclerView()

        viewModel.refreshArticleList()
    }

    private fun initRepoRecyclerView() {
        adapter = RepoItemAdapter(true) {
            repo -> Log.e(TAG, ": MTMTMT " + repo.link)
        }

        binding?.repoList?.adapter = adapter
        viewModel.articleList.observe(this, {
            binding?.loading = false
            adapter?.submitList(it)
        })

        binding?.repoList?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                binding?.loadingMore = lastPosition == adapter?.itemCount!! - 1
            }
        })
    }
}