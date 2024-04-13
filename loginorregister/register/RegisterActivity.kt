package com.arcsoft.arcfacedemo.thisapp.loginorregister.register

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.arcsoft.app.extension.shortToast
import com.arcsoft.app.staticdata.Constant
import com.arcsoft.arcfacedemo.R
import com.arcsoft.arcfacedemo.databinding.ActivityRegisterBinding
import com.arcsoft.arcfacedemo.facedb.entity.FaceEntity
import com.arcsoft.arcfacedemo.thisapp.base.activity.BaseTitleActivity
import com.arcsoft.arcfacedemo.thisapp.opengauss.OpenGaussHelper
import com.arcsoft.arcfacedemo.thisapp.util.AlertDialogHelper
import com.arcsoft.arcfacedemo.ui.activity.HomeActivity
import com.arcsoft.arcfacedemo.ui.activity.RegisterAndRecognizeActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class RegisterActivity : BaseTitleActivity<ActivityRegisterBinding>() {

    private var viewModel: RegisterViewModel = RegisterViewModel()
    private var isCollectionFace = false
    private var CheckStudentOrTeacher = Constant.isCheckTeacher //0是选中学生，1是老师

    private var faceEntity: FaceEntity? = null

    override fun initDatum() {
        // 注册事件
        EventBus.getDefault().register(this);



        super.initDatum()
        viewModel =
            ViewModelProvider(this).get(RegisterViewModel::class.java)
        initViewModel(viewModel)

        viewModel.data.observe(this){
            if (it == 1){
                AlertDialogHelper.showAlertDialogSingle(this,"注册成功","确认",object: AlertDialogHelper.OnSingleClickListener{
                    override fun Click() {

                    }
                })
            }
        }

    }

    //处理黏性事件
    @Subscribe(threadMode = ThreadMode.ASYNC, sticky = true)
    fun StickyEventBus(faceEntity: FaceEntity) {
        this.faceEntity = faceEntity
        isCollectionFace = true
    }


    override fun initViews() {
        super.initViews()

        getWindow().apply {
//            statusBarColor = ContextCompat.getColor(applicationContext, R.color.)
            // 如果您使用的是 CoordinatorLayout，可能还需要添加以下代码来使 Toolbar 正确对齐：
            // window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }

        binding.radioTeacher.isChecked;

    }

    override fun initListeners() {
        super.initListeners()
        //复选点击
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioTeacher -> {
                    CheckStudentOrTeacher = Constant.isCheckTeacher
                    binding.studentNo.setText("请输入工号")
//                    AlertDialogHelper.showAlertDialogSingle(this, "这是一个弹窗消息","确定")
                    binding.faceCollection.visibility = View.GONE
                }

                R.id.radioStudent -> {
                    CheckStudentOrTeacher = Constant.isCheckStudent
                    binding.studentNo.setText("请输入学号")
                    binding.faceCollection.visibility = View.VISIBLE
                    AlertDialogHelper.showAlertDialogDouble(
                        this,
                        "学生注册需要人脸！！",
                        "确定",
                        "取消",
                        listener = object :
                            AlertDialogHelper.OnButtonClickListener {
                            override fun onLeftButtonClicked() {
                                Log.d(TAG, "onLeftButtonClicked: 取消")
                            }

                            override fun onRightButtonClicked() {
                                Log.d(TAG, "onRightButtonClicked: 确定")
                            }
                        })
                }
            }
        }

        //注册
        binding.register.setOnClickListener {
            if (CheckStudentOrTeacher == Constant.isCheckStudent) {  //学生
                if (isCollectionFace) {
                    GlobalScope.launch {
                        OpenGaussHelper.registerUser(
                            faceEntity!!,
                            binding.nickname.text.toString(),
                            binding.password.text.toString(),
                            binding.studentNo.text.toString()
                        )
                    }
                } else {
                    AlertDialogHelper.showAlertDialogSingle(
                        this,
                        "请收集人脸！",
                        "确认",
                        object : AlertDialogHelper.OnSingleClickListener {
                            override fun Click() {

                            }
                        })
                }
            } else {    //老师
                viewModel.registerFromTeacher(binding.nickname,binding.password,binding.studentNo)
            }

        }

        //收集人脸
        binding.faceCollection.setOnClickListener {
            val intent =
                Intent(this, RegisterAndRecognizeActivity::class.java) // 创建Intent，指定目标Activity
            intent.putExtra(
                Constant.ToActivity,
                Constant.ToFaceCollectionFromRegister
            ) // 使用putExtra方法传递数据
            startActivity(intent)
        }

        //返回首页
        binding.home.setOnClickListener {
            startActivity(HomeActivity::class.java)
        }

        //比对
        binding.compare.setOnClickListener {
            val intent =
                Intent(this, RegisterAndRecognizeActivity::class.java)
            intent.putExtra(Constant.ToActivity, Constant.ToCompareFace)
            startActivity(intent)
        }


    }

    private fun insert() {

        val username = "xsc" // 替换为你的数据库用户名
        val password = "xsc20240401." // 替换为你的数据库密码
//        OpenGaussJavaHelper.getConnect("xsc","xsc20240401.")

//        OpenGaussJavaHelper.registerUser(null)

    }

    override fun onDestroy() {
        super.onDestroy()
        //移除所有粘性事件
        EventBus.getDefault().removeAllStickyEvents()
        //解除注册
        EventBus.getDefault().unregister(this)

    }


    companion object {
        val TAG: String = "RegisterActivity"
    }

}







