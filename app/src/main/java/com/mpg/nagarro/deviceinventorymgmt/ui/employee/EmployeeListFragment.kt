package com.mpg.nagarro.deviceinventorymgmt.ui.employee

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mpg.nagarro.deviceinventorymgmt.MyApplication
import com.mpg.nagarro.deviceinventorymgmt.R
import com.mpg.nagarro.deviceinventorymgmt.base.BaseFragment
import com.mpg.nagarro.deviceinventorymgmt.databinding.EmployeeListFragmentBinding
import com.mpg.nagarro.deviceinventorymgmt.factory.ViewModelFactory
import com.mpg.nagarro.deviceinventorymgmt.ui.employee.adapter.EmployeeListAdapter
import javax.inject.Inject

class EmployeeListFragment : BaseFragment<EmployeeListFragmentBinding, EmployeeListViewModel>() {

//    @Inject
//    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var listAdapter: EmployeeListAdapter

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        (requireActivity().application as MyApplication).appComponent.inject(this)
//    }
//
//    override fun getViewModelF(): ViewModelProvider.Factory? {
//        return viewModelFactory
//    }

    override fun getLayout() = R.layout.employee_list_fragment

    override fun getViewModel() = EmployeeListViewModel::class.java

    override fun onCreateView(rootView: View) {

        setUpAdapter()

        viewDataBinding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.addEditEmployeeFragment)
        }
        viewModel.employees.observe(viewLifecycleOwner, {
            listAdapter.submitList(it)
        })
    }

    /**Add Adapter to view*/
    private fun setUpAdapter() {
        viewDataBinding.employeeList.adapter = listAdapter
    }

}