package com.takehomechallenge.aliftrd.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import coil.load
import com.takehomechallenge.aliftrd.R
import com.takehomechallenge.aliftrd.base.BaseFragment
import com.takehomechallenge.aliftrd.databinding.FragmentDetailBinding
import com.takehomechallenge.aliftrd.domain.character.model.Character
import com.takehomechallenge.aliftrd.utils.ext.click
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    private val vm: DetailViewModel by viewModel()
    private var characterId: String? = null
    private var character: Character? = null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false)

    override fun initIntent() {
        characterId = arguments?.getString("character_id")
    }

    override fun initUI() {
        //
    }

    override fun initAction() {
        b.apply {
            btnBack.click {
                findNavController().popBackStack()
            }
        }
    }

    override fun initProcess() {
        vm.getDetailCharacter(characterId.toString())
        vm.checkIsFavorite(characterId.toString())
    }

    override fun initObserver() {
        vm.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            with(b) {
                val drawable =
                    if (isFavorite) R.drawable.ic_fav_filled else R.drawable.ic_fav_border
                btnFavorite.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), drawable)
                )
                btnFavorite.click {
                    Log.d("DetailFragment", "isFavorite: $isFavorite")
                    btnFavoriteClick(isFavorite)
                }
            }
        }

        vm.character.observe(viewLifecycleOwner) { character ->
            when (character) {
                is com.takehomechallenge.aliftrd.data.lib.ApiResponse.Loading -> {
                    Log.d("DetailFragment", "Loading")
                }

                is com.takehomechallenge.aliftrd.data.lib.ApiResponse.Success -> {
                    this.character = character.data
                    with(b) {
                        ivCharacter.load(character.data.image)
                        tvSpecies.text = character.data.species
                        tvStatus.text = character.data.status
                        "${character.data.name} (${character.data.gender})".also {
                            tvName.text = it
                        }
                        tvLocation.text = character.data.location
                        tvOrigin.text = character.data.origin
                    }
                }

                is com.takehomechallenge.aliftrd.data.lib.ApiResponse.Empty -> {
                    Log.d("DetailFragment", "Empty")
                }

                is com.takehomechallenge.aliftrd.data.lib.ApiResponse.Error -> {
                    Log.d("DetailFragment", "Error")
                }
            }
        }
    }

    private fun btnFavoriteClick(isFavorite: Boolean) {
        if (isFavorite) {
            vm.deleteFromFavorite(characterId.toString())
        } else {
            vm.saveToFavorite(character!!)
        }

    }

}