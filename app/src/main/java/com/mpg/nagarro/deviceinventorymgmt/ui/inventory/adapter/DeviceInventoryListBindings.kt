package com.mpg.nagarro.deviceinventorymgmt.ui.inventory.adapter

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mpg.nagarro.deviceinventorymgmt.R
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceInventory
import com.mpg.nagarro.deviceinventorymgmt.util.Utils


/**
 * [BindingAdapter]s for the [DeviceInventory]s list.
 */
@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<DeviceInventory>?) {
    items?.let {
        (listView.adapter as DeviceInventoryListAdapter).submitList(items)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("app:text")
fun setStatus(textView: TextView, status: Int) {
    textView.text =
        textView.context.getString(R.string.device_status) + Utils.intDeviceStatusToEnum(status).name
}

//@BindingAdapter("app:completedTask")
//fun setStyle(textView: TextView, enabled: Boolean) {
//    if (enabled) {
//        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
//    } else {
//        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
//    }
//}