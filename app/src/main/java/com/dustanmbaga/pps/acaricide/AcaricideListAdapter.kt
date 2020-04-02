package com.dustanmbaga.pps.acaricide

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.dustanmbaga.pps.R
import com.dustanmbaga.pps.acaricide.details.AcaricideDetailsFragment
import kotlinx.android.synthetic.main.list_acaricide_item.view.*

class AcaricideListAdapter (
    private val context: Context
) : RecyclerView.Adapter<AcaricideListAdapter.ViewHolder>() {

    private var acaricides: List<Acaricide> = ArrayList()

    override fun getItemCount() = acaricides.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_acaricide_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = acaricides[position]

        holder.acaricideNo.text = (position + 1).toString() //current.id.toString()
        holder.acaricideCommonName.text = current.commonName
        holder.acaricideRegNo.text = current.regNumber
        holder.acaricideRegistrant.text = current.registrant

        val cardView: CardView = holder.itemView.acaricide_root_item

        // Open Details Fragment
        cardView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id", current.id.toString())
            bundle.putString("commonName", current.commonName)
            bundle.putString("regNumber", current.regNumber)
            bundle.putString("regCategory", current.regCategory)
            bundle.putString("tradeName", current.tradeName)
            bundle.putString("registrant", current.registrant)
            bundle.putString("usage", current.usage)

            val newFragment = AcaricideDetailsFragment()
            newFragment.arguments = bundle

            replaceFragment(newFragment)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val manager: FragmentManager = (context as AppCompatActivity).supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()

        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    internal fun setAcaricides(acaricides: List<Acaricide>) {
        this.acaricides = acaricides
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var acaricideNo: TextView = itemView.findViewById(R.id.acaricide_item_no)
        var acaricideCommonName: TextView = itemView.findViewById(R.id.acaricide_item_common_name)
        var acaricideRegNo: TextView = itemView.findViewById(R.id.acaricide_item_reg_no)
        var acaricideRegistrant: TextView = itemView.findViewById(R.id.acaricide_item_registrant)
    }
}