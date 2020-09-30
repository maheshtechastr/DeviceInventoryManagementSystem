package com.mpg.nagarro.deviceinventorymgmt.ui.inventory

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import com.mpg.nagarro.deviceinventorymgmt.BR
import com.mpg.nagarro.deviceinventorymgmt.R
import com.mpg.nagarro.deviceinventorymgmt.base.BaseFragment
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceStatus
import com.mpg.nagarro.deviceinventorymgmt.databinding.DeviceAllottedListFragmentBinding
import com.mpg.nagarro.deviceinventorymgmt.ui.inventory.adapter.DeviceInventoryListAdapter
import com.mpg.nagarro.deviceinventorymgmt.util.EventObserver
import javax.inject.Inject

class DeviceAllottedListFragment :
    BaseFragment<DeviceAllottedListFragmentBinding, DeviceAllottedListViewModel>() {
    private val TAG = "DeviceAllottedListFragm"

    override val bindingVariable: Int
        get() = BR.viewmodel

    @Inject
    lateinit var listAdapter: DeviceInventoryListAdapter

    override fun getLayout() = R.layout.device_allotted_list_fragment

    override fun getViewModel() = DeviceAllottedListViewModel::class.java

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.inventory_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val result: Boolean
        when (item.itemId) {
            R.id.menu_filter -> {
                showFilteringPopUpMenu()
                result = true
            }
            else -> {
                result = false
            }

        }
        return result || super.onOptionsItemSelected(item)
    }

    override fun onCreateView(rootView: View) {
        setHasOptionsMenu(true)
        setupFabAction()
        setupAdapter()
    }

    private fun setupAdapter() {
        viewDataBinding.inventoryList.adapter = listAdapter
    }

    private fun showFilteringPopUpMenu() {
        val view = activity?.findViewById<View>(R.id.menu_filter) ?: return
        PopupMenu(requireContext(), view).run {
            menuInflater.inflate(R.menu.filter_inventory, menu)

            setOnMenuItemClickListener {
                viewModel.setFiltering(
                    when (it.itemId) {
                        R.id.menu_returned_filter -> {
                            DeviceStatus.RETURNED
                        }
                        R.id.menu_issued_filter -> {
                            DeviceStatus.ISSUED
                        }
                        R.id.menu_lost_filter -> {
                            DeviceStatus.LOST
                        }
                        else -> {
                            DeviceStatus.AVAILABLE
                        }
                    }
                )
                true
            }
            show()
        }
    }

    private fun setupNavigation() {
        viewModel.openDeviceInventoryEvent.observe(this, EventObserver {
            openTaskDetails(it)
        })
        viewModel.newDeviceInventoryEvent.observe(this, EventObserver {
            navigateToAddNewTask()
        })
    }

    private fun setupFabAction() {
        viewDataBinding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.deviceAllotmentFragment)
        }
    }

    private fun navigateToAddNewTask() {
//        val action = TasksFragmentDirections
//            .actionTasksFragmentToAddEditTaskFragment(
//                null,
//                resources.getString(R.string.add_task)
//            )
//        findNavController().navigate(action)
    }

    private fun openTaskDetails(taskId: String) {
//        val action = TasksFragmentDirections.actionTasksFragmentToTaskDetailFragment(taskId)
//        findNavController().navigate(action)
    }

}