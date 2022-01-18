package com.facens.hardbrakingzoneslocation

data class LocationData(
  val deviceId: String,
  val accuracy: Float,
  val longitude: String,
  val latitude: String,
  val speed: Float
)
