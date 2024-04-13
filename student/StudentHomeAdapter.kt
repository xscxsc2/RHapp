package com.arcsoft.arcfacedemo.thisapp.student

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.arcsoft.arcfacedemo.thisapp.category.Category
import com.arcsoft.arcfacedemo.thisapp.student.thisstudent.ThisCurriculumFragment

class StudentHomeAdapter(fragmentActivity: FragmentActivity, private val datum: List<Category>) :
    FragmentStateAdapter(fragmentActivity)  {
    override fun getItemCount(): Int {
        return datum.size
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            1 -> ThisCurriculumFragment.newInstance(datum[position].id)
            else -> ThisCurriculumFragment.newInstance(datum[position].id)
        }
    }
}