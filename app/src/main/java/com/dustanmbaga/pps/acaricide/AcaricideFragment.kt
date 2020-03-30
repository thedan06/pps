package com.dustanmbaga.pps.acaricide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.dustanmbaga.pps.R
import com.dustanmbaga.pps.getDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_acaricides.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        //----------------
        // Initializing Database
        val database = getDatabase((context as AppCompatActivity))
        val repository = AcaricideRepository(getNetworkService(), database.acaricideDao)

        acaricideViewModel = ViewModelProvider(viewModelStore, AcaricideViewModel.FACTORY(repository)).get(AcaricideViewModel::class.java)
        //----------------

        fabRefresh = root.findViewById(R.id.fab_refresh)

        fabRefresh.setOnClickListener {
            //acaricideViewModel.changeState()

            // Fetching from API and saving to the DB
            viewLifecycleOwner.lifecycleScope.launch {
                withContext(Dispatchers.Default) {
                    acaricideViewModel.refreshAcaricides()
                }
            }
        }

        // Observing changes in the DB and updating the UI
        acaricideViewModel.acaricideList.observe(viewLifecycleOwner, Observer {
            adapter.setAcaricides(it)
        })

        acaricideViewModel.showProgress.observe(viewLifecycleOwner, Observer {
            if (it) {
                progressBar.visibility = VISIBLE
            } else {
                progressBar.visibility = GONE
            }
        })

        adapter = AcaricideListAdapter(context!!)
        acaricideRecyclerview = root.findViewById(R.id.acaricide_recyclerView)
        acaricideRecyclerview.adapter = adapter

        return root
    }
}