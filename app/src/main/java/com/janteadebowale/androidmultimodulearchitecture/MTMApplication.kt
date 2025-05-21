package com.janteadebowale.androidmultimodulearchitecture

import android.app.Application
import com.janteadebowale.androidmultimodulearchitecture.di.mainModule
import com.janteadebowale.core.network.di.networkModule
import com.janteadebowale.data.di.dataModule
import com.janteadebowale.feature.auth.di.authModule
import com.janteadebowale.feature.home.di.homeModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MTMApplication : Application() {
    val applicationCoroutineScope = CoroutineScope(SupervisorJob())
    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@MTMApplication)
            modules(
                mainModule,
                networkModule,
                authModule,
                dataModule,
                homeModule
            )
        }
    }
}