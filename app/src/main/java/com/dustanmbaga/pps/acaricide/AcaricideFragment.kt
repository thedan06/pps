package com.dustanmbaga.pps.acaricide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.dustanmbaga.pps.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_acaricides.*

class AcaricideFragment : Fragment() {

    private lateinit var fabRefresh: FloatingActionButton
    private lateinit var acaricideRecyclerview: RecyclerView
    private lateinit var acaricideViewModel: AcaricideViewModel

    private lateinit var adapter: AcaricideListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_acaricides, container, false)

        acaricideViewModel = ViewModelProvider(this).get(AcaricideViewModel::class.java)

        fabRefresh = root.findViewById(R.id.fab_refresh)
        fabRefresh.setOnClickListener {
            //acaricideViewModel.changeState()
            acaricideViewModel.getAcaricideFromApi()
            //replaceFragment(AcaricideDetailsFragment())
        }

        acaricideViewModel.showProgress.observe(viewLifecycleOwner, Observer {
            if (it) {
                progressBar.visibility = VISIBLE
            } else {
                progressBar.visibility = GONE
            }
        })

        acaricideViewModel.acaricideList.observe(viewLifecycleOwner, Observer {
            adapter.setAcaricides(it)
        })

        adapter = AcaricideListAdapter(context!!)

        acaricideRecyclerview = root.findViewById(R.id.acaricide_recyclerView)
        acaricideRecyclerview.adapter = adapter

        //-------------------------
        return root
    }

    private fun replaceFragment(fragment: Fragment) {
        val manager: FragmentManager = (context as AppCompatActivity).supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()

        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}