package com.arcsoft.arcfacedemo.thisapp.teacher.curriculum

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arcsoft.arcfacedemo.databinding.ItemAddressBinding
import com.arcsoft.arcfacedemo.thisapp.bean.Curriculum
import com.chad.library.adapter.base.BaseQuickAdapter

class CurriculumAdapter(val viewModel: CurriculumViewModel) :
    BaseQuickAdapter<Curriculum, CurriculumAdapter.ViewHolder>() {
    override fun onBindViewHolder(
        holder: CurriculumAdapter.ViewHolder,
        position: Int,
        data: Curriculum?
    ) {
        holder.bindData(data!!)
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): CurriculumAdapter.ViewHolder {
        return ViewHolder(ItemAddressBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    inner class ViewHolder(val binding: ItemAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Curriculum){
            Log.d(TAG, "bindData: ${data.curriculumName}")
            binding.curriculumName.text = data.curriculumName
        }
    }
    companion object{
        const val TAG = "CurriculumAdapter"
    }

}

















