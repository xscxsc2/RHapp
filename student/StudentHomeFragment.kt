package com.arcsoft.arcfacedemo.thisapp.student

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.arcsoft.app.util.PreferenceUtil
import com.arcsoft.arcfacedemo.databinding.FragmentStudentHomeBinding
import com.arcsoft.arcfacedemo.thisapp.base.fragment.BaseViewModelFragment
import com.arcsoft.arcfacedemo.thisapp.teacher.TabLayoutViewPager2Mediator
import com.arcsoft.arcfacedemo.thisapp.teacher.TeacherHomeAdapter
import com.arcsoft.arcfacedemo.thisapp.teacher.TeacherHomeFragment
import com.arcsoft.arcfacedemo.thisapp.util.DataUtil

class StudentHomeFragment: BaseViewModelFragment<FragmentStudentHomeBinding>() {

    private lateinit var viewModel: StudentHomeViewModel

    override fun initDatum() {
        super.initDatum()
        val viewModelFactory = StudentHomeViewModelFactory(PreferenceUtil.getUserName())
        Log.d( TAG, "当前名字: ${PreferenceUtil.getUserName()}")
        viewModel = ViewModelProvider(this, viewModelFactory).get(StudentHomeViewModel::class.java)

        initViewModel(viewModel)

        binding.apply {//中间界面适配器
            pager.adapter = StudentHomeAdapter(requireActivity(), DataUtil.categoriesStudent)

            TabLayoutViewPager2Mediator(indicator, pager) { indicator, pager ->
            }.attach()  //联动
        }
    }


    companion object{
        const val TAG = "StudentHomeFragment"
        fun newInstance(): StudentHomeFragment{
            val args = Bundle()

            val fragment = StudentHomeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}