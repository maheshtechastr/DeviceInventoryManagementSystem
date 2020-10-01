package com.mpg.nagarro.deviceinventorymgmt.ui.inventory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceInventory
import com.mpg.nagarro.deviceinventorymgmt.databinding.InventoryListItemBinding
import com.mpg.nagarro.deviceinventorymgmt.ui.inventory.DeviceAllottedListViewModel
import com.mpg.nagarro.deviceinventorymgmt.ui.inventory.adapter.DeviceInventoryListAdapter.ViewHolder
import javax.inject.Inject

/**
 * Adapter for the deviceInventory list. Has a reference to the [DeviceAllottedListViewModel] to send actions back to it.
 */
class DeviceInventoryListAdapter @Inject constructor(private val deviceAllottedListViewModel: DeviceAllottedListViewModel) :
    ListAdapter<DeviceInventory, ViewHolder>(DeviceInventoryDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item, deviceAllottedListViewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: InventoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DeviceInventory, viewModel: DeviceAllottedListViewModel) {
            binding.viewModel = viewModel
            binding.deviceinfo = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = InventoryListItemBinding.inflate(layoutInflater, parent, false)
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
class DeviceInventoryDiffCallback : DiffUtil.ItemCallback<DeviceInventory>() {
    override fun areItemsTheSame(oldItem: DeviceInventory, newItem: DeviceInventory): Boolean {
        return oldItem.deviceId == newItem.deviceId
    }

    override fun areContentsTheSame(oldItem: DeviceInventory, newItem: DeviceInventory): Boolean {
        return oldItem == newItem
    }
}
