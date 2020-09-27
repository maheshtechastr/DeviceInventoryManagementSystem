package com.mpg.nagarro.deviceinventorymgmt.ui.employee

import android.view.View
import androidx.navigation.fragment.findNavController
import com.mpg.nagarro.deviceinventorymgmt.R
import com.mpg.nagarro.deviceinventorymgmt.base.BaseFragment
import com.mpg.nagarro.deviceinventorymgmt.databinding.AddEditEmployeeFragmentBinding

class AddEditEmployeeFragment :
    BaseFragment<AddEditEmployeeFragmentBinding, AddEditEmployeeViewModel>() {

    override fun getLayout() = R.layout.add_edit_employee_fragment

    override fun getViewModel() = AddEditEmployeeViewModel::class.java

    override fun onCreateView(rootView: View) {
        viewDataBinding.btnAddEmployee.setOnClickListener {
            viewModel.addEmployee()
            findNavController().popBackStack()
        }

    }
}