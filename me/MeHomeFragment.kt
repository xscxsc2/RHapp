package com.arcsoft.arcfacedemo.thisapp.me

import android.os.Bundle
import com.arcsoft.arcfacedemo.databinding.FragmentMeHomeBinding
import com.arcsoft.arcfacedemo.thisapp.base.fragment.BaseViewModelFragment

class MeHomeFragment: BaseViewModelFragment<FragmentMeHomeBinding>(){





    companion object{
        fun newInstance(): MeHomeFragment{
            val args = Bundle()

            val fragment = MeHomeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}