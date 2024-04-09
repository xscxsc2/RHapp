package com.arcsoft.arcfacedemo.thisapp.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.arcsoft.app.staticdata.Constant
import com.arcsoft.app.util.PreferenceUtil
import com.arcsoft.arcfacedemo.thisapp.me.MeHomeFragment
import com.arcsoft.arcfacedemo.thisapp.student.StudentHomeFragment
import com.arcsoft.arcfacedemo.thisapp.teacher.TeacherHomeFragment

class HomeAppAdapter(fragmentActivity: FragmentActivity, private val count: Int, val client: String) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return count
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                if (client == Constant.clientStudent){
                    StudentHomeFragment.newInstance()
                }else{
                    TeacherHomeFragment.newInstance()
                }
            }
            else -> {
                MeHomeFragment.newInstance()
            }
        }
    }

}