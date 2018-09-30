package motobeans.architecture.customAppComponents.activity

import android.app.ProgressDialog
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.assent.Assent
import com.optcrm.optreporting.app.presenter.connector.ReusableView
import motobeans.architecture.util.DialogFactory
import motobeans.architecture.util.exShowToast


/**
 * Created by swati on 24/9/2018.
 */
abstract class BaseFragment : Fragment(), ReusableView {

  abstract fun init()

  private var rootView: View? = null

  @CallSuper
  fun <T : ViewDataBinding> initBinding(inflater: LayoutInflater, container: ViewGroup?,
      layoutId: Int): T {
    val binding = DataBindingUtil.inflate<T>(inflater, layoutId, container, false)
    rootView = binding.root

    Assent.setFragment(this, this)

    return binding
  }

  override fun getView(): View? {
    return rootView
  }

  override fun showToast(msg: String) {
    msg.exShowToast(getContext())
  }

  companion object {
    internal var progressDialog: ProgressDialog? = null
  }

  override fun showProgressDialog() {
    when (progressDialog == null) {
      true -> progressDialog = DialogFactory.getInstance(context = activity!!)
    }
    progressDialog?.show()
  }

  override fun hideProgressDialog() {
    progressDialog?.hide()
  }

  override fun onResume() {
    super.onResume()
    Assent.setFragment(this, this)
  }

  override fun onPause() {
    super.onPause()
    if (activity != null && activity!!.isFinishing)
      Assent.setFragment(this, null)
  }
}