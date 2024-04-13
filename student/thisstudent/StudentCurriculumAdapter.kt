package com.arcsoft.arcfacedemo.thisapp.student.thisstudent

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arcsoft.app.base.activity.BaseLogicActivity
import com.arcsoft.app.staticdata.Constant
import com.arcsoft.arcfacedemo.databinding.ItemAddressBinding
import com.arcsoft.arcfacedemo.thisapp.bean.Curriculum
import com.arcsoft.arcfacedemo.thisapp.teacher.curriculum.CurriculumAdapter
import com.arcsoft.arcfacedemo.thisapp.teacher.curriculum.CurriculumZ
import com.arcsoft.arcfacedemo.thisapp.teacher.publishcheckeverycurriculum.PublishCheckCurriculumActivity
import com.chad.library.adapter.base.BaseQuickAdapter

class StudentCurriculumAdapter(
    context: Context,
    viewModel: ThisCurriculumViewModel
) : BaseQuickAdapter<CurriculumZ, StudentCurriculumAdapter.ViewHolder>() {
    override fun onBindViewHolder(
        holder: StudentCurriculumAdapter.ViewHolder,
        position: Int,
        data: CurriculumZ?
    ) {
        holder.bindData(data!!)
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): StudentCurriculumAdapter.ViewHolder {
        return ViewHolder(ItemAddressBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    inner class ViewHolder(val binding: ItemAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: CurriculumZ){
            Log.d(CurriculumAdapter.TAG, "bindData: ${data.curriculumName}")
            binding.curriculumName.text = data.curriculumName
            binding.curriculumCode.text = data.code
            binding.curriculumTeacherName.text = data.ownerName + "老师"

        }
    }


}