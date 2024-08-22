package com.takehomechallenge.aliftrd.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.takehomechallenge.aliftrd.databinding.CharactersItemBinding
import com.takehomechallenge.aliftrd.domain.character.model.Character
import com.takehomechallenge.aliftrd.utils.ext.click

class SearchAdapter(private val onClick: (Character) -> Unit) : ListAdapter<Character, SearchAdapter.ListViewHolder>(DIFF_CALLBACK) {
    inner class ListViewHolder(private val b: CharactersItemBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(character: Character) {
            with(b) {
                ivCharacter.load(character.image)
                tvSpecies.text = character.species
                tvStatus.text = character.status
                tvName.text = character.name
                tvLocation.text = character.location
                tvOrigin.text = character.origin

                root.click {
                    onClick.invoke(character)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: CharactersItemBinding = CharactersItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)

        val itemView = holder.itemView
        val isLastItem = position == itemCount - 1
        val params = itemView.layoutParams as ViewGroup.MarginLayoutParams
        params.bottomMargin = if (!isLastItem) 0 else 10
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }
}