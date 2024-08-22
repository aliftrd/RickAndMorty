package com.takehomechallenge.aliftrd.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.takehomechallenge.aliftrd.databinding.CharactersItemBinding
import com.takehomechallenge.aliftrd.domain.character.model.LocalCharacter
import com.takehomechallenge.aliftrd.utils.ext.click

class FavoriteAdapter(private val onClick: (LocalCharacter) -> Unit) : ListAdapter<LocalCharacter, FavoriteAdapter.ListViewHolder>(DIFF_CALLBACK) {
    inner class ListViewHolder(private val b: CharactersItemBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(character: LocalCharacter) {
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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LocalCharacter>() {
            override fun areItemsTheSame(oldItem: LocalCharacter, newItem: LocalCharacter): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: LocalCharacter, newItem: LocalCharacter): Boolean {
                return oldItem == newItem
            }
        }
    }
}