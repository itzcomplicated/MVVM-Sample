package com.example.stockapplication.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(stock: StockView) {
        itemView.tv_name.text = stock.name
        itemView.tv_price.text = stock.price
    }
}