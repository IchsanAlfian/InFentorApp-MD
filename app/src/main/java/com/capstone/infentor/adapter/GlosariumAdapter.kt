package com.capstone.infentor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.infentor.databinding.ItemGlosariumBinding
import com.capstone.infentor.response.JobsItem


class GlosariumAdapter(private val listGlosarium: List<JobsItem>) :
    RecyclerView.Adapter<GlosariumAdapter.ViewHolder>() {

    interface OnItemClickCallback {
        fun onItemClicked(data: JobsItem)
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ViewHolder(private val binding: ItemGlosariumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(glosarium: JobsItem) {
            binding.apply {
                tvName.text = glosarium.famousExample
                tvJob.text = glosarium.nameJob
                tvStrong.text = glosarium.strongIn
                Glide.with(itemView.context)
                    .load(glosarium.urlPictFamous)
                    .centerCrop()
                    .into(profileImage)

                root.setOnClickListener {
                    onItemClickCallback?.onItemClicked(glosarium)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGlosariumBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listGlosarium[position])
    }

    override fun getItemCount() = listGlosarium.size
}
