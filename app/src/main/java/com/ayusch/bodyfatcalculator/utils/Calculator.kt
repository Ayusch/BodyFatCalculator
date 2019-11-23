package com.ayusch.bodyfatcalculator.utils

import com.ayusch.bodyfatcalculator.calculations.CalculationViewModel


/**
 * Created by Ayus'c'h Jain
 * on 2019-09-29
 *
 */

object Calculator {
    /**
     *
     *
     *   The formula for men is: 1.10938 - (0.0008267 x the sum of the chest, abdomen and thigh measurements
     *   in millimeters) + (0.0000016 x the square of the sum of the chest,
     *   abdomen and thigh measurements in millimeters) - (0.0002574 x age) = body density
     *
     *   The formula for women, also known as the Jackson-Pollock-Ward formula, is:
     *   1.0994921 - (0.0009929 x the sum of the triceps, thigh and suprailiac measurements) +
     *   (0.0000023 x the square of the sum of the triceps, thigh and suprailiac measurements) - (0.0001392 x age) = body density
     *
     *   [(4.95/body density) - 4.5] x 100 = percent body fat
     *
     *
     *
     */
    fun bodyFat3Pinch(chest: Double?, abdomen: Double?, thigh: Double?, age: Double?, gender: CalculationViewModel.Gender?): String? {
        if (chest == null || abdomen == null || thigh == null || age == null) return null

        val sumOfSkinFolds = chest + abdomen + thigh

        val bodyDensity = if (gender == CalculationViewModel.Gender.MALE)
            calculateBodyDensityForMen(sumOfSkinFolds, age)
        else
            calculateBodyDensityForWomen(sumOfSkinFolds, age)

        if (bodyDensity == 0.0) {
            return null
        }

        return (((4.95 / bodyDensity) - 4.5) * 100).toString()
    }

    private fun calculateBodyDensityForMen(sumOfSkinFolds: Double, age: Double) =
            1.10938 - (0.0008267 * sumOfSkinFolds) + (0.0000016 * sumOfSkinFolds * sumOfSkinFolds) - 0.0002574 * age

    private fun calculateBodyDensityForWomen(sumOfSkinFolds: Double, age: Double) =
            1.0994921 - (0.0009929 * sumOfSkinFolds) + (0.0000023 * sumOfSkinFolds * sumOfSkinFolds) - (0.0001392 * age)
}
