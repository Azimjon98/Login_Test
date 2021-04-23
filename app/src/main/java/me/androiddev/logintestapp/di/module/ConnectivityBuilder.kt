package me.androiddev.logintestapp.di.module


import com.mobile.core.util.Connectivity
import com.mobile.core.util.ConnectivityImpl
import dagger.Binds
import dagger.Module
import me.androiddev.logintestapp.di.CoreScope

@Module
abstract class ConnectivityBuilder {

    @Binds
    @CoreScope
    abstract fun bindConnectivity(connectivityImpl: ConnectivityImpl): Connectivity
}