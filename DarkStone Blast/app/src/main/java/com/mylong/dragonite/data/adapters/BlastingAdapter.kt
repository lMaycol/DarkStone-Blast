package com.mylong.dragonite.data.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mylong.dragonite.R
import com.mylong.dragonite.data.models.Blasting

class BlastingAdapter(
    private var blastingList: List<Blasting>,
    private val btnClick: (Blasting) -> Unit
) : RecyclerView.Adapter<BlastingAdapter.BlastingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlastingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BlastingViewHolder(layoutInflater.inflate(R.layout.item_blasting, parent, false))
    }

    override fun onBindViewHolder(holder: BlastingViewHolder, position: Int) {
        val item = blastingList[position]
        holder.render(item, btnClick)
    }

    override fun getItemCount(): Int = blastingList.size

    class BlastingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var title: TextView = view.findViewById(R.id.itemTvBlastingTile)
        private var image: ImageView = view.findViewById(R.id.itemIvBlasting)

        fun render(blasting: Blasting, btnClick: (Blasting) -> Unit) {
            title.text = blasting.title
            image.setImageResource(blasting.image)
            itemView.setOnClickListener { btnClick(blasting) }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateBlasting(blasting: List<Blasting>) {
        this.blastingList = blasting
        notifyDataSetChanged()
    }

}