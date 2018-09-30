package motobeans.architecture.appDelegates

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import motobeans.architecture.appDelegates.ViewModelType.WITHOUT_FACTORY

/**
 * Created by swati on 24/9/2018.
 */

enum class ViewModelType {
    WITHOUT_FACTORY, WITH_DAO
}

inline fun <reified VM : ViewModel> Fragment.viewModelProvider(activity: FragmentActivity,
    viewModelType: ViewModelType = WITHOUT_FACTORY) = lazy {
    var viewModelFactory: ViewModelProvider.Factory? = null

    // Initialize Product View Model
    if (viewModelFactory != null) {
        ViewModelProviders.of(activity, viewModelFactory).get(VM::class.java)
    } else {
        ViewModelProviders.of(activity).get(VM::class.java)
    }
}

inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvider(activity: FragmentActivity,
    viewModelType: ViewModelType = WITHOUT_FACTORY) = lazy {
    var viewModelFactory: ViewModelProvider.Factory? = null

    // Initialize Product View Model
    if (viewModelFactory != null) {
        ViewModelProviders.of(activity, viewModelFactory).get(VM::class.java)
    } else {
        ViewModelProviders.of(activity).get(VM::class.java)
    }
}