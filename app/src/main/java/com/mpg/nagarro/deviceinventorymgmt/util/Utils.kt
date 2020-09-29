package com.mpg.nagarro.deviceinventorymgmt.util

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.mpg.nagarro.deviceinventorymgmt.R
import com.mpg.nagarro.deviceinventorymgmt.data.entity.DeviceStatus

object Utils {
    /**
     * Kindly Don't change ENUMS numeric value from Utils other wise some functionality may cause ambiguity
     *
     * */
    fun enumToIntDeviceStatus(deviceStatus: DeviceStatus) =
        when (deviceStatus) {
            DeviceStatus.AVAILABLE -> 1
            DeviceStatus.ISSUED -> 2
            DeviceStatus.RETURNED -> 3
            DeviceStatus.LOST -> 4
        }

    fun intDeviceStatusToEnum(status: Int) =
        when (status) {
            1 -> DeviceStatus.AVAILABLE
            // 2-> DeviceStatus.ISSUED
            3 -> DeviceStatus.RETURNED
            4 -> DeviceStatus.LOST
            else -> DeviceStatus.ISSUED
        }

    /***************************************/

    fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    internal fun isNullOrEmpty(name: String?): Boolean {
        return (name == null || name.isEmpty())
    }


    fun <T> Fragment.showDialog(
        title: String, message: String,
        dialogCallBack: DialogCallBack<T>?,
        data: T
    ) {
        val builder =
            AlertDialog.Builder(requireContext(), R.style.ThemeOverlay_AppCompat_Dialog_Alert)
        //set title for alert dialog
        builder.setTitle(title)
        //set message for alert dialog
        builder.setMessage(message)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->
//            view?.showToast("Clicked Yes")
            dialogCallBack?.onPositiveButtonClicked(data)
        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
//            view?.showToast("Clicked No")
            dialogCallBack?.onNegativeButtonClicked(data)
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}

/**
 * To Get Dialog CallBack **/
interface DialogCallBack<T> {
    fun onPositiveButtonClicked(data: T)
    fun onNegativeButtonClicked(data: T) {

    }
}