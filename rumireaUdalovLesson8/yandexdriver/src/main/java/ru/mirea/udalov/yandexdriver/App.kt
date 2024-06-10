package ru.mirea.udalov.yandexdriver

import android.app.Application
import com.yandex.mapkit.MapKitFactory

class App  : Application() {

    companion object {
        private const val MAPKIT_API_KEY = "ff9e4d86-f85d-4dc6-80b2-f70b839631f3"
    }

    override fun onCreate() {
        super.onCreate()

        MapKitFactory.setApiKey(MAPKIT_API_KEY)
    }
}