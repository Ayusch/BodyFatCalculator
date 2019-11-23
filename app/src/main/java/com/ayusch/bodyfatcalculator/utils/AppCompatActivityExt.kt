package com.ayusch.bodyfatcalculator.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


/**
 * Created by Ayus'c'h Jain
 * on 2019-09-29
 *
 */

fun <T : ViewModel> AppCompatActivity.obtainViewModel(
    classLoader: Class<T>,
    factory: ViewModelProvider.Factory? = null
) = ViewModelProviders.of(this, factory).get(classLoader)


fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
    }
}

fun FragmentManager.transact(block: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        block()
    }.commit()
}
