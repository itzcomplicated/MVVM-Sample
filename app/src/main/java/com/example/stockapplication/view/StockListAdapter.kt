package com.example.stockapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.stockapplication.R

class StockListAdapter : ListAdapter<StockView, StockViewHolder>(StockViewDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        StockViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout, parent, false)
        )

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


private class StockViewDC : DiffUtil.ItemCallback<StockView>() {
    override fun areItemsTheSame(
        oldItem: StockView,
        newItem: StockView
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: StockView,
        newItem: StockView
    ): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.price == newItem.price
    }
}
