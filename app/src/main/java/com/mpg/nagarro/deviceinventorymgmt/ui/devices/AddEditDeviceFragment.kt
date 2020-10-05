package com.mpg.nagarro.deviceinventorymgmt.ui.devices

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mpg.nagarro.deviceinventorymgmt.BR
import com.mpg.nagarro.deviceinventorymgmt.R
import com.mpg.nagarro.deviceinventorymgmt.base.BaseFragment
import com.mpg.nagarro.deviceinventorymgmt.databinding.AddEditDeviceFragmentBinding
import com.mpg.nagarro.deviceinventorymgmt.ui.employee.AddEditEmployeeFragmentArgs
import com.mpg.nagarro.deviceinventorymgmt.util.showSnackbar
import timber.log.Timber

class AddEditDeviceFragment :
    BaseFragment<AddEditDeviceFragmentBinding, AddEditDeviceViewModel>() {

    private val args: AddEditEmployeeFragmentArgs by navArgs()

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun getLayout() = R.layout.add_edit_device_fragment

    override fun getViewModel() = AddEditDeviceViewModel::class.java

    override fun onCreateView(rootView: View) {

        Timber.i("onCreateView: $rootView")

        viewModel.taskUpdatedEvent.observe(viewLifecycleOwner, {
            getBack()
        })

        viewModel.showMessage.observe(viewLifecycleOwner, { msg ->
            msg?.let { viewDataBinding.root.showSnackbar(it) }
        })
    }

    private fun getBack() {
        findNavController().popBackStack()
    }
}