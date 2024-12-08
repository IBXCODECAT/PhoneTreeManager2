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
import com.example.phonetreemanager.CallManager
import com.example.phonetreemanager.CallState
import com.example.phonetreemanager.ui.call_info.CallInfoFragment
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

        var selectedCall = -1

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listView: ListView = binding.lstHome

        val adapter = ArrayAdapter(requireContext(), R.layout.simple_list_item_1, CallManager.getCallNames())
        listView.adapter = adapter

        val buttons = binding.layoutCallButtons
        val callTitle = binding.txtCallTitle

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

            // Remove the selected call from the list
            CallManager.removeCallAt(selectedCall)

            // Refresh the list view
            adapter.clear()
            adapter.addAll(CallManager.getCallNames())
            adapter.notifyDataSetChanged()

            buttons.visibility = View.GONE
            callTitle.visibility = View.GONE
            listView.visibility = View.VISIBLE
        })

        parkButton.setOnClickListener(View.OnClickListener {
            val hideTransaction = requireActivity().supportFragmentManager.beginTransaction()
            hideTransaction.hide(callInfoFragment)
            hideTransaction.commit()

            // Update the selected call to show it has been parked
            CallManager.setStateAt(selectedCall, CallState.PARKED)

            // Refresh the list view
            adapter.clear()
            adapter.addAll(CallManager.getCallNames())
            adapter.notifyDataSetChanged()
            
            buttons.visibility = View.GONE
            callTitle.visibility = View.GONE
            listView.visibility = View.VISIBLE
        })

        transferButton.setOnClickListener(View.OnClickListener {
            val hideTransaction = requireActivity().supportFragmentManager.beginTransaction()
            hideTransaction.hide(callInfoFragment)
            hideTransaction.commit()

            // Update the selected call to show it has been transferred
            CallManager.setStateAt(selectedCall, CallState.TRANSFERRED)


            // Refresh the list view
            adapter.clear()
            adapter.addAll(CallManager.getCallNames())
            adapter.notifyDataSetChanged()

            buttons.visibility = View.GONE
            callTitle.visibility = View.GONE
            listView.visibility = View.VISIBLE
        })

        listView.setOnItemClickListener { parent, view, position, id ->
            val showTransaction= requireActivity().supportFragmentManager.beginTransaction()
            showTransaction.show(callInfoFragment)
            showTransaction.commit()

            selectedCall = position

            buttons.visibility = View.VISIBLE

            callTitle.text = CallManager.getCallAt(selectedCall).name

            callTitle.visibility = View.VISIBLE
            listView.visibility = View.GONE
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}