package com.takehomechallenge.aliftrd.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.takehomechallenge.aliftrd.base.BaseFragment
import com.takehomechallenge.aliftrd.data.lib.ApiResponse
import com.takehomechallenge.aliftrd.databinding.FragmentSearchBinding
import com.takehomechallenge.aliftrd.utils.ext.gone
import com.takehomechallenge.aliftrd.utils.ext.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val vm: SearchViewModel by viewModel()
    override fun getViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)

    override fun initIntent() {
        //
    }

    override fun initUI() {
        with(b) {
            emptyLayout.root.show()
            loadingLayout.root.gone()
            rvSearchCharacters.gone()
        }
    }

    override fun initAction() {
        //
    }

    override fun initProcess() {
        with(b) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { _, _, _ ->
                searchBar.setText(searchView.text)
                searchView.hide()
                vm.searchCharacters(searchBar.text.toString())
                false
            }
        }
    }

    override fun initObserver() {
        vm.characters.observe(viewLifecycleOwner) { characters ->
            when (characters) {
                is ApiResponse.Loading -> {
                    with(b) {
                        loadingLayout.root.show()
                        emptyLayout.root.gone()
                        rvSearchCharacters.gone()
                    }
                }
                is ApiResponse.Success -> {
                    with(b) {
                        rvSearchCharacters.show()
                        emptyLayout.root.gone()
                        loadingLayout.root.gone()
                    }

                    val a = SearchAdapter {
                        toDetailFragment(it.id.toString())
                    }
                    a.submitList(characters.data)
                    b.rvSearchCharacters.apply {
                        adapter = a
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    }
                }
                is ApiResponse.Empty -> {
                    with(b) {
                        emptyLayout.root.show()
                        rvSearchCharacters.gone()
                        loadingLayout.root.gone()
                    }
                }
                is ApiResponse.Error -> {
                    with(b) {
                        emptyLayout.root.show()
                        rvSearchCharacters.gone()
                        loadingLayout.root.gone()
                    }
                    Toast.makeText(context, characters.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun toDetailFragment(characterId: String) {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(characterId)
        findNavController().navigate(action)
    }
}