
package com.optcrm.optreporting.app.viewModel

import android.arch.lifecycle.MutableLiveData
import android.support.v4.app.FragmentActivity

/**
 * Created by swati on 24/9/2018.
 */
class MainViewModel(private val activity: FragmentActivity) : BaseViewModel(activity){


    /** ProductViewState Mutable Live Data */
    val viewState: MutableLiveData<ProductViewState> = MutableLiveData()

    /**
     * Constructor of MainViewModel
     */
    init {
        viewState.value = ProductViewState()
    }


    /**
     * Get Current View State
     *
     * @return LiveData of View Model State
     */
    private fun currentViewState(): ProductViewState = viewState.value!!

    /**
     * This class contains different states of screen such as:
     *
     *   isLoading = api call is running and getting data from network
     *
     *   isError = if any error occurs during network operation
     *
     *   isEmptyData = if no data is available
     */
    data class ProductViewState(var isNoDataFound: Boolean = false,
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val isEmptyData: Boolean = false,
        val errorMessage: String? = null)

    /**
     * Set loading view state
     */
    private fun isLoading(isLoading: Boolean) {
        viewState.value = currentViewState().copy(isLoading = isLoading)
    }

    /**
     * Set Error view state
     */
    private fun isError(isError: Boolean, errorMessage: String?) {
        viewState.value = currentViewState().copy(isError = isError, errorMessage = errorMessage)
    }

    /**
     * Set Empty view state
     */
    private fun isEmptyList(isEmptyData: Boolean) {
        viewState.value = currentViewState().copy(isEmptyData = isEmptyData)
    }
}