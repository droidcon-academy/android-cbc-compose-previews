package com.droidcon.composepreview

import android.app.Application
import com.droidcon.composepreview.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BorrowApp: Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidLogger(Level.NONE)
      androidContext(this@BorrowApp)
      modules(appModules)
    }
  }
}