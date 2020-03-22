package com.dustanmbaga.pps.acaricide

import android.content.ContentValues
import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dustanmbaga.pps.R

/*

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

                */
/*filteredList = mAcaricides.filter {
                    it.commonName == filterPattern || it.regNumber == filterPattern || it.registrant == filterPattern
                }*//*


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

//
class AcaricideAdapters(@Nullable clickCallback: AcaricideClickCallback) :
    RecyclerView.Adapter<AcaricideAdapter.AcaricideViewHolder>() {
    var mAcaricideList: List<Acaricide?>? = null

    @Nullable
    private val mAcaricideClickCallback: AcaricideClickCallback
    
    fun setAcaricideList(acaricideList: List<Acaricide?>) {
        if (mAcaricideList == null) {
            mAcaricideList = acaricideList
            notifyItemRangeInserted(0, acaricideList.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return mAcaricideList!!.size
                }

                override fun getNewListSize(): Int {
                    return acaricideList.size
                }

                override fun areItemsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    return mAcaricideList!![oldItemPosition]!!.id == acaricideList[newItemPosition]!!.id
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    val newAcaricide: Acaricide? = acaricideList[newItemPosition]
                    val oldAcaricide: Acaricide? = mAcaricideList!![oldItemPosition]
                    return (newAcaricide!!.id == oldAcaricide!!.id && TextUtils.equals(
                        newAcaricide.commonName,
                        oldAcaricide.commonName
                    )
                        && TextUtils.equals(newAcaricide.tradeName, oldAcaricide.tradeName)
                        && newAcaricide.regNumber === oldAcaricide.regNumber)
                }
            })
            mAcaricideList = acaricideList
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AcaricideViewHolder {
        val binding: AcaricideItemBinding = DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context), R.layout.acaricide_item,
                parent, false
            )
        binding.setCallback(mAcaricideClickCallback)
        return AcaricideViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AcaricideViewHolder,
        position: Int
    ) {
        holder.binding.setAcaricide(mAcaricideList!![position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return if (mAcaricideList == null) 0 else mAcaricideList.size
    }

    override fun getItemId(position: Int): Long {
        return mAcaricideList!![position].getId()
    }

    class AcaricideViewHolder(binding: AcaricideItemBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {
        val binding: AcaricideItemBinding

        init {
            this.binding = binding
        }
    }

    init {
        mAcaricideClickCallback = clickCallback
        setHasStableIds(true)
    }
}

interface AcaricideClickCallback {
    fun onClick(acaricide: Acaricide?)
}


//-----------------------

*/

