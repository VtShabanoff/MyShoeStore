package com.skillbox.myshoestore.domain

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun editShopList(shopItem: ShopItem){
        shopListRepository.editShopList(shopItem)
    }
}