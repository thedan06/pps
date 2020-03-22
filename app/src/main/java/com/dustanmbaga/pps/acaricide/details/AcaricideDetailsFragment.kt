package com.dustanmbaga.pps.acaricide.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.dustanmbaga.pps.R

class AcaricideDetailsFragment : Fragment() {

    private lateinit var acaricideDetailsViewModel: AcaricideDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_acaricide_details, container, false)

        return root
    }
}