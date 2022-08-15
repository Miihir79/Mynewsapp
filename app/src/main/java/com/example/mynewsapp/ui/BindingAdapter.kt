package com.example.mynewsapp.ui

import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton


@BindingAdapter("term","viewModel")
fun onClickNews(view: MaterialButton,text:String?,vm: ViewModel){
    view.setOnClickListener{
        if (text != null) {
            vm.getNews(text)
        }else{
            vm.getNews(null)
        }
    }
}
