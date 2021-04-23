package me.androiddev.logintestapp.di

import android.app.Application
import com.mobile.core.di.viewmodel.ViewModelFactoryBuilder
import dagger.BindsInstance
import dagger.Component
import me.androiddev.logintestapp.App
import me.androiddev.logintestapp.di.module.ConnectivityBuilder
import me.androiddev.logintestapp.di.module.ContextModule
import me.androiddev.logintestapp.ui.LoginFragment


@CoreScope
@Component(modules = [ContextModule::class, ConnectivityBuilder::class, ViewModelFactoryBuilder::class])
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): CoreComponent
    }

    fun inject(app: App)
    fun inject(fragment: LoginFragment)
}