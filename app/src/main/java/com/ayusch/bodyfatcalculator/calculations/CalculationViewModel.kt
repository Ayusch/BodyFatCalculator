package com.ayusch.bodyfatcalculator.calculations

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ayusch.bodyfatcalculator.R
import com.ayusch.bodyfatcalculator.repository.BfcRepository
import com.ayusch.bodyfatcalculator.utils.Calculator
import com.ayusch.bodyfatcalculator.utils.Event
import com.ayusch.bodyfatcalculator.utils.uptoDecimalPlace
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup


/**
 * Created by Ayus'c'h Jain
 * on 2019-09-29
 *
 */
class CalculationViewModel(val context: Context) : ViewModel() {

    val chestMeasure = MutableLiveData<String?>()
    val abdomenMeasure = MutableLiveData<String?>()
    val thighMeasure = MutableLiveData<String?>()
    val age = MutableLiveData<String?>()
    val weight = MutableLiveData<String?>()

    private val _fatPerc = MutableLiveData<String>()
    val fatPerc: LiveData<String>
        get() = _fatPerc

    private val _resultAvailable = MutableLiveData<Boolean>(true)
    val resultAvailable: LiveData<Boolean>
        get() = _resultAvailable

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarMessage: LiveData<Event<String>>
        get() = _snackbarText

    enum class Gender {
        MALE, FEMALE
    }

    var gender: Gender = Gender.MALE

    val onGenderChanged: (singleSelectToggleGroup: SingleSelectToggleGroup, id: Int) -> Unit =
        { group, id ->
            gender = if (id == R.id.choice_female)
                Gender.FEMALE
            else
                Gender.MALE
        }


    fun validateAndCalculate() {
        if (chestMeasure.value.isNullOrEmpty() || abdomenMeasure.value.isNullOrEmpty() || thighMeasure.value.isNullOrEmpty()) {
            _snackbarText.value = Event("Please enter all the values")
            return
        }

        if (age.value.isNullOrEmpty()) {
            _snackbarText.value = Event("Please enter your age")
            return
        }

        if (weight.value.isNullOrEmpty()) {
            _snackbarText.value = Event("Please enter your correct weight")
            return
        }

        BfcRepository.getInstance(context)
            .saveChestMeasure(chestMeasure.value?.toFloat() ?: 0.0f)

        BfcRepository.getInstance(context)
            .saveThighMeasure(thighMeasure.value?.toFloat() ?: 0.0f)

        BfcRepository.getInstance(context)
            .saveAbdomenMeasure(abdomenMeasure.value?.toFloat() ?: 0.0f)

        BfcRepository.getInstance(context)
            .saveAgeMeasure(age.value?.toFloat() ?: 0.0f)

        BfcRepository.getInstance(context)
            .saveWeightMeasure(weight.value?.toFloat() ?: 0.0f)

        val fat = Calculator.bodyFat3Pinch(
            chestMeasure.value?.toDouble(),
            abdomenMeasure.value?.toDouble(),
            thighMeasure.value?.toDouble(),
            age.value?.toDouble(),
            gender
        )

        fat?.let {
            BfcRepository.getInstance(context)
                .saveBodyFatPerc(it.toFloat())
            setResult(it)
        } ?: setResult("")

    }

    private fun setResult(fat: String) {
        _snackbarText.value = Event(getFloatUptoTwoDecimals(fat))
        _fatPerc.value = getFloatUptoTwoDecimals(fat)
        _resultAvailable.value = true
        chestMeasure.value = BfcRepository.getInstance(context).getChestMeasure()
        abdomenMeasure.value = BfcRepository.getInstance(context).getAbdomenMeasure()
        thighMeasure.value = BfcRepository.getInstance(context).getThighMeasure()
        age.value = BfcRepository.getInstance(context).getAgeMeasure()
        weight.value = BfcRepository.getInstance(context).getWeightMeasure()
    }

    private fun getFloatUptoTwoDecimals(fat: String) =
        if (fat.isEmpty()) "" else fat.toFloat().uptoDecimalPlace(2).toString() + "%"


    fun init() {
        setResult(
            BfcRepository.getInstance(context)
                .getBodyFatPerc()
        )
    }

    class ClaculationViewModelFactory(val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CalculationViewModel(context) as T
        }

    }

}