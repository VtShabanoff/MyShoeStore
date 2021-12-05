package com.skillbox.myshoestore.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.myshoestore.data.ShopListRepositoryImpl
import com.skillbox.myshoestore.domain.AddShopItemUseCase
import com.skillbox.myshoestore.domain.EditShopItemUseCase
import com.skillbox.myshoestore.domain.GetItemIdUseCase
import com.skillbox.myshoestore.domain.ShopItem

class ShopItemScreenViewModel : ViewModel() {

    private val repositoryShopItem = ShopListRepositoryImpl

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _exitScreen = MutableLiveData<Unit>()
    val exitScreen: LiveData<Unit>
        get() = _exitScreen

    private val editShopItem = EditShopItemUseCase(repositoryShopItem)
    private val addShopItem = AddShopItemUseCase(repositoryShopItem)
    private val getShopItem = GetItemIdUseCase(repositoryShopItem)

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validate(name, count)
        if (fieldsValid) {
            _shopItem.value?.let {
                val item = it.copy(name = name, count = count)
                editShopItem.editShopItem(item)
                finishWorkScreen()
            }
        }
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validate(name, count)
        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItem.addShopItem(shopItem)
            finishWorkScreen()
        }
    }

    fun getShopItem(shopItemId: Long) {
        val item = getShopItem.getItemFromId(shopItemId)
        _shopItem.postValue(item)
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }
    private fun validate(name: String, count: Int): Boolean{
        var result = true
        if (name.isBlank()) result = false
        _errorInputName.postValue(true)
        if (count <= 0) result = false
        _errorInputCount.postValue(true)
        return result
    }

    fun resetErrorInputName(){
        _errorInputName.postValue(false)
    }

    fun resetErrorInputCount(){
        _errorInputCount.postValue(false)
    }

    private fun finishWorkScreen(){
        _exitScreen.postValue(Unit)
    }
}