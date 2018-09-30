package com.optcrm.optreporting.app.view.activity

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ClipDrawable
import android.os.Handler
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.lazyloading.app.view.adapters.Recycler.Adapter.EndlessRecyclerViewScrollListener
import com.optcrm.optreporting.R
import com.optcrm.optreporting.app.presenter.connector.MainConnector
import com.optcrm.optreporting.app.presenter.presenter.MainPresenter
import com.optcrm.optreporting.app.view.adapters.Recycler.Adapter.ItemRecyclerAdapter
import com.optcrm.optreporting.app.viewModel.MainViewModel
import com.optcrm.optreporting.databinding.ActivityMainBinding
import motobeans.architecture.appDelegates.ViewModelType.WITH_DAO
import motobeans.architecture.appDelegates.viewModelProvider
import motobeans.architecture.application.ArchitectureApp
import motobeans.architecture.customAppComponents.activity.BaseAppCompatActivity
import motobeans.architecture.retrofit.response.Response
import motobeans.architecture.retrofit.response.Response.ResponseData
import motobeans.architecture.util.delegates.ActivityBindingProviderDelegate
import motobeans.architecture.util.exGone
import motobeans.architecture.util.exVisible


/**
 * Created by swati on 24/9/2018.
 */

class MainActivity : BaseAppCompatActivity(), MainConnector.ViewOpt {

  companion object {
    fun start(context: Context) {
      val intent = Intent(context, MainActivity::class.java)
      context.startActivity(intent)
    }
  }

  var alItem = ArrayList<Response.RowData>()


  lateinit var presenter: MainConnector.PresenterOpt
  private val binding: ActivityMainBinding by ActivityBindingProviderDelegate(this,
      R.layout.activity_main)
  private val viewModel: MainViewModel by viewModelProvider(activity = this,
      viewModelType = WITH_DAO)

  val linearLayoutManager = LinearLayoutManager(this)

  override fun init() {
    hideBack()
    ArchitectureApp.instance.component.inject(this)
    presenter = MainPresenter(viewOpt = this)

    var itemDecor = DividerItemDecoration(this, ClipDrawable.HORIZONTAL)

    linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

    binding.rvItem.setHasFixedSize(true)
    binding.rvItem.isNestedScrollingEnabled = true
    binding.rvItem.addItemDecoration(itemDecor)
    binding.rvItem.layoutManager = linearLayoutManager

    var adapter = ItemRecyclerAdapter(this, alItem)
    binding.rvItem.adapter = adapter

    binding.pullToRefresh.setOnRefreshListener {
      presenter.callApiForData(isLoadMore = false)
      binding.pullToRefresh.isRefreshing = false
    }

    addScrollListenerToRecyclerView(layout = linearLayoutManager)
  }

  private fun addScrollListenerToRecyclerView(layout: LinearLayoutManager) {

    binding.rvItem.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layout) {
      override fun onLoadMore(page: Int, totalItemsCount: Int) {
        hitApiForData()
      }

    })
  }

  private fun hitApiForData() {
    showMoreDataLoading()

    Handler().postDelayed(object : Runnable {
      override fun run() {
        presenter.callApiForData(isLoadMore = true)
      }
    }, 1500)


  }

  private fun showMoreDataLoading() {
    binding.itemProgressBar.exVisible()
  }

  private fun hideMoreDataLoading() {
    binding.itemProgressBar.exGone()
  }

  override fun getApiDataSuccess(isLoadMore: Boolean, value: ResponseData) {
    hideMoreDataLoading()

    setTitleCustom(value.title)

    when(isLoadMore){
      false -> alItem.clear()
    }

    alItem.addAll(value.rows)
  }

  override fun getApiDataFailure(msg: String) {
    hideMoreDataLoading()
  }
}