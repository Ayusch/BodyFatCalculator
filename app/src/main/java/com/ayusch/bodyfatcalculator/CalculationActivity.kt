package com.ayusch.bodyfatcalculator

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ayusch.bodyfatcalculator.calculations.CalculationViewModel
import com.ayusch.bodyfatcalculator.fargment.CalculationFragment
import com.ayusch.bodyfatcalculator.utils.Event
import com.ayusch.bodyfatcalculator.utils.Utils
import com.ayusch.bodyfatcalculator.utils.obtainViewModel
import com.ayusch.bodyfatcalculator.utils.replaceFragmentInActivity

class CalculationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewFragment()
        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        obtainViewModel().apply {
            snackbarMessage.observe(this@CalculationActivity, Observer<Event<String>> { event ->
                event.getContentIfNotHandled()?.let {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                    Utils.hideKeyboard(this@CalculationActivity)
                }

            })
        }

    }

    private fun setupViewFragment() {
        supportFragmentManager.findFragmentById(R.id.contentFrame) ?: replaceFragmentInActivity(
            CalculationFragment.newInstance(),
            R.id.contentFrame
        )
    }

    fun obtainViewModel() = obtainViewModel(
        CalculationViewModel::class.java,
        CalculationViewModel.ClaculationViewModelFactory(this)
    )


}
