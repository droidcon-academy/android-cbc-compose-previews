package com.droidcon.composepreview.di

import androidx.room.Room
import com.droidcon.composepreview.BorrowViewModel
import com.droidcon.composepreview.data.BorrowDb
import com.droidcon.composepreview.data.BorrowRepository
import com.droidcon.composepreview.data.BorrowRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
  single {
    Room.databaseBuilder(
      androidApplication(),
      BorrowDb::class.java,
      "Borrow.db"
    ).fallbackToDestructiveMigration().build()
  }
}
val repositoryModule = module {
  single<BorrowRepository> { BorrowRepositoryImpl(get()) }
}

val viewModelModule = module {
  viewModel { BorrowViewModel(get()) }
}

val appModules = listOf(
  dataModule,
  repositoryModule,
  viewModelModule
)