package com.arcsoft.arcfacedemo.thisapp.teacher.publishcheckeverycurriculum

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.arcsoft.arcfacedemo.databinding.ActivityPublishChechCurriculumBinding
import com.arcsoft.arcfacedemo.thisapp.base.activity.BaseTitleActivity
import com.arcsoft.arcfacedemo.thisapp.bean.Curriculum

class PublishCheckCurriculumActivity : BaseTitleActivity<ActivityPublishChechCurriculumBinding>() {

    private lateinit var currentItem: Curriculum
    private lateinit var viewModel: PublishCheckCurriculumViewModel

    override fun initDatum() {
        super.initDatum()

        currentItem = intent.getParcelableExtra<Curriculum>("currentItem")!!

        val viewModelFactory = PublishCheckCurriculumViewModelFactory(currentItem)

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(PublishCheckCurriculumViewModel::class.java)
        initViewModel(viewModel)
        Log.d(TAG, "initDatum: ${currentItem.toString()}")

    }


    override fun initListeners() {
        super.initListeners()
        binding.btnCheck.setOnClickListener {
            showProgressBar()
            viewModel.check()
        }
    }

    companion object{
        const val TAG = "PublishCheckCurriculumActivity"
    }

}
























