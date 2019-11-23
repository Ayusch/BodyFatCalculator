package com.ayusch.bodyfatcalculator.utils

import androidx.databinding.BindingAdapter
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup


/**
 * Created by Ayus'c'h Jain
 * on 2019-09-29
 *
 */
class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("app:bindToggleListener")
        fun bindToggleListener(
            singleSelectToggleGroup: SingleSelectToggleGroup,
            listener: (singleSelectToggleGroup: SingleSelectToggleGroup, id: Int) -> Unit
        ) {
            singleSelectToggleGroup.setOnCheckedChangeListener(listener)
        }
    }
}