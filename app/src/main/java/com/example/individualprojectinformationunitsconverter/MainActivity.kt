package com.example.individualprojectinformationunitsconverter

import android.content.ClipData
import android.content.ClipboardManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.individualprojectinformationunitsconverter.adapters.ResultUnitAdapter
import com.example.individualprojectinformationunitsconverter.databinding.ActivityMainBinding
import com.example.individualprojectinformationunitsconverter.databinding.InfoDialogItemBinding
import com.example.individualprojectinformationunitsconverter.databinding.UnitsChoiceDialogItemBinding
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
    private lateinit var resultUnitChoiceDialogItemBinding: UnitsChoiceDialogItemBinding

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
                convert(queryEt.text.toString())
            }
            queryEt.addTextChangedListener {
                queryEt.setBackgroundResource(R.drawable.custom_background)
            }
            copyQuery.setOnClickListener {
                copyToClipboard(queryEt.text.toString())
            }
            copyResult.setOnClickListener {
                copyToClipboard(resultTv.text.toString())
            }
        }
    }

    private fun convert(query: String) {
        binding.apply {
            val result = calculate(
                query = query,
                resultUnit = resultUnit
            )
            if (result == null) {
                queryEt.setBackgroundResource(R.drawable.error_edit_text_background)
                showInfoDialog()
            } else {
                resultTv.text = result.toString()
            }
        }
    }

    private fun chooseResultUnit() {
        binding.apply {
            resultUnitChoiceAlertDialog = AlertDialog.Builder(this@MainActivity).create()
            resultUnitChoiceDialogItemBinding =
                UnitsChoiceDialogItemBinding.inflate(layoutInflater)
            resultUnitChoiceDialogItemBinding.apply {
                resultUnitChoiceAlertDialog.setView(root)
                resultUnitChoiceAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                val resultUnitAdapter = ResultUnitAdapter(
                    getUnitList()
                ) { myUnit ->
                    resultUnitTv.text = myUnit.unitName
                    resultUnit = myUnit.unitAcronym
                    resultUnitChoiceAlertDialog.dismiss()
                    convert(queryEt.text.toString())
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
                val adapter = ResultUnitAdapter(
                    getUnitList()
                ) { unit ->
                    copyToClipboard(unit.unitAcronym)
                }
                unitsRv.adapter = adapter
                infoAlertDialog.show()
            }

        }
    }

    private fun copyToClipboard(text: String) {
        if (text.isNotEmpty()) {
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "$text copied to clipboard", Toast.LENGTH_SHORT).show()
            infoAlertDialog.dismiss()
        } else {
            Toast.makeText(this, "Empty text is not copied to clipboard", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}