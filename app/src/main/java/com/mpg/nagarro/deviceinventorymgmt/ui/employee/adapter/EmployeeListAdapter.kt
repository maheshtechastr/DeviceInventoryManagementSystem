package com.mpg.nagarro.deviceinventorymgmt.ui.employee.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mpg.nagarro.deviceinventorymgmt.data.entity.EmployeeEntity
import com.mpg.nagarro.deviceinventorymgmt.databinding.EmployeeListItemBinding
import com.mpg.nagarro.deviceinventorymgmt.ui.employee.EmployeeListViewModel
import javax.inject.Inject

/**
 * Adapter for the deviceInventory list. Has a reference to the [EmployeeListViewModel] to send actions back to it.
 */
class EmployeeListAdapter @Inject constructor() :
    ListAdapter<EmployeeEntity, EmployeeListAdapter.ViewHolder>(EmployeeDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: EmployeeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EmployeeEntity) {

            binding.employeeinfo = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = EmployeeListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class EmployeeDiffCallback : DiffUtil.ItemCallback<EmployeeEntity>() {
    override fun areItemsTheSame(oldItem: EmployeeEntity, newItem: EmployeeEntity): Boolean {
        return oldItem.empId == newItem.empId
    }

    override fun areContentsTheSame(oldItem: EmployeeEntity, newItem: EmployeeEntity): Boolean {
        return oldItem == newItem
    }
}
