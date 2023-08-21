package com.mylong.dragonite.data.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mylong.dragonite.R
import com.mylong.dragonite.data.models.Drilling

class DrillingAdapter(
    private var drillingList: List<Drilling>,
    private val btnClick: (Drilling) -> Unit
): RecyclerView.Adapter<DrillingAdapter.DrillingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrillingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DrillingViewHolder(layoutInflater.inflate(R.layout.item_drilling, parent, false))
    }

    override fun onBindViewHolder(holder: DrillingViewHolder, position: Int) {
        val item = drillingList[position]
        holder.render(item, btnClick)
    }

    override fun getItemCount(): Int = drillingList.size

    class DrillingViewHolder(view: View): RecyclerView.ViewHolder(view)  {

        private var title: TextView = view.findViewById(R.id.itemTvDrillingTile)
        private var image: ImageView = view.findViewById(R.id.itemIvDrilling)

        fun render(drilling: Drilling, btnClick:(Drilling) -> Unit) {
            title.text = drilling.title
            image.setImageResource(drilling.image)
            itemView.setOnClickListener { btnClick(drilling) }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDrilling(drilling: List<Drilling>) {
        this.drillingList = drilling
        notifyDataSetChanged()
    }


}