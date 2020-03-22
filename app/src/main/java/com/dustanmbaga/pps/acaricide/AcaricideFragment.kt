package com.dustanmbaga.pps.acaricide

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dustanmbaga.pps.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_acaricides.*


class AcaricideFragment : Fragment() {

    private lateinit var fabRefresh: FloatingActionButton
    private lateinit var acaricideViewModel: AcaricideViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_acaricides, container, false)

        //-------------------------
        /*val recyclerView = root.findViewById<RecyclerView>(R.id.acaricide_recyclerView)
        val adapter = AcaricideListAdapter(context!!)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context!!)*/

        acaricideViewModel = ViewModelProvider(this).get(AcaricideViewModel::class.java)

        fabRefresh = root.findViewById<FloatingActionButton>(R.id.fab_refresh)
        fabRefresh.setOnClickListener {
            acaricideViewModel.changeState()
        }
        //getSupportFragmentManager

        fabRefresh.setOnClickListener {
//            val fm: FragmentManager =
//            fm.beginTransaction().add(R.layout.container_id, OtherFragmentObject()).commit();

        }

        acaricideViewModel.showProgress.observe(viewLifecycleOwner, Observer {
            if (it) {
                progressBar.visibility = VISIBLE
            } else {
                progressBar.visibility = GONE
            }
        })

        //-------------------------
        return root
    }
}