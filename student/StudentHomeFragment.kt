package com.arcsoft.arcfacedemo.thisapp.student

import android.os.Bundle
import com.arcsoft.arcfacedemo.databinding.FragmentStudentHomeBinding
import com.arcsoft.arcfacedemo.thisapp.base.fragment.BaseViewModelFragment

class StudentHomeFragment: BaseViewModelFragment<FragmentStudentHomeBinding>() {



    companion object{
        fun newInstance(): StudentHomeFragment{
            val args = Bundle()

            val fragment = StudentHomeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}