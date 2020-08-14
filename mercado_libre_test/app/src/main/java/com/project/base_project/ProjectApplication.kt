package com.project.base_project

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ProjectApplication: Application() {

    val module = module {  }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ProjectApplication)
            modules(module)
        }
    }

}