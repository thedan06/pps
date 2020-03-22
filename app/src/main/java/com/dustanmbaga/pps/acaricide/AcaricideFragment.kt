package com.dustanmbaga.pps.acaricide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dustanmbaga.pps.R


class AcaricideFragment : Fragment() {

    private lateinit var acaricideViewModel: AcaricideViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_acaricides, container, false)

        //-------------------------

        val recyclerView = root.findViewById<RecyclerView>(R.id.acaricide_recyclerView)
        val adapter = AcaricideListAdapter(context!!)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context!!)

        acaricideViewModel = ViewModelProvider(this).get(AcaricideViewModel::class.java)
        acaricideViewModel.acaricides.observe(viewLifecycleOwner, Observer { acaricides ->
            // Update the cached copy of the words in the adapter.
            acaricides?.let { adapter.setAcaricides(it) }
        })

        //-------------------------
        /*val rootLayout: ConstraintLayout = root.findViewById(R.id.acaricideLayout)
        val commonName: TextView = root.findViewById(R.id.title)
        //val taps: TextView = findViewById(R.id.taps)
        val spinner: ProgressBar = root.findViewById(R.id.spinner)

        // Get MainViewModel by passing a database to the factory
        val database = getDatabase(context!!)
        val repository = AcaricideRepository(getNetworkService(), database.acaricideDao)

        val viewModel = ViewModelProviders
            .of(this, AcaricideViewModel.FACTORY(repository))
            .get(AcaricideViewModel::class.java)

        // When rootLayout is clicked call onMainViewClicked in ViewModel
        rootLayout.setOnClickListener {
            viewModel.onMainViewClicked()
        }

        // update the title when the [MainViewModel.title] changes
        // Observe product data

        // Observe product data
        // Update the list of products when the underlying data changes.

        // Update the list of Acaricides when the underlying data changes.
        viewModel.acaricides.observe(
            viewLifecycleOwner,
            Observer<List<Acaricide>?> { myAcaricides ->
                if (myAcaricides != null) {
                    mBinding.setIsLoading(false)
                    mProductAdapter.setProductList(myAcaricides)
                } else {
                    mBinding.setIsLoading(true)
                }
            })*/


        /*viewModel.taps.observe(this) { value ->
            taps.text = value
        }

        // show the spinner when [MainViewModel.spinner] is true
        viewModel.spinner.observe(this) { value ->
            value.let { show ->
                spinner.visibility = if (show) View.VISIBLE else View.GONE
            }
        }

        // Show a snackbar whenever the [ViewModel.snackbar] is updated a non-null value
        viewModel.snackbar.observe(this) { text ->
            text?.let {
                Snackbar.make(rootLayout, text, Snackbar.LENGTH_SHORT).show()
                viewModel.onSnackbarShown()
            }
        }*/
        //-------------------------
        return root
    }
}