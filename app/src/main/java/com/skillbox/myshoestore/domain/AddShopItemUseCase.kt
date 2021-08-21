package com.skillbox.myshoestore.domain

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun addShopList(shopItem: ShopItem){
        shopListRepository.addShopList(shopItem)
    }
}