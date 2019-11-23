package com.ayusch.bodyfatcalculator.repository

import android.content.Context
import com.ayusch.bodyfatcalculator.SingletonHolder
import com.ayusch.bodyfatcalculator.utils.SharedPreferenceManager


/**
 * Created by Ayus'c'h Jain
 * on 2019-10-13
 *
 */

class BfcRepository(val context: Context) {


    companion object : SingletonHolder<BfcRepository, Context>(::BfcRepository)

    private val FAT_PERCENTAGE: String = "fat_percentage"
    private val CHEST_MEASURE: String = "chest_measure"
    private val THIGH_MEASURE: String = "thigh_measure"
    private val ABDOMEN_MEASURE: String = "abdomen_measure"
    private val WEIGHT_MEASURE: String = "weight_measure"
    private val AGE_MEASURE: String = "age_measure"

    fun saveBodyFatPerc(perc: Float) {

        SharedPreferenceManager
                .setFloatValueForKey(FAT_PERCENTAGE, perc)
    }

    fun getBodyFatPerc() =
            SharedPreferenceManager.getFloatValueForKey(FAT_PERCENTAGE, 0.0f).toString()

    fun saveChestMeasure(chest: Float) {
        SharedPreferenceManager
                .setFloatValueForKey(CHEST_MEASURE, chest)
    }

    fun getChestMeasure() = SharedPreferenceManager.getFloatValueForKey(CHEST_MEASURE, 0.0f).toString()

    fun saveWeightMeasure(chest: Float) {
        SharedPreferenceManager
                .setFloatValueForKey(WEIGHT_MEASURE, chest)
    }

    fun getWeightMeasure() = SharedPreferenceManager.getFloatValueForKey(WEIGHT_MEASURE, 0.0f).toString()


    fun saveAgeMeasure(age: Float) {
        SharedPreferenceManager
            .setFloatValueForKey(AGE_MEASURE, age)
    }

    fun getAgeMeasure() = SharedPreferenceManager.getFloatValueForKey(AGE_MEASURE, 0.0f).toString()


    fun saveThighMeasure(chest: Float) {
        SharedPreferenceManager
                .setFloatValueForKey(THIGH_MEASURE, chest)
    }

    fun getThighMeasure() = SharedPreferenceManager.getFloatValueForKey(THIGH_MEASURE, 0.0f).toString()

    fun saveAbdomenMeasure(chest: Float) {
        SharedPreferenceManager
                .setFloatValueForKey(ABDOMEN_MEASURE, chest)
    }

    fun getAbdomenMeasure() = SharedPreferenceManager.getFloatValueForKey(ABDOMEN_MEASURE, 0.0f).toString()


}