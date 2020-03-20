package com.dustanmbaga.pps.acaricide

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dustanmbaga.pps.R

class AcaricideRecyclerViewAdapter(private val mContext: Context) :
    RecyclerView.Adapter<AcaricideRecyclerViewAdapter.AcaricideViewHolder>(), Filterable {

    private lateinit var mAcaricides: MutableList<Acaricide>
    private lateinit var mAcaricidesFull: List<Acaricide>
    //private lateinit var mAcaricidesFull2: ArrayList<Acaricide>

    fun getAcaricides() = mAcaricides

    fun setAcaricides(acaricides: MutableList<Acaricide>) {
        this.mAcaricides = acaricides
        mAcaricidesFull = ArrayList<Acaricide>(acaricides)

        //mAcaricidesFull2 = ArrayList(acaricides)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): AcaricideViewHolder {
        Log.d(ContentValues.TAG, "onCreateAnimalsViewHolder: ")

        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_acaricide_item, viewGroup, false)
        return AcaricideViewHolder(view)
    }

    override fun onBindViewHolder(acaricideViewHolder: AcaricideViewHolder, position: Int) {
        acaricideViewHolder.acaricideNo.text = mAcaricides[position].id.toString()
        acaricideViewHolder.acaricideCommonName.text = mAcaricides[position].commonName
        acaricideViewHolder.acaricideRegNo.text = mAcaricides[position].regNumber
        acaricideViewHolder.acaricideRegistrant.text = mAcaricides[position].registrant
    }

    override fun getItemCount() = mAcaricides.size

    class AcaricideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var acaricideNo: TextView = itemView.findViewById(R.id.acaricide_item_no)
        internal var acaricideCommonName: TextView = itemView.findViewById(R.id.acaricide_item_common_name)
        internal var acaricideRegNo: TextView = itemView.findViewById(R.id.acaricide_item_reg_no)
        internal var acaricideRegistrant: TextView = itemView.findViewById(R.id.acaricide_item_registrant)
    }


    override fun getFilter(): Filter {
        return acaricidesFilter
    }

    private val acaricidesFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults? {

            var filteredList: ArrayList<Acaricide> = arrayListOf()

            if (constraint.isEmpty()) {

                filteredList.addAll(mAcaricidesFull)

            } else {
                val filterPattern = constraint.toString().toLowerCase().trim()

                /*filteredList = mAcaricides.filter {
                    it.commonName == filterPattern || it.regNumber == filterPattern || it.registrant == filterPattern
                }*/

                mAcaricidesFull.iterator().forEach {
                    if (checkPattern(it.toString(), filterPattern)) {
                        filteredList.add(it)
                    }
                }
            }

            val results = FilterResults()
            results.values = filteredList

            return results
        }

        fun checkPattern(searchIn: String, searchThis: String): Boolean {
            return searchIn.toLowerCase().contains(searchThis)
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            mAcaricides.clear()

            //var newList: MutableList<Acaricide> = results.values as MutableList<Acaricide>
            //mAcaricides.addAll(newList)
            mAcaricides.addAll(results as List<Acaricide>)


            notifyDataSetChanged()
        }
    }
}