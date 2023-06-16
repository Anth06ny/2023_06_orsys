package com.amonteiro.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amonteiro.myapplication.databinding.RowCoordBinding

class WindListAdapter : ListAdapter<WindBean, WindListAdapter.ViewHolder>(Comparator()) {

    class ViewHolder(val binding:RowCoordBinding) : RecyclerView.ViewHolder(binding.root)

    class Comparator : DiffUtil.ItemCallback<WindBean>() {
        override fun areItemsTheSame(oldItem: WindBean, newItem: WindBean)
        = oldItem === newItem

        override fun areContentsTheSame(oldItem: WindBean, newItem: WindBean)
        = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        =ViewHolder(RowCoordBinding.inflate(LayoutInflater.from(parent.context)))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.tvville.text = "${item.speed}"
    }
}