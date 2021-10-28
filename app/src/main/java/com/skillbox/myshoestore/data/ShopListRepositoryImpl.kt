package com.skillbox.myshoestore.data

import com.skillbox.myshoestore.domain.ShopItem
import com.skillbox.myshoestore.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId: Long = 0

    init {
        for (i in 0 until 10){
            val item = ShopItem("Name $i", i, true)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldItem = getItemFromId(shopItem.id)
        shopList.remove(oldItem)
        addShopItem(shopItem)
    }

    override fun getItemFromId(shopItemId: Long): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId is not found")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toMutableList()
    }
}