package com.example.noname

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.noname.model.ResurantsModel
import com.google.firebase.firestore.core.View
import kotlinx.android.synthetic.main.resturantitem.view.*

class HomeAdapter() : ListAdapter<ResurantsModel, HomeAdapter.HomeViewHolder>(HomeDifffUtil()) {
    class HomeViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
    }

    class HomeDifffUtil : DiffUtil.ItemCallback<ResurantsModel>() {
        override fun areItemsTheSame(oldItem: ResurantsModel, newItem: ResurantsModel): Boolean {
            return oldItem.url == newItem.url && oldItem.title == newItem.title && oldItem.subTitle == newItem.subTitle
        }

        override fun areContentsTheSame(oldItem: ResurantsModel, newItem: ResurantsModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        return HomeViewHolder(inflator.inflate(R.layout.resturantitem, parent, false))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.apply {
            Glide.with(this).load(item.url).into(imageUrl)
            txtTitle.text = item.title
            txtSubTitle.text = item.subTitle
            txtAdress.text = item.Adress.toString()
        }
    }

}