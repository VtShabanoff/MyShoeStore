package com.skillbox.myshoestore.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.skillbox.myshoestore.R
import com.skillbox.myshoestore.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {
    var count = 0
    var onShopItemLongClick: ((shopItem: ShopItem) -> Unit)? = null
    var onShopItemClick: ((shopItem: ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        Log.d("Adapter", "onCreateViewHolder, count = ${++count}")
        val shopItemState = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.shop_item_disabled
            VIEW_TYPE_ENABLED -> R.layout.shop_item_enabled
            else -> throw Exception("no correct view type $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(
            shopItemState,
            parent,
            false
        )
        return ShopItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
        holder.view.setOnLongClickListener {
            onShopItemLongClick?.invoke(shopItem)
            true
        }
        holder.view.setOnClickListener {
            onShopItemClick?.invoke(shopItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position).enabled
        return if (item) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_POOL_SIZE = 15
    }

}