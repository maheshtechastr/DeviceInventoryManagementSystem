package com.mpg.nagarro.deviceinventorymgmt.ui.devices.adapter

import android.graphics.Paint
import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceEntity
import java.util.*


private const val TAG = "DeviceListBindings"

/**
 * [BindingAdapter]s for the [DeviceEntity]s list.
 */
@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<DeviceEntity>?) {
    Log.i(TAG, "observe setItems: $items");
    items?.let {
        (listView.adapter as DeviceListAdapter).submitList(items)
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

@BindingAdapter("app:text")
fun setStringFromAny(textView: TextView, any: Int) {
    textView.text = any.toString()
}
@BindingAdapter("app:text")
fun setStringFromAny(textView: TextView, date: Date) {
    textView.text = date.toString()
}