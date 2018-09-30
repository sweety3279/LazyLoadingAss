package motobeans.architecture.development.components

import android.app.Application
import com.optcrm.optreporting.AppModule
import com.optcrm.optreporting.app.presenter.presenter.MainPresenter
import com.optcrm.optreporting.app.view.activity.MainActivity
import com.optcrm.optreporting.app.view.adapters.Recycler.Adapter.ItemRecyclerAdapter
import com.optcrm.optreporting.app.view.adapters.Recycler.Holder.ItemHolder

import com.optcrm.optreporting.app.viewModel.MainViewModel
import dagger.Component
import motobeans.architecture.customAppComponents.activity.BaseAppCompatActivity
import motobeans.architecture.development.modules.NetworkModule
import motobeans.architecture.development.modules.PrimitivesModule
import javax.inject.Singleton

/**
 * Created by swati on 24/9/2018.
 */
@Singleton
@Component(modules = arrayOf(
    AppModule::class, NetworkModule::class, PrimitivesModule::class
))
interface ApplicationComponent {

    fun inject(app: Application)

    /**
     * Activities
     */
    fun inject(activity: MainActivity)
    fun inject(activity: BaseAppCompatActivity)



    /**
     * Presenters
     */
    fun inject(presenter: MainPresenter)


    /**
     * View Model
     */
    fun inject(viewModel: MainViewModel)

    /**
     * Adapters
     */
    fun inject(adapter: ItemRecyclerAdapter)


    /**
     * Holders
     */
    fun inject(other: ItemHolder)
}