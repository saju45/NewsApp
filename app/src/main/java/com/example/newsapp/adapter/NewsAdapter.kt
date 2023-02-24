package com.example.newsapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.model.NewsModel
import com.example.newsapp.R
import com.example.newsapp.WebViewActivity
import com.example.newsapp.databinding.LayoutItemBinding

class NewsAdapter(val context: Context,val list:ArrayList<NewsModel>) :RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){


    inner class NewsViewHolder(view:View):RecyclerView.ViewHolder(view){

        val binding=LayoutItemBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {


        return NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val data=list[position]
        holder.binding.header.text=data.title
        holder.binding.content.text=data.description
        holder.binding.author.text=data.author
        holder.binding.publishAt.text="Published At : "+data.publishedAt


        Glide.with(context).load(data.urlToImage).into(holder.binding.imageview)


        holder.itemView.setOnClickListener {

            val intent=Intent(context,WebViewActivity::class.java)
            intent.putExtra("url",data.url)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {

    return list.size
    }
}