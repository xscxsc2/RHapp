package com.arcsoft.arcfacedemo.thisapp.teacher

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.arcsoft.arcfacedemo.thisapp.category.Category
import com.arcsoft.arcfacedemo.thisapp.teacher.curriculum.CurriculumFragment

class TeacherHomeAdapter(fragmentActivity: FragmentActivity, private val datum: List<Category>) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return datum.size
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            1 -> CurriculumFragment.newInstance(datum[position].id)
            else -> CurriculumFragment.newInstance(datum[position].id)
        }

    }

}