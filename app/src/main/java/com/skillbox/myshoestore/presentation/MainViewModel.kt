package com.skillbox.myshoestore.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.myshoestore.data.ShopListRepositoryImpl
import com.skillbox.myshoestore.domain.DeleteShopItemUseCase
import com.skillbox.myshoestore.domain.EditShopItemUseCase
import com.skillbox.myshoestore.domain.GetShopListUseCase
import com.skillbox.myshoestore.domain.ShopItem

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopListLD = MutableLiveData<List<ShopItem>>()

    fun getShopList(){
        val list = getShopListUseCase.getShopList()
        shopListLD.postValue(list)
    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
        getShopList()
    }
}