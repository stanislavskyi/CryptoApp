package com.hfad.cryptoapp.di

import android.app.Application
import com.hfad.cryptoapp.presentation.CoinApp
import com.hfad.cryptoapp.presentation.CoinDetailFragment
import com.hfad.cryptoapp.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [DataModule::class, ViewModelModule::class, WorkerModule::class]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: CoinDetailFragment)

    fun inject(application: CoinApp)

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}