package com.dustanmbaga.pps.acaricide.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dustanmbaga.pps.R


class AcaricideDetailsFragment : Fragment() {

    private lateinit var acaricideDetailsViewModel: AcaricideDetailsViewModel
    //private lateinit var tradeNameTitle: TextView
    private lateinit var commonName: TextView
    private lateinit var regNumber: TextView
    private lateinit var registrant: TextView
    private lateinit var regCategory: TextView
    private lateinit var tradeName: TextView
    private lateinit var usage: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_acaricide_details, container, false)

        // tradeNameTitle = root.findViewById(R.id.tradeNameTitle)
        commonName = root.findViewById(R.id.commonName)
        regNumber = root.findViewById(R.id.regNumber)
        registrant = root.findViewById(R.id.registrant)
        regCategory = root.findViewById(R.id.registrationCategory)
        tradeName = root.findViewById(R.id.tradeName)
        usage = root.findViewById(R.id.usage)

        val bundle = this.arguments
        if (bundle != null) {
            val commonNameValue = bundle.get("commonName")
            val regNumberValue = bundle.get("regNumber")
            val registrarValue = bundle.get("registrant")

            commonName.text = commonNameValue.toString()
            regNumber.text = regNumberValue.toString()
            registrant.text = registrarValue.toString()
            //tradeNameTitle.text = bundle.get("tradeName").toString()
            tradeName.text = bundle.get("tradeName").toString()
            usage.text = bundle.get("usage").toString()
            regCategory.text = bundle.get("regCategory").toString()
        }

        return root
    }
}