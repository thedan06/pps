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
    private lateinit var commonName: TextView
    private lateinit var regNumber: TextView
    private lateinit var registrar: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_acaricide_details, container, false)

        commonName = root.findViewById(R.id.commonName)
        regNumber = root.findViewById(R.id.registeredName)
        registrar = root.findViewById(R.id.registrar)

        val bundle = this.arguments
        if (bundle != null) {
            val commonNameValue = bundle.get("commonName")
            val regNumberValue = bundle.get("regNumber")
            val registrarValue = bundle.get("registrant")

            commonName.text = commonNameValue.toString()
            regNumber.text = regNumberValue.toString()
            registrar.text = registrarValue.toString()
        }

        return root
    }
}