package com.skillbox.myshoestore.domain

class GetItemIdUseCase(private val shopListRepository: ShopListRepository) {
    fun getItemFromId(shopItemId: Long): ShopItem{
        return shopListRepository.getItemFromId(shopItemId)
    }
}