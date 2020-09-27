package com.mpg.nagarro.deviceinventorymgmt.ui.employee

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mpg.nagarro.deviceinventorymgmt.MyApplication
import com.mpg.nagarro.deviceinventorymgmt.R
import com.mpg.nagarro.deviceinventorymgmt.base.BaseFragment
import com.mpg.nagarro.deviceinventorymgmt.databinding.AddEditEmployeeFragmentBinding
import com.mpg.nagarro.deviceinventorymgmt.factory.ViewModelFactory
import javax.inject.Inject

class AddEditEmployeeFragment :
    BaseFragment<AddEditEmployeeFragmentBinding, AddEditEmployeeViewModel>() {

//    @Inject
//    lateinit var viewModelFactory: ViewModelFactory
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        (requireActivity().application as MyApplication).appComponent.inject(this)
//    }
//
//    override fun getViewModelF(): ViewModelProvider.Factory? {
//        return viewModelFactory
//    }

    override fun getLayout() = R.layout.add_edit_employee_fragment

    override fun getViewModel() = AddEditEmployeeViewModel::class.java

    override fun onCreateView(rootView: View) {
        viewDataBinding.btnAddEmployee.setOnClickListener {
            viewModel.addEmployee()
            findNavController().popBackStack()
        }

    }
}