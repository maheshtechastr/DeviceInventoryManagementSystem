package com.mpg.nagarro.deviceinventorymgmt.ui.employee.adapter

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceInventory
import com.mpg.nagarro.deviceinventorymgmt.data.entity.EmployeeEntity


/**
 * [BindingAdapter]s for the [EmployeeEntity]s list.
 */
@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<EmployeeEntity>?) {
    items?.let {
        (listView.adapter as EmployeeListAdapter).submitList(items)
    }
}

//@BindingAdapter("app:completedTask")
//fun setStyle(textView: TextView, enabled: Boolean) {
//    if (enabled) {
//        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
//    } else {
//        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
//    }
//}