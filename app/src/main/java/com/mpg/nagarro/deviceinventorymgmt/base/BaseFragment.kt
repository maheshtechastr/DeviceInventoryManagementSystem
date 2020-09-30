package com.mpg.nagarro.deviceinventorymgmt.base

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mpg.nagarro.deviceinventorymgmt.R
import com.mpg.nagarro.deviceinventorymgmt.util.hideKeyboard
import com.mpg.nagarro.deviceinventorymgmt.util.observe
import com.mpg.nagarro.deviceinventorymgmt.util.showSnackbar
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    lateinit var viewDataBinding: VB
    lateinit var viewModel: VM
    abstract val bindingVariable: Int

    private lateinit var progressAlertDialog: AlertDialog

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract fun getLayout(): Int
    abstract fun getViewModel(): Class<VM>
    abstract fun onCreateView(rootView: View)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        /*
         *  Remember in our FragmentModule, we
         * defined MovieListFragment injection? So we need
         * to call this method in order to inject the
         * ViewModelFactory into our Fragment
         * */
        AndroidSupportInjection.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false)

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        viewDataBinding.setVariable(bindingVariable, viewModel)

        onCreateView(viewDataBinding.root)
    }

    /**
     * Method to observer loading
     */
    internal fun observeLoading(loadingInProgress: Boolean) {
        if (loadingInProgress) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    protected fun showLoading() {
        hideLoading()
        val dialogBuilder = AlertDialog.Builder(context)
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        val dialogView = inflater?.inflate(R.layout.dialog_progress, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)
        progressAlertDialog = dialogBuilder.create()
        progressAlertDialog.show()
        progressAlertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

    }

    protected fun hideLoading() {
        if (::progressAlertDialog.isInitialized)
            progressAlertDialog.dismiss()
    }

    private fun handleObserver() {
        observe(viewModel.showMessage, ::observeError)
        observe(viewModel.isLoading, ::observeLoading)
    }

    internal fun observeError(message: String) {
        view?.showSnackbar(message)
    }

    override fun onResume() {
        super.onResume()
        hideKeyboard()
    }
}