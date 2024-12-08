package com.example.phonetreemanager.ui.home

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.phonetreemanager.ui.call_info.CallInfoFragment
import com.example.phonetreemanager.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!

    var fragmentShowing = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listView: ListView = binding.lstHome

        // Add items to ListView
        val list = mutableListOf<String>()
        list.add("Home 1")
        list.add("Home 2")
        list.add("Home 3")

        val adapter = ArrayAdapter(requireContext(), R.layout.simple_list_item_1, list)
        listView.adapter = adapter

        val buttons = binding.layoutCallButtons

        val parkButton = binding.btnParkCall
        val transferButton = binding.btnTransferCall
        val endButton = binding.btnEndCall

        val callInfoFragment = CallInfoFragment()

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(binding.callInfoFragment.id, callInfoFragment)
        transaction.addToBackStack(null)
        transaction.hide(callInfoFragment)
        transaction.commit()

        endButton.setOnClickListener(View.OnClickListener {
            val hideTransaction = requireActivity().supportFragmentManager.beginTransaction()
            hideTransaction.hide(callInfoFragment)
            hideTransaction.commit()

            buttons.visibility = View.GONE
            listView.visibility = View.VISIBLE
            fragmentShowing = false
        })

        parkButton.setOnClickListener(View.OnClickListener {
            val hideTransaction = requireActivity().supportFragmentManager.beginTransaction()
            hideTransaction.hide(callInfoFragment)
            hideTransaction.commit()

            buttons.visibility = View.GONE
            listView.visibility = View.VISIBLE
            fragmentShowing = false
        })

        transferButton.setOnClickListener(View.OnClickListener {
            val hideTransaction = requireActivity().supportFragmentManager.beginTransaction()
            hideTransaction.hide(callInfoFragment)
            hideTransaction.commit()

            buttons.visibility = View.GONE
            listView.visibility = View.VISIBLE
            fragmentShowing = false
        })

        listView.setOnItemClickListener { parent, view, position, id ->

            if(!fragmentShowing) {

                val showTransaction= requireActivity().supportFragmentManager.beginTransaction()
                showTransaction.show(callInfoFragment)
                showTransaction.commit()

                buttons.visibility = View.VISIBLE
                listView.visibility = View.GONE
            }

            fragmentShowing = true
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}