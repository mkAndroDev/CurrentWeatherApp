package com.dreamit.currentweatherapp.utils

import kotlin.test.Test
import kotlin.test.assertTrue

class TemperatureUtilsTest {

    @Test
    fun kelvinsToCelsiusShouldBeCalculatedProperly() {
        /* Given */
        val kelvins1 = 0.0
        val kelvins2 = 56.8
        val kelvins3 = 299.9
        val kelvins4 = 256.5
        val kelvins5 = 273.15
        /* When */
        val celsius1 = getCelsiusFormKelvins(kelvins1)
        val celsius2 = getCelsiusFormKelvins(kelvins2)
        val celsius3 = getCelsiusFormKelvins(kelvins3)
        val celsius4 = getCelsiusFormKelvins(kelvins4)
        val celsius5 = getCelsiusFormKelvins(kelvins5)
        /* Then */
        assertTrue { celsius1 == -273.15 }
        assertTrue { celsius2 == -216.35 }
        assertTrue { celsius3 == 26.75 }
        assertTrue { celsius4 == -16.65 }
        assertTrue { celsius5 == 0.0 }
    }
}