package com.mpg.nagarro.deviceinventorymgmt.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mpg.nagarro.deviceinventorymgmt.util.hideKeyboard

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {

    lateinit var viewDataBinding: VB

    abstract fun getLayout(): Int
    abstract fun onCreated(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayout())
        onCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        hideKeyboard()
    }
}