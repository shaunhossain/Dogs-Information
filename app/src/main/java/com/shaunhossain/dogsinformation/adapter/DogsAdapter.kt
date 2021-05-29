package com.shaunhossain.dogsinformation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shaunhossain.dogsinformation.databinding.RowItemBinding
import com.shaunhossain.dogsinformation.model.dogs.DogsItem
import javax.inject.Inject

class DogsAdapter
    @Inject
    constructor(): PagingDataAdapter<DogsItem,DogsAdapter.DogsViewHolder>(Diff) {

    inner class DogsViewHolder(private val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind( dogsItem: DogsItem){
            binding.apply {
                image.load(dogsItem.url)
                //name.text = dogsItem.breeds.elementAt(0).name.toString()
            }
        }
    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        val dogs = getItem(position)
        if (dogs!= null){
            holder.bind(dogs)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        return DogsViewHolder(RowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    object Diff : DiffUtil.ItemCallback<DogsItem>(){
        override fun areItemsTheSame(oldItem: DogsItem, newItem: DogsItem): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: DogsItem, newItem: DogsItem): Boolean {
            return oldItem == newItem
        }


    }

}