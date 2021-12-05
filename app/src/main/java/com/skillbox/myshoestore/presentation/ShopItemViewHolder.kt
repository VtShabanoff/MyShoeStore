package com.skillbox.myshoestore.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.myshoestore.R

class ShopItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    val tvName: TextView = view.findViewById(R.id.tvName)
    val tvCount: TextView = view.findViewById(R.id.tvCount)
}