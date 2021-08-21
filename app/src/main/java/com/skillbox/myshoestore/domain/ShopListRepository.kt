package com.skillbox.myshoestore.domain

interface ShopListRepository {
    fun addShopList(shopItem: ShopItem)

    fun deleteShopList(shopItem: ShopItem)

    fun editShopList(shopItem: ShopItem)

    fun getItemFromId(shopItemId: Long): ShopItem

    fun getShopList(): List<ShopItem>
}