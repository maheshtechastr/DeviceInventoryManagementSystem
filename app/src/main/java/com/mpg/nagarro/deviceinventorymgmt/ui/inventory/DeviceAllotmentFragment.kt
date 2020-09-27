package com.mpg.nagarro.deviceinventorymgmt.ui.inventory

import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.mpg.nagarro.deviceinventorymgmt.R
import com.mpg.nagarro.deviceinventorymgmt.base.BaseFragment
import com.mpg.nagarro.deviceinventorymgmt.databinding.DeviceListFragmentBinding
import kotlinx.android.synthetic.main.device_allotment_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class DeviceAllotmentFragment :
    BaseFragment<DeviceListFragmentBinding, DeviceAllotmentViewModel>(), View.OnClickListener,
    AdapterView.OnItemSelectedListener {

    override fun getLayout() = R.layout.device_allotment_fragment

    override fun getViewModel() = DeviceAllotmentViewModel::class.java

    override fun onCreateView(rootView: View) {
        initViews()
    }

    private var users =
        arrayOf("Suresh Dasari", "Trishika Dasari", "Rohini Alavala", "Praveen Kumar", "Madhav Sai")
    private var isDatePickerVisible = true

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.date_picker_btn -> {
                date_picker_btn.isEnabled = false
                Handler().postDelayed({
                    date_picker_btn.isEnabled = true

                    isDatePickerVisible = !isDatePickerVisible
                    if (isDatePickerVisible) {
                        datePicker.visibility = View.VISIBLE
                        selected_date_txt_view.visibility = View.VISIBLE
                        selected_date_txt_view.text = getCurrentDate()
                    } else {
                        datePicker.visibility = View.GONE
                    }
                }, 100)
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(context, "Selected User: " + users[position], Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    private fun initViews(){
        selected_date_txt_view.visibility = View.VISIBLE
        selected_date_txt_view.text = getCurrentDate()
        date_picker_btn.setOnClickListener(this)
        datePickerInit()
        spinnerInit()
    }

    private fun datePickerInit(){
        val today = Calendar.getInstance()
        datePicker.init(
            today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)

        ) { _myView, year, month, day ->

            val month = month + 1

            selected_date_txt_view.visibility = View.VISIBLE
            selected_date_txt_view.text = "$day/$month/$year"
            datePicker.visibility = View.GONE
            isDatePickerVisible = !isDatePickerVisible
        }
        datePicker.minDate = System.currentTimeMillis()

    }

    private fun spinnerInit(){
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, users)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    private fun getCurrentDate(): String {
        val c = Calendar.getInstance()

        val df = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
        val formattedDate: String = df.format(c.time)
        // formattedDate have current date/time
        // formattedDate have current date/time
        return formattedDate
    }
}