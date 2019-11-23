package com.ayusch.bodyfatcalculator.utils


/**
 * Created by Ayus'c'h Jain
 * on 2019-11-09
 *
 */

fun Float.uptoDecimalPlace(precision: Int): Float {
    return String.format("%.$precision" + "f", this).toFloat()
}