package com.dustanmbaga.pps.acaricide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dustanmbaga.pps.R

class AcaricideListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<AcaricideListAdapter.AcaricideViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var acaricides = emptyList<Acaricide>()

    inner class AcaricideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var acaricideNo: TextView = itemView.findViewById(R.id.acaricide_item_no)
        var acaricideCommonName: TextView = itemView.findViewById(R.id.acaricide_item_common_name)
        var acaricideRegNo: TextView = itemView.findViewById(R.id.acaricide_item_reg_no)
        var acaricideRegistrant: TextView = itemView.findViewById(R.id.acaricide_item_registrant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcaricideViewHolder {
        val itemView = inflater.inflate(R.layout.list_acaricide_item, parent, false)
        return AcaricideViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AcaricideViewHolder, position: Int) {
        val current = acaricides[position]

        holder.acaricideNo.text = current.id.toString()
        holder.acaricideCommonName.text = current.commonName
        holder.acaricideRegNo.text = current.regNumber
        holder.acaricideRegistrant.text = current.registrant
    }

    internal fun setAcaricides(acaricides: Array<Acaricide>) {
        this.acaricides = acaricides.toList()

        notifyDataSetChanged()
    }

    override fun getItemCount() = acaricides.size
}