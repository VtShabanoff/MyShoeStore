package com.skillbox.myshoestore.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getItemFromId(shopItemId: Long): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}