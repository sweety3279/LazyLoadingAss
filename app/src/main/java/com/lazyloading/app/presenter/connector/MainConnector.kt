package com.optcrm.optreporting.app.presenter.connector

import motobeans.architecture.retrofit.response.Response

/**
 * Created by swati on 24/9/2018.
 */
interface MainConnector {

    interface ViewOpt: ReusableView {
        fun getApiDataSuccess(isLoadMore: Boolean, value: Response.ResponseData)
        fun getApiDataFailure(msg: String)

    }

    interface PresenterOpt: ReusableNetworkConnector {
        fun callApiForData(isLoadMore: Boolean)
    }

}