package com.takehomechallenge.aliftrd.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.takehomechallenge.aliftrd.R
import com.takehomechallenge.aliftrd.base.BaseFragment
import com.takehomechallenge.aliftrd.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment: BaseFragment<FragmentSplashBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSplashBinding = FragmentSplashBinding.inflate(inflater, container, false)

    override fun initIntent() {
        //
    }

    override fun initUI() {
        //
    }

    override fun initAction() {
        //
    }

    override fun initProcess() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(3000L)
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }
    }

    override fun initObserver() {
        //
    }

}