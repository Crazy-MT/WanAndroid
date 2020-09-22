package com.mt.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mt.wanandroid.adapter.PopularItemAdapter
import com.mt.wanandroid.databinding.ActivityMainBinding
import com.mt.wanandroid.viewmodel.PopularViewModel

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    var viewModel: PopularViewModel = PopularViewModel()
    var adapter: PopularItemAdapter? = null

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.viewmodel = viewModel
        binding?.lifecycleOwner = this

        initPopularRecyclerView()

        viewModel.refreshArticleList()
    }

    private fun initPopularRecyclerView() {
        adapter = PopularItemAdapter(true) {
            repo -> Log.e(TAG, ": MTMTMT " + repo.link)
        }

        binding?.popularList?.adapter = adapter
        viewModel.articleList.observe(this, {
            binding?.loading = false
            adapter?.submitList(it)
        })

        binding?.popularList?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                binding?.loadingMore = lastPosition == adapter?.itemCount!! - 1
            }
        })
    }
}