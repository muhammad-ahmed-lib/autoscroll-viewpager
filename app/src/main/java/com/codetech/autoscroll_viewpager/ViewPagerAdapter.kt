package com.codetech.autoscroll_viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codetech.autoscroll_viewpager.databinding.PagerLayoutBinding

class ViewPagerAdapter :
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
        private val mList by lazy {ArrayList<PagerModelClass>()  }
    class ViewHolder(private val binding: PagerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        internal fun bind(item:PagerModelClass){
            Glide.with(itemView.context).load(item.page).into(binding.imageView)
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Current item ${item.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun submitList(list:List<PagerModelClass>){
        mList.clear()
        mList.addAll(list)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PagerLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int = mList.size
}