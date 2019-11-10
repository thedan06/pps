package com.dustanmbaga.pps.ui.acaricides

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dustanmbaga.pps.R

class AcaricidesFragment : Fragment() {

    private lateinit var homeViewModel: AcaricidesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(AcaricidesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_acaricides, container, false)
        val textView: TextView = root.findViewById(R.id.text_acaricides)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}