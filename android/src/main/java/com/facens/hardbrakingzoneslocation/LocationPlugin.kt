package com.facens.hardbrakingzoneslocation

import android.Manifest
import com.getcapacitor.*
import com.getcapacitor.annotation.CapacitorPlugin
import com.getcapacitor.annotation.Permission
import com.getcapacitor.annotation.PermissionCallback

@CapacitorPlugin(
    name = "Location",
    permissions = [
        Permission(
            alias = LocationPlugin.LOCATION,
            strings = [
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ],
        )
    ]
)
class LocationPlugin : Plugin() {
    companion object {
        const val LOCATION = "location"
    }

    private val implementation = Location()

    @PluginMethod
    fun getLocation(call: PluginCall?) {
        if (call == null) {
            throw IllegalArgumentException("plugin call expected")
        }

        if (getPermissionState(LOCATION) != PermissionState.GRANTED) {
            requestPermissionForAlias(LOCATION, call, "locationPermissionCallback")
        } else {
            implementation.getLocation(context) { location ->
                resolve(call, location)
            }
        }
    }

    @PermissionCallback
    fun locationPermissionCallback(call: PluginCall) {
        if (getPermissionState(LOCATION) == PermissionState.GRANTED) {
            implementation.getLocation(context) { location ->
                resolve(call, location)
            }
        } else {
            call.reject("Permission is required to get the location")
        }
    }

    private fun resolve(call: PluginCall, locationData: LocationData) {
        val response = JSObject()
        response.put("deviceId", locationData.deviceId)
        response.put("accuracy", locationData.accuracy)
        response.put("longitude", locationData.longitude)
        response.put("latitude", locationData.latitude)
        response.put("speed", locationData.speed)
        call.resolve(response)
    }
}
