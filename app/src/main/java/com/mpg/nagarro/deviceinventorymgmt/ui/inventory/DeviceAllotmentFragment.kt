package com.mpg.nagarro.deviceinventorymgmt.ui.inventory

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.mpg.nagarro.deviceinventorymgmt.BR
import com.mpg.nagarro.deviceinventorymgmt.R
import com.mpg.nagarro.deviceinventorymgmt.base.BaseFragment
import com.mpg.nagarro.deviceinventorymgmt.databinding.DeviceAllotmentFragmentBinding
import com.mpg.nagarro.deviceinventorymgmt.util.showDatePicker

class DeviceAllotmentFragment :
    BaseFragment<DeviceAllotmentFragmentBinding, DeviceAllotmentViewModel>(),
    AdapterView.OnItemSelectedListener {
    private val TAG = "DeviceAllotmentFragment"

    override val bindingVariable: Int
        get() = BR.viewmodel

    override fun getLayout() = R.layout.device_allotment_fragment

    override fun getViewModel() = DeviceAllotmentViewModel::class.java

    override fun onCreateView(rootView: View) {
        initViews()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d(TAG, "onItemSelected() called = $position")
    }

    //
    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.i(TAG, "onNothingSelected: $parent")
    }

    private fun initViews() {

        viewDataBinding.btnDatePicker.setOnClickListener {
            it.showDatePicker()
        }

        viewModel.employeeNames.observe(viewLifecycleOwner, {
            autoCompleteInit(it)
        })

        viewModel.deviceNames.observe(viewLifecycleOwner, {
            spinnerInit(it)
        })

        viewDataBinding.autoTextViewEmployeeName.threshold = 1
        viewDataBinding.autoTextViewEmployeeName.setOnItemClickListener { parent, view, position, id ->
            Log.i(TAG, "setOnItemClickListener: $view ===  $position")
        }

        viewDataBinding.btnSubmit.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val deviceName = viewDataBinding.spinner.selectedItem?.toString()
        val employeeName = viewDataBinding.autoTextViewEmployeeName.editableText
        val returnDate = viewDataBinding.btnDatePicker.text.split(":")[1].trim()
        Log.i(TAG, "$employeeName==setOnItemClickListener=saveData: $deviceName")
        Log.i(TAG, "$returnDate==setOnItemClickListener=saveData: $deviceName")
        deviceName?.let {
            val employeeEntity = viewModel.getEmployeeEntity(employeeName.toString())
            val deviceEntity = viewModel.getDeviceEntity(deviceName)

            if (employeeEntity == null || deviceEntity == null)
                return
            viewModel.saveData(deviceEntity, employeeEntity, returnDate)
        }
    }


    /**
     *Set Employee Name List*/
    private fun autoCompleteInit(dataList: List<String>) {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_activated_1,
            dataList
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewDataBinding.autoTextViewEmployeeName.setAdapter(adapter)
        viewDataBinding.autoTextViewEmployeeName.onItemSelectedListener = this

        viewModel.isLoading.observe(viewLifecycleOwner, {
            if (!it)
                gotBack()
        })
    }

    /**
     *Set Device Name List*/
    private fun spinnerInit(dataList: List<String>) {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            dataList
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewDataBinding.spinner.adapter = adapter
        viewDataBinding.spinner.onItemSelectedListener = this
    }

    private fun gotBack() {
        findNavController().popBackStack()
    }
}