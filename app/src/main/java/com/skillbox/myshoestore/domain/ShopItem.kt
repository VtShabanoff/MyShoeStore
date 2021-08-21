package com.skillbox.myshoestore.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val enabled: Boolean,
    var id: Long = -1
){
    companion object{
        const val UNDEFINED_ID: Long = -1
    }
}
