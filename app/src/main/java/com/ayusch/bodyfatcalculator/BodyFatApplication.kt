package com.ayusch.bodyfatcalculator

import android.app.Application


/**
 * Created by Ayus'c'h Jain
 * on 2019-09-29
 *
 */

class BodyFatApplication : Application() {

    companion object {
        var mInstance: BodyFatApplication? = null
        fun getInstance(): BodyFatApplication? {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }


}
