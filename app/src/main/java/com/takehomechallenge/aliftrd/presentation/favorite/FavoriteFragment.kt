package com.takehomechallenge.aliftrd.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.takehomechallenge.aliftrd.base.BaseFragment
import com.takehomechallenge.aliftrd.databinding.FragmentFavoriteBinding
import com.takehomechallenge.aliftrd.utils.ext.gone
import com.takehomechallenge.aliftrd.utils.ext.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {
    private val vm: FavoriteViewModel by viewModel()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentFavoriteBinding = FragmentFavoriteBinding.inflate(inflater, container, false)

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
        vm.getLocalCharacters()
    }

    override fun initObserver() {
        vm.characters.observe(viewLifecycleOwner) {
            when {
                it.isEmpty() -> {
                    b.emptyLayout.root.show()
                    b.rvCharacters.gone()
                }
                else -> {
                    b.emptyLayout.root.gone()
                    b.rvCharacters.show()

                    val a = FavoriteAdapter {
                        toDetailFragment(it.id.toString())
                    }
                    a.submitList(it)
                    b.rvCharacters.apply {
                        adapter = a
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    }
                }
            }
        }
    }

    private fun toDetailFragment(characterId: String) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(characterId)
        findNavController().navigate(action)
    }
}