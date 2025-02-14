package com.dzulfikri.suitmediatestapp.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dzulfikri.suitmediatestapp.data.response.DataItem
import com.dzulfikri.suitmediatestapp.databinding.ItemUsersBinding
import com.dzulfikri.suitmediatestapp.screen.SecondScreen

class UserPagingAdapter : PagingDataAdapter<DataItem,UserPagingAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding: ItemUsersBinding) : RecyclerView.ViewHolder(binding.root){

        @SuppressLint("SetTextI18n")
        fun bind(data: DataItem){
            binding.tvUserName.text = "${data.firstName} ${data.lastName}"
            binding.tvUserEmail.text = data.email
            Glide.with(itemView.context)
                .load(data.avatar)
                .circleCrop()
                .into(binding.ivUser)

            itemView.setOnClickListener{
                val name = "${data.firstName} ${data.lastName}"
                val intent = Intent(itemView.context, SecondScreen::class.java)
                intent.putExtra("EXTRA_SELECTED_NAME",name)
                itemView.context.startActivity(intent)
            }
        }


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null){
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemUsersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}