package com.dustanmbaga.pps.ui.acaricides

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.dustanmbaga.pps.R
import com.dustanmbaga.pps.ui.acaricides.details.AcaricideDetailsFragment


class AcaricidesFragment : Fragment() {

    private lateinit var acaricidesViewModel: AcaricidesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        acaricidesViewModel =
            ViewModelProviders.of(this).get(AcaricidesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_acaricides, container, false)

        val acaricideItem: LinearLayout = root.findViewById(R.id.acaricide_item)
        acaricideItem.setOnClickListener {
            replaceFragment(AcaricideDetailsFragment())
        }

        return root
    }

    private fun replaceFragment(someFragment: Fragment) {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, someFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}