package com.dustanmbaga.pps.acaricide

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.dustanmbaga.pps.R
import com.dustanmbaga.pps.getDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.internal.bind.ArrayTypeAdapter.FACTORY
import com.google.gson.internal.bind.ObjectTypeAdapter.FACTORY
import com.google.gson.internal.bind.TimeTypeAdapter.FACTORY
import kotlinx.android.synthetic.main.fragment_acaricides.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AcaricideFragment : Fragment() {

    private lateinit var fabRefresh: FloatingActionButton
    private lateinit var acaricideRecyclerview: RecyclerView
    lateinit var acaricideViewModel: AcaricideViewModel

    private lateinit var adapter: AcaricideListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_acaricides, container, false)

        //acaricideViewModel = ViewModelProvider(this).get(AcaricideViewModel::class.java)

        //----------------
        // Initializing Database
        // Get MainViewModel by passing a database to the factory
        //val database = context?.let { getDatabase(it) }
        /*val database = getDatabase((context as AppCompatActivity))
        val repository = AcaricideRepository(getNetworkService(),
            database.acaricideDao
        )
        acaricideViewModel = ViewModelProviders
            .of(this)
            .get(AcaricideViewModel::class.java)*/

        val database = getDatabase((context as AppCompatActivity))
        val repository = AcaricideRepository(getNetworkService(), database.acaricideDao)
        val acaricideViewModel = ViewModelProviders
            .of(this, AcaricideViewModel.FACTORY(repository))
            .get(AcaricideViewModel::class.java)

        //acaricideViewModel = ViewModelProvider(this).get(AcaricideViewModel::class.java)
        //----------------

        fabRefresh = root.findViewById(R.id.fab_refresh)

        /*fabRefresh.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                withContext(Dispatchers.Default) {
                    //acaricideViewModel.refreshAcaricides()

                    getAcaricideFromApi()
                }
            }
        }*/
        fabRefresh.setOnClickListener {
            //acaricideViewModel.changeState()
            //acaricideViewModel.getAcaricideFromApi()

            //replaceFragment(AcaricideDetailsFragment())

            acaricideViewModel.getAcaricideFromApi.observe(viewLifecycleOwner, Observer {
                //Toast.makeText(viewLifecycleOwner, "Data zipo", Toast.LENGTH_LONG)

                Log.d("AcaricideFragment", "Data zimepatikana")

                adapter.setAcaricides(it)
            })

        }

        /*
        acaricideViewModel.showProgress.observe(viewLifecycleOwner, Observer {
            if (it) {
                progressBar.visibility = VISIBLE
            } else {
                progressBar.visibility = GONE
            }
        })

        acaricideViewModel.acaricideList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.setAcaricides(it)
            }
        })

        */
        adapter = AcaricideListAdapter(context!!)
        acaricideRecyclerview = root.findViewById(R.id.acaricide_recyclerView)
        acaricideRecyclerview.adapter = adapter

        //-------------------------
        return root
    }

    /*private fun replaceFragment(fragment: Fragment) {
        val manager: FragmentManager = (context as AppCompatActivity).supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()

        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }*/
}