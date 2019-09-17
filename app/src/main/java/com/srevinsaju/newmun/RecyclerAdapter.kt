package com.srevinsaju.newmun

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val titles1: Array<out String>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView
        init {
            itemTitle = itemView.findViewById(R.id.textView5)
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recyclerviewadapt, viewGroup, false)
        return ViewHolder(v)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(1)
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(1)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles1[i]
    }
    override fun getItemCount(): Int {
        return titles1.size
    }
}
