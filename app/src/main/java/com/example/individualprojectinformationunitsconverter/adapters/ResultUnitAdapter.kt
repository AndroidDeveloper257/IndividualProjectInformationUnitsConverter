package com.example.individualprojectinformationunitsconverter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.individualprojectinformationunitsconverter.databinding.UnitsChoiceItemBinding
import com.example.individualprojectinformationunitsconverter.models.MyUnit

class ResultUnitAdapter(
    private val unitList: ArrayList<MyUnit>,
    val onItemClick: (MyUnit) -> Unit
) : RecyclerView.Adapter<ResultUnitAdapter.ResultUnitViewHolder>() {

    inner class ResultUnitViewHolder(
        private val itemBinding: UnitsChoiceItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(myUnit: MyUnit) {
            itemBinding.apply {
                unitNameTv.text = myUnit.unitName
                unitAcronymTv.text = myUnit.unitAcronym
                root.setOnClickListener {
                    onItemClick.invoke(myUnit)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultUnitViewHolder {
        return ResultUnitViewHolder(
            UnitsChoiceItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = unitList.size

    override fun onBindViewHolder(holder: ResultUnitViewHolder, position: Int) {
        holder.onBind(unitList[position])
    }
}