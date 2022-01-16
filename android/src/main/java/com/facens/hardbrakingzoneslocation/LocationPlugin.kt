package com.facens.hardbrakingzoneslocation

import android.Manifest
import com.getcapacitor.*
import com.getcapacitor.annotation.CapacitorPlugin
import com.getcapacitor.annotation.Permission
import com.getcapacitor.annotation.PermissionCallback
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@CapacitorPlugin(
        name = "Location",
        permissions = [
            Permission(
                    alias = LocationPlugin.LOCATION,
                    strings = [
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                    ],
            ),
            Permission(
                    alias = LocationPlugin.BACKGROUND_LOCATION,
                    strings = [
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION
                    ]
            )
        ]
)
class LocationPlugin : Plugin() {
    companion object {
        const val LOCATION = "location"
        const val BACKGROUND_LOCATION = "background_location"
    }

    private val implementation = Location()

    private fun execute() {
        setInterval(1000) {
            implementation.getLocation(context) { location ->
                val response = locationToJSObject(location)
                notifyListeners("location", response, true)
            }
        }
    }

    @PluginMethod
    fun init(call: PluginCall) {
        if (getPermissionState(LOCATION) == PermissionState.GRANTED &&
                getPermissionState(BACKGROUND_LOCATION) == PermissionState.GRANTED) {
            execute()
            return
        }

        if (getPermissionState(LOCATION) != PermissionState.GRANTED) {
            requestPermissionForAlias(LOCATION, call, "locationPermissionCallback")
            return
        }

        if (getPermissionState(BACKGROUND_LOCATION) != PermissionState.GRANTED) {
            requestPermissionForAlias(BACKGROUND_LOCATION, call, "backgroundLocationPermissionCallback")
            return
        }
    }

    @PermissionCallback
    fun locationPermissionCallback(call: PluginCall) {
        if (getPermissionState(LOCATION) == PermissionState.GRANTED) {
            if (getPermissionState(BACKGROUND_LOCATION) != PermissionState.GRANTED) {
                requestPermissionForAlias(BACKGROUND_LOCATION, call, "backgroundLocationPermissionCallback")
                return
            }
            execute()
            return
        }

        call.reject("Permission is required to get the location")
    }

    @PermissionCallback
    fun backgroundLocationPermissionCallback(call: PluginCall) {
        if (getPermissionState(BACKGROUND_LOCATION) == PermissionState.GRANTED) {
            execute()
            return
        }

        call.reject("Permission is required to get the background location")
    }

    private fun setInterval(milliseconds: Long, handler: () -> Unit) {
        runBlocking {
            launch {
                while (true) {
                    delay(milliseconds)
                    handler()
                }
            }
        }
    }

    private fun locationToJSObject(locationData: LocationData): JSObject {
        val response = JSObject()
        response.put("deviceId", locationData.deviceId)
        response.put("accuracy", locationData.accuracy)
        response.put("longitude", locationData.longitude)
        response.put("latitude", locationData.latitude)
        response.put("speed", locationData.speed)
        return response
    }
}
