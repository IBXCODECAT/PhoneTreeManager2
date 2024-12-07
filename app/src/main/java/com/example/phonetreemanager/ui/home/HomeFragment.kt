package com.example.phonetreemanager.ui.home

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.example.phonetreemanager.CallInfoFragment
import com.example.phonetreemanager.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!

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

        // Launch a new fragment
        listView.setOnItemClickListener { parent, view, position, id ->
            val fragmentManager = requireActivity().supportFragmentManager

            fragmentManager.commit {
                setCustomAnimations(R.animator.fade_in, R.animator.fade_out)
                replace<CallInfoFragment>(container?.id ?: 0) // Use the correct ID
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }



        val adapter = ArrayAdapter(requireContext(), R.layout.simple_list_item_1, list)
        listView.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}