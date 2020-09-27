package com.mpg.nagarro.deviceinventorymgmt.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mpg.nagarro.deviceinventorymgmt.BR
import com.mpg.nagarro.deviceinventorymgmt.factory.ViewModelFactory
import com.mpg.nagarro.deviceinventorymgmt.util.hideKeyboard
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<VB : ViewDataBinding, VM : ViewModel> : Fragment() {

    lateinit var viewDataBinding: VB
    lateinit var viewModel: VM

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    open fun getViewModelF():ViewModelProvider.Factory?{
        return null
    }

    abstract fun getLayout(): Int
    abstract fun getViewModel(): Class<VM>
    abstract fun onCreateView(rootView: View)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        AndroidSupportInjection.inject(this)

        viewModel = if (getViewModelF() != null)
            ViewModelProviders.of(this, getViewModelF()).get(getViewModel())
        else
            ViewModelProviders.of(this).get(getViewModel())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        viewDataBinding.setVariable(BR.viewModel, viewModel)
        viewDataBinding.executePendingBindings()
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreateView(viewDataBinding.root)
    }

    override fun onResume() {
        super.onResume()
        hideKeyboard()
    }
}