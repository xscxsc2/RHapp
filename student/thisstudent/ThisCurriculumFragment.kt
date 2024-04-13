package com.arcsoft.arcfacedemo.thisapp.student.thisstudent

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arcsoft.app.staticdata.Constant
import com.arcsoft.app.util.PreferenceUtil
import com.arcsoft.arcfacedemo.R
import com.arcsoft.arcfacedemo.databinding.FragmentStudentThisCurriculumBinding
import com.arcsoft.arcfacedemo.thisapp.base.fragment.BaseViewModelFragment

class ThisCurriculumFragment: BaseViewModelFragment<FragmentStudentThisCurriculumBinding>() {

    private val client: String = PreferenceUtil.getClient()
    private val studentName = PreferenceUtil.getUserName()
    private lateinit var viewModel: ThisCurriculumViewModel
    private var isFirstShow: Boolean = true
    private lateinit var adapter: StudentCurriculumAdapter


    override fun initViews() {
        super.initViews()
        binding.list.apply {
            layoutManager = LinearLayoutManager(hostActivity)
            //分割线
            val decoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
            addItemDecoration(decoration)
        }
    }

    override fun initDatum() {
        super.initDatum()
        val viewModelFactory =
            ThisCurriculumViewModelFactory(
                PreferenceUtil.getUserName()
            )
        viewModel = ViewModelProvider(this, viewModelFactory).get(ThisCurriculumViewModel::class.java)
        initViewModel(viewModel)

        binding.emptyData.visibility = View.GONE
        binding.list.visibility = View.VISIBLE

        //适配器
        adapter = StudentCurriculumAdapter(hostActivity, viewModel)
        binding.list.adapter = adapter

        viewModel.ds.observe(this, Observer {
            processRefreshAndLoadMoreStatus(true)
            adapter.submitList(it)
        })



    }

    private fun processRefreshAndLoadMoreStatus(success: Boolean, noMore: Boolean = false) {
        //传入false表示刷新失败
        binding.refresh.finishRefresh(0, success, false)

    }

    override fun initListeners() {
        super.initListeners()
        // 下拉加载刷新
        binding.refresh.setOnRefreshListener {
            viewModel.loadData()
            // 停止刷新动画
//            binding.refresh.finishRefresh()
        }

        //加入课堂
        binding.btnJoinCurriculum.setOnClickListener {
            val dialog = Dialog(hostActivity)
            dialog.setContentView(R.layout.dialog_publish)
            dialog.show()
            val publish = dialog.findViewById<Button>(R.id.publish_btn)
            publish.text = "加入"
            val ed_input = dialog.findViewById<EditText>(R.id.publish_curriculum_name)
            Log.d("inputCode", "initListeners: ${ed_input}")
            dialog.findViewById<TextView>(R.id.tv_curriculumCode).text = "课号"
            Log.d("123", "initListeners: " + ed_input.text.toString())
            publish.setOnClickListener {
                viewModel.joinCurriculum(ed_input.text.toString(), studentName)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (isFirstShow) {
            isFirstShow = false
            //第一次进入，直接展示，不然会数据直接出来
            binding.refresh.autoRefresh()
//            viewModel.loadData()
        }
    }

    companion object{

        fun newInstance(
            categoryId: String? = null,
            index: Int = Constant.VALUE_NO
        ): ThisCurriculumFragment{
            val args = Bundle()

            val fragment = ThisCurriculumFragment()
            fragment.arguments = args
            return fragment
        }
    }
}