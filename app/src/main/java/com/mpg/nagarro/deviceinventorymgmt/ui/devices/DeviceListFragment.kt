package com.mpg.nagarro.deviceinventorymgmt.ui.devices

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.mpg.nagarro.deviceinventorymgmt.R
import com.mpg.nagarro.deviceinventorymgmt.base.BaseFragment
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import com.mpg.nagarro.deviceinventorymgmt.databinding.DeviceListFragmentBinding
import com.mpg.nagarro.deviceinventorymgmt.ui.devices.adapter.DeviceListAdapter
import com.mpg.nagarro.deviceinventorymgmt.ui.devices.adapter.OnDeviceItemClickListener
import com.mpg.nagarro.deviceinventorymgmt.util.DialogCallBack
import com.mpg.nagarro.deviceinventorymgmt.util.Utils.showDialog
import com.mpg.nagarro.deviceinventorymgmt.util.showSnackbar
import javax.inject.Inject

class DeviceListFragment : BaseFragment<DeviceListFragmentBinding, DeviceListViewModel>(),
    OnDeviceItemClickListener, DialogCallBack<DeviceEntity> {

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

        listAdapter.setItemClickListener(this)

        viewModel.showMessage.observe(viewLifecycleOwner, {
//            viewDataBinding.root.showToast(it, Toast.LENGTH_LONG)
            viewDataBinding.root.showSnackbar(it)

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

    override fun onItemClicked(position: Int, item: DeviceEntity) {
        showDialog("Device : ${item.name}", getString(R.string.alert_message), this, item)
    }

    override fun onPositiveButtonClicked(data: DeviceEntity) {
        viewModel.deleteRowAction(data)
    }

}