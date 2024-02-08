package com.fexapk.aoelookup

import android.app.Application
import com.fexapk.aoelookup.data.AppContainer
import com.fexapk.aoelookup.data.DefaultAppContainer
class AoeLookupApplication: Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}