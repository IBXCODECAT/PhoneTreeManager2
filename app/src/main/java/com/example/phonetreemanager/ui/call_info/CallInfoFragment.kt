package com.example.phonetreemanager.ui.call_info

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.phonetreemanager.databinding.FragmentCallinfoBinding
import com.example.phonetreemanager.ui.home.HomeViewModel

class CallInfoFragment : Fragment() {

    private var _binding: FragmentCallinfoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val callViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentCallinfoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val btnSubmit: Button = binding.btnSubmit;

        btnSubmit.setOnClickListener(View.OnClickListener {
            val manager: FragmentManager = requireActivity().supportFragmentManager
            manager.popBackStack()
            
            // Toast notification
            Toast.makeText(this.context, "Phone Calls Updated!", Toast.LENGTH_SHORT).show()
        })

        return root
    }
}