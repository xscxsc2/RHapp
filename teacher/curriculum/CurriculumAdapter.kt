package com.arcsoft.arcfacedemo.thisapp.teacher.curriculum

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arcsoft.app.staticdata.Constant
import com.arcsoft.arcfacedemo.R
import com.arcsoft.arcfacedemo.databinding.ItemAddressBinding
import com.arcsoft.arcfacedemo.thisapp.bean.Curriculum
import com.arcsoft.arcfacedemo.thisapp.teacher.publishcheckeverycurriculum.PublishCheckCurriculumActivity
import com.chad.library.adapter.base.BaseQuickAdapter

class CurriculumAdapter(context: Context, val viewModel: CurriculumViewModel) :
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

        init {
            binding.root.setOnClickListener{
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val currentItem = getItem(position)
                    Log.d(TAG, ": ${currentItem.toString()}" )
                    // 在这里处理点击事件，例如触发 ViewModel 中的方法处理点击事件
                    val intent = Intent(context, PublishCheckCurriculumActivity::class.java)
                    intent.putExtra(Constant.currentItem, currentItem)
                    context.startActivity(intent)
                }
            }
        }

        fun bindData(data: Curriculum){
            Log.d(TAG, "bindData: ${data.curriculumName}")
            binding.curriculumName.text = data.curriculumName
            binding.curriculumCode.text = data.code
            binding.curriculumTeacherName.text = data.ownerName + "老师"
        }
    }



    companion object{
        const val TAG = "CurriculumAdapter"
    }

}

















