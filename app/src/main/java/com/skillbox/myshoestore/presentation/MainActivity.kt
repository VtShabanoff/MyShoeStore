package com.skillbox.myshoestore.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.myshoestore.R

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopListLD.observe(this) {
            shopListAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        shopListAdapter = ShopListAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewShopList)
        recyclerView.adapter = shopListAdapter
        recyclerView.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_ENABLED,
            ShopListAdapter.MAX_POOL_SIZE
        )
        recyclerView.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_DISABLED,
            ShopListAdapter.MAX_POOL_SIZE
        )
        setupLongClickListener()
        setupClickListener()
        setupSwipeShopItem(recyclerView)
    }

    private fun setupSwipeShopItem(recyclerView: RecyclerView?) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val shopItem = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(shopItem)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setupClickListener() {
        shopListAdapter.onShopItemClick = {
            toast("this item name ${it.name}, id = ${it.id}")
        }
    }

    private fun setupLongClickListener() {
        shopListAdapter.onShopItemLongClick = {
            viewModel.changeEnableState(it)
        }
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}