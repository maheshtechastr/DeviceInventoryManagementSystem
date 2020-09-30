package com.mpg.nagarro.deviceinventorymgmt.util

interface ExporterListener {
    fun success(s: String)

    fun fail(message: String,exception:String)
}