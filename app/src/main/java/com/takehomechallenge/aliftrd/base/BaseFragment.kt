package com.takehomechallenge.aliftrd.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding>: Fragment() {
    private var _b: VB? = null
    val b get() = _b!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _b = getViewBinding(inflater, container, savedInstanceState)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initIntent()
        initUI()
        initAction()
        initProcess()
        initObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }

    abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): VB

    abstract fun initIntent()
    abstract fun initUI()
    abstract fun initAction()
    abstract fun initProcess()
    abstract fun initObserver()
}