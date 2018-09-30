
package com.optcrm.optreporting.app.viewModel

import android.app.Activity
import android.app.ProgressDialog
import android.arch.lifecycle.ViewModel
import com.optcrm.optreporting.app.presenter.connector.ReusableView
import motobeans.architecture.util.DialogFactory
import motobeans.architecture.util.exShowToast

/**
 * Created by swati on 24/9/2018.
 */
open class BaseViewModel(private val activity: Activity) : ViewModel(), ReusableView {

    private var progressCounter = 0
    init {
    }

    override fun showToast(msg: String) {
        msg.exShowToast(activity)
    }

    companion object {
        internal var progressDialog: ProgressDialog? = null
    }

    override fun showProgressDialog() {
        when(progressDialog == null) {
            true -> progressDialog = DialogFactory.getInstance(context = activity)
        }
        progressDialog?.show()
    }

    override fun hideProgressDialog() {
        progressDialog?.hide()
    }


}