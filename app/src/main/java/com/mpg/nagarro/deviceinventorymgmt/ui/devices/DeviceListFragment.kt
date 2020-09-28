package com.mpg.nagarro.deviceinventorymgmt.ui.devices

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mpg.nagarro.deviceinventorymgmt.MyApplication
import com.mpg.nagarro.deviceinventorymgmt.R
import com.mpg.nagarro.deviceinventorymgmt.base.BaseFragment
import com.mpg.nagarro.deviceinventorymgmt.databinding.DeviceListFragmentBinding
import com.mpg.nagarro.deviceinventorymgmt.factory.ViewModelFactory
import com.mpg.nagarro.deviceinventorymgmt.ui.devices.adapter.DeviceListAdapter
import javax.inject.Inject

class DeviceListFragment : BaseFragment<DeviceListFragmentBinding, DeviceListViewModel>() {

    @Inject
    lateinit var listAdapter: DeviceListAdapter

    override fun getLayout() = R.layout.device_list_fragment

    override fun getViewModel() = DeviceListViewModel::class.java

    private val TAG = "DeviceListFragment"

    override fun onCreateView(rootView: View) {
        setUpAdapter()

        viewDataBinding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.add_edit_device_fragment)
        }

        viewModel.devices.observe(viewLifecycleOwner, {
            Log.i(TAG, "observe: $it")
            listAdapter.submitList(it)
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun setUpAdapter() {
        viewDataBinding.deviceList.adapter = listAdapter
    }
}