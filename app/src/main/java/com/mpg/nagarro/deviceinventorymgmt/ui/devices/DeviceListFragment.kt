package com.mpg.nagarro.deviceinventorymgmt.ui.devices

import android.view.View
import androidx.navigation.fragment.findNavController
import com.mpg.nagarro.deviceinventorymgmt.BR
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

    override val bindingVariable: Int
        get() = BR.viewModel

    @Inject
    lateinit var listAdapter: DeviceListAdapter

    override fun getLayout() = R.layout.device_list_fragment

    override fun getViewModel() = DeviceListViewModel::class.java

    override fun onCreateView(rootView: View) {
        setUpAdapter()

        viewDataBinding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.add_edit_device_fragment)
        }

        listAdapter.setItemClickListener(this)

        viewModel.showMessage.observe(viewLifecycleOwner, {
            viewDataBinding.root.showSnackbar(it)

        })
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