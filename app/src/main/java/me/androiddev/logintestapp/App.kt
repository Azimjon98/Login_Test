package me.androiddev.logintestapp

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentActivity
import me.androiddev.logintestapp.di.CoreComponent
import me.androiddev.logintestapp.di.DaggerCoreComponent

class App : Application() {

    override fun onCreate() {
        //Finish process if device rooted

        super.onCreate()
        application = this
        coreComponent.inject(this)
    }

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent
            .factory()
            .create(this)
    }

    companion object {
        lateinit var application: Application

        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as App).coreComponent

    }

}

fun Activity.coreComponent() = App.coreComponent(this)
fun FragmentActivity.coreComponent() = App.coreComponent(this)