package com.optcrm.optreporting

import android.app.Application
import dagger.Module
import dagger.Provides
import motobeans.architecture.application.ArchitectureApp
import javax.inject.Singleton

/**
 * Created by swati on 24/9/2018.
 */
@Module
class AppModule(private val app: ArchitectureApp) {
    @Provides
    @Singleton
    fun provideApplication(): Application {
        return app
    }

    @Provides
    @Singleton
    fun provideArchitecture(): ArchitectureApp {
        return app
    }
}