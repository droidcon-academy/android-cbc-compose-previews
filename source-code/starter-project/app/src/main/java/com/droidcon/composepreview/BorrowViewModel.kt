package com.droidcon.composepreview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcon.composepreview.data.BorrowRepository
import com.droidcon.composepreview.data.models.BorrowItem
import com.droidcon.composepreview.ui.theme.Theme
import kotlinx.coroutines.launch

class BorrowViewModel(private val borrowRepository: BorrowRepository): ViewModel() {

  private val _items = MutableLiveData<List<BorrowItem>>()
  val items: LiveData<List<BorrowItem>> = _items

  var itemName = ""
  var borrowerName = ""
  var date = ""

  var theme: Theme = Theme.FOLLOW_SYSTEM

  fun addItem() {
    viewModelScope.launch {
      borrowRepository.addItem(
        BorrowItem(
          id = 0,
          itemName = itemName,
          borrowerName = borrowerName,
          borrowDate = date
        )
      )
    }
  }

  fun getAllItems() {
    viewModelScope.launch {
      borrowRepository.getAllBorrowedItems().collect { items ->
        _items.postValue(items)
      }
    }
  }

  fun validateInputs(): Boolean {
    return itemName.isNotEmpty() && borrowerName.isNotEmpty() && date.isNotEmpty()
  }
}