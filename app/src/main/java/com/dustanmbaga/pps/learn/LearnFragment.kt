package com.dustanmbaga.pps.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dustanmbaga.pps.R

class LearnFragment : Fragment() {

    private lateinit var learnViewModel: LearnViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        learnViewModel = ViewModelProvider(this).get(LearnViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_learn, container, false)
        val textView: TextView = root.findViewById(R.id.text_learn)
        learnViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}