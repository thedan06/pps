package com.dustanmbaga.pps.ui.acaricides.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.dustanmbaga.pps.R

class AcaricideDetailsFragment : Fragment() {

    private lateinit var homeViewModel: AcaricideDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(AcaricideDetailsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_acaricide_details, container, false)
//        val textView: TextView = root.findViewById(R.id.text_acaricides)
//        homeViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        return root
    }
}