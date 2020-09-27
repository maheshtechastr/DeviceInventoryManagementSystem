package com.mpg.nagarro.deviceinventorymgmt.ui.inventory

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mpg.nagarro.deviceinventorymgmt.MyApplication
import com.mpg.nagarro.deviceinventorymgmt.R
import com.mpg.nagarro.deviceinventorymgmt.base.BaseFragment
import com.mpg.nagarro.deviceinventorymgmt.databinding.DeviceAllottedListFragmentBinding
import com.mpg.nagarro.deviceinventorymgmt.factory.ViewModelFactory
import com.mpg.nagarro.deviceinventorymgmt.ui.inventory.adapter.DeviceInventoryListAdapter
import javax.inject.Inject

class DeviceAllottedListFragment :
    BaseFragment<DeviceAllottedListFragmentBinding, DeviceAllottedListViewModel>() {

//    @Inject
//    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var listAdapter: DeviceInventoryListAdapter

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        (requireActivity().application as MyApplication).appComponent.inject(this)
//    }
//
//    override fun getViewModelF(): ViewModelProvider.Factory? {
//        return viewModelFactory
//    }

    override fun getLayout() = R.layout.device_allotted_list_fragment

    override fun getViewModel() = DeviceAllottedListViewModel::class.java

    override fun onCreateView(rootView: View) {

        setupAdapter()

        viewDataBinding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.deviceAllotmentFragment)
        }

    }

    private fun setupAdapter() {
        viewDataBinding.inventoryList.adapter = listAdapter
    }
}