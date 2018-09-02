package com.dreamit.currentweatherapp.utils

import java.text.DecimalFormat

private const val KELVINS_TO_CELSIUS_PATTERN = 273.15

fun getCelsiusFormKelvins(kelvins: Double): Double {
    val decimalFormat = DecimalFormat("0.00")
    val celsiusWithFormat = decimalFormat.format(kelvins - KELVINS_TO_CELSIUS_PATTERN).replace(",", ".")
    return celsiusWithFormat.toDouble()
}