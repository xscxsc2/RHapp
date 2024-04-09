package com.arcsoft.arcfacedemo.thisapp.teacher

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.arcsoft.app.extension.shortToast
import com.arcsoft.app.util.PreferenceUtil
import com.arcsoft.arcfacedemo.R

import com.arcsoft.arcfacedemo.databinding.FragmentTeacherHomeBinding
import com.arcsoft.arcfacedemo.thisapp.base.fragment.BaseViewModelFragment
import com.arcsoft.arcfacedemo.thisapp.util.DataUtil
import com.arcsoft.arcfacedemo.thisapp.util.DateUtil
import kotlin.math.log

class TeacherHomeFragment : BaseViewModelFragment<FragmentTeacherHomeBinding>() {

    private lateinit var localTeacherSno: String
    private lateinit var viewModel: TeacherHomeViewModel

    override fun initDatum() {
        super.initDatum()


        val viewModelFactory = TeacherHomeViewModelFactory(PreferenceUtil.getUserName())
        Log.d(TAG, "当前教师: ${PreferenceUtil.getUserName()}")
        viewModel = ViewModelProvider(this,viewModelFactory).get(TeacherHomeViewModel::class.java)

        initViewModel(viewModel)

        localTeacherSno = PreferenceUtil.getUserSno()

        binding.apply {//中间界面适配器
            pager.adapter = TeacherHomeAdapter(requireActivity(), DataUtil.categories)

            TabLayoutViewPager2Mediator(indicator, pager) { indicator, pager ->
            }.attach()  //联动
        }

        //观察发布课号
        viewModel.publish.observe(this){
            Log.d(TAG, "initDatum: ${getString(it)}")
            getString(it).shortToast()
        }

    }


    override fun initListeners() {
        super.initListeners()
        binding.floatBtn.setOnClickListener {
            val dialog = Dialog(hostActivity)
            dialog.setContentView(R.layout.dialog_publish)
            dialog.show()
            val publish = dialog.findViewById<Button>(R.id.publish_btn)
            val curriculumName = dialog.findViewById<EditText>(R.id.publish_curriculum_name)
            publish.setOnClickListener {
                viewModel.publish(curriculumName.text.toString())
            }


            val NowTime = DateUtil.getCurrentDate(DateUtil.All)
            Log.d(TAG, "initListeners: 当前时间:${NowTime}")

        }


    }

    companion object {
        const val TAG = "TeacherHomeFragment"
        fun newInstance(): TeacherHomeFragment {
            val args = Bundle()

            val fragment = TeacherHomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

}















