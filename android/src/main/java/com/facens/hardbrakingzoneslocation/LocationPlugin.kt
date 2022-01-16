package com.facens.hardbrakingzoneslocation

import android.Manifest
import com.getcapacitor.*
import com.getcapacitor.annotation.CapacitorPlugin
import com.getcapacitor.annotation.Permission
import com.getcapacitor.annotation.PermissionCallback
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Plugin that allows getting the user's position and speed each second, even
 * when the application is running on background.
 */
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

    /**
     * Property that defines the plugin logic.
     */
    private val implementation = Location()

    /**
     * Method that initializes the plugin loop.
     */
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

    /**
     * Method that is called when the user answers the location permission prompt.
     */
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

    /**
     * Method that is called when the user answers the location permission prompt.
     */
    @PermissionCallback
    fun backgroundLocationPermissionCallback(call: PluginCall) {
        if (getPermissionState(BACKGROUND_LOCATION) == PermissionState.GRANTED) {
            execute()
            return
        }

        call.reject("Permission is required to get the background location")
    }

    /**
     * Method that starts the plugin loop.
     */
    private fun execute() {
        setInterval(1000) {
            implementation.getLocation(context) { location ->
                val response = locationToJSObject(location)
                notifyListeners("location", response, true)
            }
        }
    }

    /**
     * Method that runs some callback each **milliseconds** interval.
     */
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

    /**
     * Method that converts an location object to a JSObject.
     */
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
