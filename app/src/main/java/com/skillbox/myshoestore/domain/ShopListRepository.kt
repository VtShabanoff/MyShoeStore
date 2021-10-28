package com.skillbox.myshoestore.domain

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getItemFromId(shopItemId: Long): ShopItem

    fun getShopList(): List<ShopItem>
}