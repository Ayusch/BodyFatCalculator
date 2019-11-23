package com.ayusch.bodyfatcalculator.fargment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.ayusch.bodyfatcalculator.CalculationActivity
import com.ayusch.bodyfatcalculator.R
import com.ayusch.bodyfatcalculator.databinding.FragmentCalculationBinding

/**
 * A simple [Fragment] subclass.
 */
class CalculationFragment : Fragment() {

    private lateinit var binding: FragmentCalculationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCalculationBinding.inflate(inflater, container, false).apply {
            viewmodel = (activity as CalculationActivity).obtainViewModel()
        }
        return binding.root
    }

    companion object {
        fun newInstance() = CalculationFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.findViewById<Button>(R.id.btn_calculate)?.let {
            it.setOnClickListener {
                binding.viewmodel?.validateAndCalculate()
            }
        }
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewmodel?.init()
    }


}
