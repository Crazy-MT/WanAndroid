package com.mt.wanandroid.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.mt.wanandroid.ext.htmlToSpanned

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
fun setSelected(view: ImageView, isSelected:Boolean) {
    view.isSelected = isSelected
}

@BindingAdapter("htmlText")
fun setHtmlText(view: TextView, text: String) {
    view.text = text.htmlToSpanned()
}