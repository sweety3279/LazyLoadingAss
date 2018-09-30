package com.optcrm.optreporting.app.presenter.presenter

import com.optcrm.optreporting.app.presenter.connector.MainConnector.PresenterOpt
import com.optcrm.optreporting.app.presenter.connector.MainConnector.ViewOpt
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import motobeans.architecture.application.ArchitectureApp
import motobeans.architecture.constants.ConstantsApi
import motobeans.architecture.constants.ConstantsApi.CALL_TEMP
import motobeans.architecture.development.interfaces.ApiProject
import motobeans.architecture.retrofit.response.Response
import javax.inject.Inject

/**
 * Created by swati on 24/9/2018.
 */
class MainPresenter(private val viewOpt: ViewOpt) : PresenterOpt {
    @Inject
    lateinit var apiProject: ApiProject

    init {
        ArchitectureApp.instance.component.inject(this)
    }

    override fun callNetwork(type: ConstantsApi) {
        when (type) {
            CALL_TEMP -> callApiForData(isLoadMore = false)
        }
    }

    override fun callApiForData(isLoadMore: Boolean) {
        val requestApi = apiProject.api.getData()

        requestApi
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ resposne -> onNextForgotPassword(isLoadMore = isLoadMore, response = resposne) },
                { e -> viewOpt.getApiDataFailure(e?.message ?: "") })
    }


    private fun onNextForgotPassword(isLoadMore: Boolean, response: Response.ResponseData) {
        viewOpt.getApiDataSuccess(isLoadMore, response)

    }
}