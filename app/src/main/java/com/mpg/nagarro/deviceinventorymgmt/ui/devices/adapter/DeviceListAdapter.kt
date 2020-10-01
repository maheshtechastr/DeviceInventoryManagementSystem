package com.mpg.nagarro.deviceinventorymgmt.ui.devices.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import com.mpg.nagarro.deviceinventorymgmt.databinding.DeviceListItemBinding
import com.mpg.nagarro.deviceinventorymgmt.ui.devices.DeviceListViewModel
import javax.inject.Inject

/**
 * Adapter for the deviceInventory list. Has a reference to the [DeviceListViewModel] to send actions back to it.
 */
class DeviceListAdapter @Inject constructor(private val viewModel: DeviceListViewModel) :
    ListAdapter<DeviceEntity, DeviceListAdapter.ViewHolder>(DeviceDiffCallback()) {
    private var itemClickListener: OnDeviceItemClickListener? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, viewModel, itemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: DeviceListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: DeviceEntity,
            viewModel: DeviceListViewModel,
            itemClickListener: OnDeviceItemClickListener?
        ) {
            binding.deviceinfo = item
            binding.viewModel = viewModel
            binding.ivDeleteRow.setOnClickListener {
                itemClickListener?.onItemClicked(adapterPosition, item)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DeviceListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    fun setItemClickListener(itemClicked: OnDeviceItemClickListener) {
        this.itemClickListener = itemClicked
    }
}

interface OnDeviceItemClickListener {
    fun onItemClicked(position: Int, item: DeviceEntity)
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class DeviceDiffCallback : DiffUtil.ItemCallback<DeviceEntity>() {
    override fun areItemsTheSame(oldItem: DeviceEntity, newItem: DeviceEntity): Boolean {
        return oldItem.deviceId == newItem.deviceId
    }

    override fun areContentsTheSame(oldItem: DeviceEntity, newItem: DeviceEntity): Boolean {
        return oldItem == newItem
    }
}
