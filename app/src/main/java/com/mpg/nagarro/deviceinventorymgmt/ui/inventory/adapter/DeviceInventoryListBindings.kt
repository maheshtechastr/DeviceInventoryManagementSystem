package com.mpg.nagarro.deviceinventorymgmt.ui.inventory.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mpg.nagarro.deviceinventorymgmt.R
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceInventory
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceStatus
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

@BindingAdapter("app:enabled")
fun isActive(checkBox: CheckBox, status: Int) {
    checkBox.isEnabled = (status == Utils.enumToIntDeviceStatus(DeviceStatus.ISSUED))
}

@BindingAdapter("app:isDateOver")
fun changeAlertColor(viewGroup: View, deviceInventory: DeviceInventory) {
    if (System.currentTimeMillis() > deviceInventory.returnDate.time) {
        viewGroup.setBackgroundColor(Color.parseColor("#9DCDB6"))
    } else
        viewGroup.setBackgroundColor(Color.parseColor("#00000000"))
}
