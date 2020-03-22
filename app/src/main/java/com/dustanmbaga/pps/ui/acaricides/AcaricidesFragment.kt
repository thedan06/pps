package com.dustanmbaga.pps.ui.acaricides

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dustanmbaga.pps.R
import com.dustanmbaga.pps.acaricide.Acaricide
//import com.dustanmbaga.pps.acaricide.AcaricideRecyclerViewAdapter
import com.dustanmbaga.pps.acaricide.AcaricideViewModel
/*


class AcaricidesFragment : Fragment() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var acaricideAdapter: AcaricideRecyclerViewAdapter

    //private var acaricides = myAcaricideViewModel.getAllAcaricides()

    private var acaricides: List<Acaricide> = mutableListOf()

    private var initialAcaricides: MutableList<Acaricide> = mutableListOf(
        Acaricide(1, "Taktic 12.5EC", "Amitraz 125g/l", "AC/0018", "Intervet South Africa (Pty)", "Control of Mange, mites, ticks and lice on cattle.", "Full Registration"),

        Acaricide(2, "Tixfix 12.5%EC", "Amitraz 125g/l", "AC/0019", "Rotam Limited-Kenya", "Control of Ticks on cattle.", "Full Registration"),

        Acaricide(3, "Toptix 12.5%EC", "Amitraz 125g/l", "AC/0020", "Alfasan International Holland", "Control of ticks & Ectoparasites on cattle", "Full Registration"),

        Acaricide(4, "Norotraz 12.5%", "Amitraz 125g/l", "AC/0022", "Norbrook Laboratories-Kenya", "Control of Ticks on cattle.", "Full Registration")
    )

    private lateinit var acaricidesViewModel: AcaricideViewModel

    private lateinit var myAcaricideViewModel: AcaricideViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        */
/*acaricidesViewModel =
            ViewModelProviders.of(this).get(AcaricidesViewModel::class.java)*//*

        val root = inflater.inflate(R.layout.fragment_acaricides, container, false)

        // ---------------------------------
        myAcaricideViewModel = ViewModelProviders.of(this).get(AcaricideViewModel::class.java)

        // Deleting all data in the DB
        //myAcaricideViewModel.deleteAllAcaricide()

        myAcaricideViewModel.getAllAcaricides().observe(
            this, Observer { listOfAcaricides ->
                listOfAcaricides?.let {
                    acaricideAdapter.setAcaricides(it as MutableList<Acaricide>)
                }
            }
        )

        initialAcaricides.iterator().forEach {
            myAcaricideViewModel.insertAcaricide(it)
        }

        mRecyclerView = root.findViewById(R.id.acaricide_recyclerView)
        acaricideAdapter = AcaricideRecyclerViewAdapter(context!!)
        acaricideAdapter.setAcaricides(acaricides as MutableList<Acaricide>)
        mRecyclerView.adapter = acaricideAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        // ---------------------------------

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu, menu)

        val searchItem: MenuItem = menu.findItem(R.id.appSearchBar)
        val searchView: SearchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                acaricideAdapter.filter.filter(query)
                return false
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun replaceFragment(someFragment: Fragment) {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, someFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}*/
