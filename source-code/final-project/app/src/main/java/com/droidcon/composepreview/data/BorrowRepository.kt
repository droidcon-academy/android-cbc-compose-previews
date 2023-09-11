package com.droidcon.composepreview.data

import com.droidcon.composepreview.data.models.BorrowItem
import kotlinx.coroutines.flow.Flow

interface BorrowRepository {
  suspend fun addItem(item: BorrowItem)
  suspend fun getAllBorrowedItems(): Flow<List<BorrowItem>>
}