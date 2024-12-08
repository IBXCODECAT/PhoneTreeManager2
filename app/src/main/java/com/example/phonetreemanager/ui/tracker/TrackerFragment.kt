package com.example.phonetreemanager.ui.tracker

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.phonetreemanager.systems.CallManager
import com.example.phonetreemanager.data.CallState
import com.example.phonetreemanager.databinding.FragmentTrackerBinding
import com.example.phonetreemanager.ui.call_info.CallInfoFragment

class TrackerFragment : Fragment() {

    private var _binding: FragmentTrackerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var selectedCall = -1

        _binding = FragmentTrackerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listView: ListView = binding.lstCalls

        val adapter = ArrayAdapter(requireContext(), R.layout.simple_list_item_1, CallManager.getCallNames())
        listView.adapter = adapter

        if(CallManager.getCallNames().isEmpty()) {
            adapter.add("There are currently no calls in the tracker.")
            adapter.add("Please select \"Incoming Call\" to get started!.")
            adapter.notifyDataSetChanged()
        }

        val buttons = binding.layoutCallButtons
        val callTitle = binding.txtCallTitle

        val parkButton = binding.btnParkCall
        val transferButton = binding.btnTransferCall
        val endButton = binding.btnEndCall

        endButton.setOnClickListener(View.OnClickListener {
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

        listView.setOnItemClickListener { _, _, position, _ ->
            try
            {
                selectedCall = position
                callTitle.text = CallManager.getCallAt(selectedCall).toString()

                buttons.visibility = View.VISIBLE
                callTitle.visibility = View.VISIBLE
                listView.visibility = View.GONE
            }
            catch (e: Exception)
            {
                Toast.makeText(this.context, e.message, Toast.LENGTH_LONG).show()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}