package com.skillbox.myshoestore.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteShopList(shopItem: ShopItem){
        shopListRepository.deleteShopList(shopItem)
    }
}