package com.arcsoft.arcfacedemo.thisapp.teacher.curriculum

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arcsoft.app.staticdata.Constant
import com.arcsoft.arcfacedemo.databinding.FragmentCurriculumBinding
import com.arcsoft.arcfacedemo.thisapp.base.fragment.BaseViewModelFragment
import com.arcsoft.arcfacedemo.thisapp.util.CodeUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CurriculumFragment:BaseViewModelFragment<FragmentCurriculumBinding>() {

    private lateinit var viewModel: CurriculumViewModel
    private lateinit var adapter: CurriculumAdapter
    private var isFirstShow: Boolean = true

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
            CurriculumViewModelFactory(
                requireArguments().getString(Constant.ID),
                extraInt(Constant.STYLE)
            )
        viewModel = ViewModelProvider(this, viewModelFactory).get(CurriculumViewModel::class.java)
        initViewModel(viewModel)

        Log.d(TAG, "验证码"+"${CodeUtil.getCurriculumCode()}")

        //适配器
        adapter = CurriculumAdapter(viewModel)
        binding.list.adapter = adapter

        lifecycleScope.launch {
            viewModel.data.collect(){
                processRefreshAndLoadMoreStatus(true, it.isEmpty() ?: true)
                delay(1000)
                adapter.addAll(it)
            }
        }

    }


    private fun processRefreshAndLoadMoreStatus(success: Boolean, noMore: Boolean = false) {
        //传入false表示刷新失败
        binding.refresh.finishRefresh(1000, success, false)

        //next=null，表示没有更多数据了
        binding.refresh.finishLoadMore(1000, success, noMore)
    }

    override fun initListeners() {
        super.initListeners()

    }

    override fun onResume() {
        super.onResume()
        if (isFirstShow){
            isFirstShow = false
            //第一次进入，直接展示，不然会数据直接出来
            binding.refresh.autoRefresh()
            viewModel.loadData()
        }

    }

    companion object {
        const val TAG = "ContentFragment"

        fun newInstance(
            categoryId: String? = null,
            index: Int = Constant.VALUE_NO
        ): CurriculumFragment {
            val args = Bundle()
            args.putInt(Constant.STYLE, index)
            categoryId?.let {
                args.putString(Constant.ID, it) //把categoryId传进去，上面initdatum可以获得
            }

            val fragment = CurriculumFragment()
            fragment.arguments = args
            return fragment
        }
    }
}