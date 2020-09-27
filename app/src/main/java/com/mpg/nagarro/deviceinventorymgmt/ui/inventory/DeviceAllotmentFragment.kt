package com.mpg.nagarro.deviceinventorymgmt.ui.inventory

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.mpg.nagarro.deviceinventorymgmt.R
import com.mpg.nagarro.deviceinventorymgmt.base.BaseFragment
import com.mpg.nagarro.deviceinventorymgmt.databinding.DeviceAllotmentFragmentBinding
import com.mpg.nagarro.deviceinventorymgmt.util.hideKeyboard
import java.util.*

class DeviceAllotmentFragment :
    BaseFragment<DeviceAllotmentFragmentBinding, DeviceAllotmentViewModel>(),
    AdapterView.OnItemSelectedListener {
    private val TAG = "DeviceAllotmentFragment"
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
            showDatePicker()
        }

        viewModel.employeeName.observe(viewLifecycleOwner, {
            autoCompleteInit(it)
        })

        viewModel.deviceName.observe(viewLifecycleOwner, {
            spinnerInit(it)
        })
    }

    @SuppressLint("SetTextI18n")
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            requireContext(), DatePickerDialog.OnDateSetListener
            { view, yearI, monthOfYear, dayOfMonth ->
                // Display Selected date in textbox
                val monthOfYear = monthOfYear + 1
                viewDataBinding.btnDatePicker.text =
                    StringBuffer("Returned Date: $dayOfMonth/$monthOfYear/$yearI")
            },
            year, month, day
        )

        dpd.datePicker.minDate = System.currentTimeMillis()
        dpd.show()
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

}