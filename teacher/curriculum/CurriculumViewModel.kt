package com.arcsoft.arcfacedemo.thisapp.teacher.curriculum

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.arcsoft.app.base.viewmodel.BaseViewModel
import com.arcsoft.arcfacedemo.thisapp.bean.Curriculum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

/**
 * 内容界面ViewModel
 */
class CurriculumViewModel(private val categoryId: String?, val index: Int) : BaseViewModel() {
    var lastId: String? = null
    var query: String? = null

    private val _data = MutableSharedFlow<List<Curriculum>>()
    val data: Flow<List<Curriculum>> = _data

    fun loadData(){
        var datas: List<Curriculum> = listOf(
            Curriculum("高等数学"),
            Curriculum("程序设计求解"),
            Curriculum("面向对象编程"),
            Curriculum("计算机网络"),
        )
        viewModelScope.launch {
            Log.d("名称测试", "loadData: ${datas.get(2).curriculumName}")
            _data.emit(datas)
        }
    }

    companion object{

    }


}



















