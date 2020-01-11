package com.ayusch.bodyfatcalculator

import com.ayusch.bodyfatcalculator.calculations.CalculationViewModel
import com.ayusch.bodyfatcalculator.utils.Calculator
import org.junit.Assert
import org.junit.Test


/**
 * Created by Ayus'c'h Jain
 * on 2020-01-11
 *
 */

class CalculatorUnitTest {

    @Test
    fun check3Pinch_isCorrect() {
        val bodyFat3Pinch = Calculator.bodyFat3Pinch(
            0.toDouble(),
            0.toDouble(),
            0.toDouble(),
            0.toDouble(),
            CalculationViewModel.Gender.MALE
        )
        Assert.assertEquals(bodyFat3Pinch?.toDouble(), -3.8048279219022874)
    }
}
