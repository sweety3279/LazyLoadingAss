package motobeans.architecture.application

import android.app.Application
import android.content.Context
import motobeans.architecture.development.modules.AppModule
import motobeans.architecture.development.components.ApplicationComponent
import motobeans.architecture.development.components.DaggerApplicationComponent
import motobeans.architecture.development.modules.NetworkModule
import motobeans.architecture.development.modules.PrimitivesModule


/**
 * Created by swati on 24/9/2018.
 */
class ArchitectureApp : Application() {

    companion object {
        lateinit var instance: ArchitectureApp
            private set
    }

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .primitivesModule(PrimitivesModule())
            .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        component.inject(this)
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}