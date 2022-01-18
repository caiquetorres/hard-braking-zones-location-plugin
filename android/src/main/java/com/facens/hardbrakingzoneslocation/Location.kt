package com.facens.hardbrakingzoneslocation

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

@SuppressLint("MissingPermission")
class Location {
  private var fusedLocationClient: FusedLocationProviderClient? = null

  /**
   * Method that gets the user's location.
   */
  fun getLocation(context: Context, callback: (locationData: LocationData) -> Unit) {
    if (fusedLocationClient == null) {
      fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
      fusedLocationClient!!.requestLocationUpdates(
        this.createLocationRequest(),
        object : LocationCallback() {},
        null
      )
    }

    fusedLocationClient!!.lastLocation.addOnSuccessListener { location ->
      if (location != null) {
        callback(
          LocationData(
            deviceId = getDeviceId(context),
            accuracy = location.speedAccuracyMetersPerSecond,
            longitude = location.longitude.toString(),
            latitude = location.latitude.toString(),
            speed = location.speed
          )
        )
      }
    }
  }

  /**
   * Method that gets the user's device id.
   */
  @SuppressLint("HardwareIds")
  private fun getDeviceId(context: Context): String {
    return Settings.Secure.getString(
      context.contentResolver,
      Settings.Secure.ANDROID_ID
    )
  }

  private fun createLocationRequest(): LocationRequest {
    val locationRequest = LocationRequest.create()
    locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    locationRequest.interval = 500
    locationRequest.fastestInterval = 250
    return locationRequest
  }
}
