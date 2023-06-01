package com.example.individualprojectinformationunitsconverter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.individualprojectinformationunitsconverter.adapters.ResultUnitAdapter
import com.example.individualprojectinformationunitsconverter.databinding.ActivityMainBinding
import com.example.individualprojectinformationunitsconverter.databinding.InfoDialogItemBinding
import com.example.individualprojectinformationunitsconverter.databinding.ResultUnitChoiceDialogItemBinding
import com.example.individualprojectinformationunitsconverter.utils.BIT_ACRONYM
import com.example.individualprojectinformationunitsconverter.utils.calculate
import com.example.individualprojectinformationunitsconverter.utils.getUnitList

class MainActivity : AppCompatActivity() {

    companion object {
        private lateinit var resultUnit: String
    }

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var infoAlertDialog: AlertDialog
    private lateinit var resultUnitChoiceAlertDialog: AlertDialog
    private lateinit var infoDialogItemBinding: InfoDialogItemBinding
    private lateinit var resultUnitChoiceDialogItemBinding: ResultUnitChoiceDialogItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            supportActionBar?.hide()
            setContentView(root)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = getColor(R.color.blue)
            resultUnit = BIT_ACRONYM
            toolbar.setOnMenuItemClickListener { menuItem ->
                if (menuItem.itemId == R.id.info) {
                    showInfoDialog()
                }
                false
            }
            resultUnitLayout.setOnClickListener {
                chooseResultUnit()
            }
            convertIv.setOnClickListener {
                val result = calculate(
                    query = queryEt.text.toString(),
                    resultUnit = resultUnit
                )
                if (result == null) {
                    queryEt.setBackgroundResource(R.drawable.error_edit_text_background)
                    showInfoDialog()
                } else {
                    resultTv.text = result.toString()
                }
            }
            queryEt.addTextChangedListener {
                queryEt.setBackgroundResource(R.drawable.custom_background)
            }
        }
    }

    private fun chooseResultUnit() {
        binding.apply {
            resultUnitChoiceAlertDialog = AlertDialog.Builder(this@MainActivity).create()
            resultUnitChoiceDialogItemBinding =
                ResultUnitChoiceDialogItemBinding.inflate(layoutInflater)
            resultUnitChoiceDialogItemBinding.apply {
                resultUnitChoiceAlertDialog.setView(root)
                resultUnitChoiceAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                val resultUnitAdapter = ResultUnitAdapter(
                    getUnitList()
                ) { myUnit ->
                    resultUnitTv.text = myUnit.unitName
                    resultUnit = myUnit.unitAcronym
                    resultUnitChoiceAlertDialog.dismiss()
                }
                rv.adapter = resultUnitAdapter
                resultUnitChoiceAlertDialog.show()
            }
        }
    }

    private fun showInfoDialog() {
        binding.apply {
            infoAlertDialog = AlertDialog.Builder(this@MainActivity).create()
            infoDialogItemBinding = InfoDialogItemBinding.inflate(layoutInflater)
            infoDialogItemBinding.apply {
                infoAlertDialog.setView(root)
                infoAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                okBtn.setOnClickListener {
                    infoAlertDialog.dismiss()
                }
                infoAlertDialog.show()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}