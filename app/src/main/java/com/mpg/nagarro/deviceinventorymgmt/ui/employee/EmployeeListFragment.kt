package com.mpg.nagarro.deviceinventorymgmt.ui.employee

import android.view.View
import androidx.navigation.fragment.findNavController
import com.mpg.nagarro.deviceinventorymgmt.BR
import com.mpg.nagarro.deviceinventorymgmt.R
import com.mpg.nagarro.deviceinventorymgmt.base.BaseFragment
import com.mpg.nagarro.deviceinventorymgmt.data.entity.EmployeeEntity
import com.mpg.nagarro.deviceinventorymgmt.databinding.EmployeeListFragmentBinding
import com.mpg.nagarro.deviceinventorymgmt.ui.employee.adapter.EmployeeListAdapter
import com.mpg.nagarro.deviceinventorymgmt.util.showSnackbar
import javax.inject.Inject

class EmployeeListFragment : BaseFragment<EmployeeListFragmentBinding, EmployeeListViewModel>(),
    EmployeeListAdapter.OnItemClickListener {


    override val bindingVariable: Int
        get() = BR.viewmodel

    @Inject
    lateinit var listAdapter: EmployeeListAdapter

    override fun getLayout() = R.layout.employee_list_fragment

    override fun getViewModel() = EmployeeListViewModel::class.java

    override fun onCreateView(rootView: View) {

        setUpAdapter()

        viewDataBinding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.addEditEmployeeFragment)
        }
//        viewModel.employees.observe(viewLifecycleOwner, {
//            listAdapter.submitList(it)
//        })
        viewModel.showMessage.observe(viewLifecycleOwner, {
            viewDataBinding.root.showSnackbar(it)
        })
    }

    /**Add Adapter to view*/
    private fun setUpAdapter() {
        listAdapter.setItemClickListener(this)
        viewDataBinding.employeeList.adapter = listAdapter
    }

    override fun onItemClicked(position: Int, item: EmployeeEntity) {
        viewModel.deleteRow(item)
    }

}