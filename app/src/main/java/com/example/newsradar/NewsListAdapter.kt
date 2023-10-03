package com.example.newsradar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class NewsListAdapter( private val listner: NewsItemClicked) : RecyclerView.Adapter<NewsViewHolder>() {
    private val items: ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
         val viewHolder=NewsViewHolder(view)

        view.setOnClickListener{
        listner.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem= items[position]

        holder.titleView.text= currentItem.title
        holder.textView.text= currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageToUrl).into(holder.imageView)

    }
    fun updateNews(updatedNews: ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val titleView: TextView=itemView.findViewById(R.id.title)
    val imageView: ImageView=itemView.findViewById(R.id.image)
    val textView: TextView=itemView.findViewById(R.id.author)

}
interface NewsItemClicked{
    fun onItemClicked(item: News)
}