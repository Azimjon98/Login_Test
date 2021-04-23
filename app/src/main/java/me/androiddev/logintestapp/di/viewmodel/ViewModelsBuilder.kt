package com.mobile.core.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.androiddev.logintestapp.ui.mv.LoginFragmentVM

@Module
abstract class ViewModelsBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(LoginFragmentVM::class)
    abstract fun bindViewModel(albumsViewModel: LoginFragmentVM): ViewModel

}