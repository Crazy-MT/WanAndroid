package com.mt.wanandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.mt.wanandroid.R
import com.mt.wanandroid.common.DataBoundListAdapter
import com.mt.wanandroid.databinding.ItemPopularBinding
import com.mt.wanandroid.databinding.ItemRepoBinding
import com.mt.wanandroid.model.Article

/**
 * ================================================
 *  @author : MaoTong
 *  @date : 2020/9/17 10:56
 *  description :
 * <a href="mailto:ytumaotong@gmail.com">Contact me</a>
 * <a href="https://github.com/Crazy-MT">Follow me</a>
 * ================================================
 */
class PopularItemAdapter(
    private val showFullName: Boolean,
    private val articleClickCallback: ((Article) -> Unit)?
) :
    DataBoundListAdapter<Article, ItemPopularBinding>(
        object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.author == newItem.author
                        && oldItem.apkLink == newItem.apkLink
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.desc == newItem.desc
            }
        }
    ) {

    override fun createBinding(parent: ViewGroup): ItemPopularBinding {

        val binding =  DataBindingUtil.inflate<ItemPopularBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_popular,
            parent,
            false
        )
        binding.showFullName = showFullName
        binding.root.setOnClickListener{
            binding.article?.let {
                articleClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: ItemPopularBinding, item: Article) {
        binding.article = item
    }

    companion object {
        var TAG = "article"
    }
}