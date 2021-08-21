package com.skillbox.myshoestore.data

import com.skillbox.myshoestore.domain.ShopItem
import com.skillbox.myshoestore.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId: Long = 0

    override fun addShopList(shopItem: ShopItem) {
        if (autoIncrementId == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopList(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopList(shopItem: ShopItem) {
        val oldItem = getItemFromId(shopItem.id)
        shopList.remove(oldItem)
        addShopList(shopItem)
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