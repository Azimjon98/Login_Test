package me.androiddev.logintestapp.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import me.androiddev.logintestapp.di.CoreScope

@Module
internal class ContextModule {

    @Provides
    @CoreScope
    fun bindContext(application: Application): Context = application
}