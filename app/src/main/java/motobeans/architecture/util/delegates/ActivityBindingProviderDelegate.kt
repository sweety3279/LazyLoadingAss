package motobeans.architecture.util.delegates

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import com.optcrm.optreporting.databinding.CustomActionbaractivityWithBackBinding
import motobeans.architecture.customAppComponents.activity.BaseAppCompatActivity
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by swati on 24/9/2018.
 */
class ActivityBindingProviderDelegate<out T : ViewDataBinding>(
    private val baseAppCompatActivity: BaseAppCompatActivity, @LayoutRes private val layoutRes: Int) : ReadOnlyProperty<Activity, T> {

    private var binding: T? = null

    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
        return binding ?: createBinding(
            baseAppCompatActivity.getParentBinding()).also { binding = it }
    }

    private fun createBinding(bindingParent: CustomActionbaractivityWithBackBinding): T {

        val inflator = LayoutInflater.from(bindingParent.llInflatorContainer?.context)
        return DataBindingUtil.inflate(inflator, layoutRes, bindingParent.llInflatorContainer, true)
    }
}