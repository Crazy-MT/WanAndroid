package com.mt.wanandroid.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.mt.wanandroid.ext.htmlToSpanned
import com.mt.wanandroid.model.Article

/**
 * ================================================
 *  @author : MaoTong
 *  @date : 2020/9/22 10:42
 *  description :
 * <a href="mailto:ytumaotong@gmail.com">Contact me</a>
 * <a href="https://github.com/Crazy-MT">Follow me</a>
 * ================================================
 */

@BindingAdapter("selected")
fun setSelected(view: ImageView, isSelected: Boolean) {
    view.isSelected = isSelected
}

@BindingAdapter("htmlText")
fun setHtmlText(view: TextView, text: String) {
    view.text = text.htmlToSpanned()
}

@BindingAdapter("shareUser")
fun setShareUser(view: TextView, article: Article) {
    view.text = when {
        !article.author.isNullOrEmpty() -> {
            article.author
        }
        !article.shareUser.isNullOrEmpty() -> {
            article.shareUser
        }
        else -> "匿名"
    }
}

@BindingAdapter("chapter")
fun setChapter(view: TextView, article: Article) {
    view.text = when {
        !article.superChapterName.isNullOrEmpty() && !article.chapterName.isNullOrEmpty() ->
            "${article.superChapterName.htmlToSpanned()}/${article.chapterName.htmlToSpanned()}"
        article.superChapterName.isNullOrEmpty() && !article.chapterName.isNullOrEmpty() ->
            article.chapterName.htmlToSpanned()
        !article.superChapterName.isNullOrEmpty() && article.chapterName.isNullOrEmpty() ->
            article.superChapterName.htmlToSpanned()
        else -> ""

    }
}