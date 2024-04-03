package com.arcsoft.arcfacedemo.thisapp.loginorregister.register

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
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

    private var faceEntity: FaceEntity? = null

    override fun initDatum() {
        // 注册事件
        EventBus.getDefault().register(this);



        super.initDatum()
        viewModel =
            ViewModelProvider(this).get(RegisterViewModel::class.java)
        initViewModel(viewModel)





    }

    //处理黏性事件
    @Subscribe(threadMode = ThreadMode.ASYNC ,sticky = true)
    fun StickyEventBus(faceEntity: FaceEntity){
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
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioTeacher -> {
                    binding.studentNo.setText("请输入工号")
//                    AlertDialogHelper.showAlertDialogSingle(this, "这是一个弹窗消息","确定")
                    binding.faceCollection.visibility = View.GONE
                }

                R.id.radioStudent -> {
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

        binding.register.setOnClickListener {
//
//            val faceEntity : FaceEntity = null!!
//            //TODO:insertOpenGauss

//            GlobalScope.launch {
//                // 方法2：使用 GlobalScope 单例对象，调用 launch 开启协程
//                insert()
//
//            }
            if (isCollectionFace){
                GlobalScope.launch {
                    OpenGaussHelper.registerUser(faceEntity!!,binding.nickname.text.toString(),binding.password.text.toString(),binding.studentNo.text.toString())
                }
            }else{
                AlertDialogHelper.showAlertDialogSingle(this,"请收集人脸！","确认", object : AlertDialogHelper.OnSingleClickListener{
                    override fun Click() {

                    }
                })
            }
        }


        binding.faceCollection.setOnClickListener {
            val intent =
                Intent(this, RegisterAndRecognizeActivity::class.java) // 创建Intent，指定目标Activity
            intent.putExtra(
                Constant.ToActivity,
                Constant.ToFaceCollectionFromRegister
            ) // 使用putExtra方法传递数据
            startActivity(intent)
        }

        binding.home.setOnClickListener{
            startActivity(HomeActivity::class.java)
        }

    }

    private  fun insert() {

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







