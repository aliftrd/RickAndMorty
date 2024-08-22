package com.takehomechallenge.aliftrd.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.takehomechallenge.aliftrd.base.BaseFragment
import com.takehomechallenge.aliftrd.data.lib.ApiResponse
import com.takehomechallenge.aliftrd.databinding.FragmentHomeBinding
import com.takehomechallenge.aliftrd.utils.ext.gone
import com.takehomechallenge.aliftrd.utils.ext.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val vm: HomeViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun initIntent() {
        //
    }

    override fun initUI() {
        with(b) {
            emptyLayout.root.show()
            loadingLayout.root.gone()
            rvCharacters.gone()
        }
    }

    override fun initAction() {
        //
    }

    override fun initProcess() {
        vm.getCharacters()
    }

    override fun initObserver() {
        vm.characters.observe(viewLifecycleOwner) { characters ->
            when (characters) {
                is ApiResponse.Loading -> {
                    with(b) {
                        loadingLayout.root.show()
                        emptyLayout.root.gone()
                        rvCharacters.gone()
                    }
                }
                is ApiResponse.Success -> {
                    with(b) {
                        rvCharacters.show()
                        emptyLayout.root.gone()
                        loadingLayout.root.gone()
                    }
                    val a = HomeAdapter {
                        toDetailFragment(it.id.toString())
                    }
                    a.submitList(characters.data)
                    b.rvCharacters.apply {
                        adapter = a
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    }
                }
                is ApiResponse.Empty -> {
                    with(b) {
                        emptyLayout.root.show()
                        rvCharacters.gone()
                        loadingLayout.root.gone()
                    }
                }
                is ApiResponse.Error -> {
                    with(b) {
                        emptyLayout.root.show()
                        rvCharacters.gone()
                        loadingLayout.root.gone()
                    }
                    Toast.makeText(context, characters.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun toDetailFragment(characterId: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(characterId)
        findNavController().navigate(action)
    }
}