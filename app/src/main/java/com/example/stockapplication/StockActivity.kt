package com.example.stockapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stockapplication.R.layout.activity_stock
import com.example.stockapplication.view.StockListAdapter
import com.example.stockapplication.viewmodel.StockViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_stock.*

@AndroidEntryPoint
class StockActivity : AppCompatActivity() {

    private val stockViewModel: StockViewModel by viewModels()
    private lateinit var adapter: StockListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_stock)
        setupUI()
    }

    private fun setupUI() {
        adapter = StockListAdapter()
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
        stockViewModel.getStocks().observe(this, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
    }
}
