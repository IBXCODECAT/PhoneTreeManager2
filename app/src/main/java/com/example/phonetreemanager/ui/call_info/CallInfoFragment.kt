package com.example.phonetreemanager.ui.call_info

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.phonetreemanager.data.Call
import com.example.phonetreemanager.systems.CallManager
import com.example.phonetreemanager.data.CallState
import com.example.phonetreemanager.databinding.FragmentCallinfoBinding
import com.example.phonetreemanager.ui.tracker.TrackerViewModel
import java.time.LocalTime


class CallInfoFragment : Fragment() {

    private var _binding: FragmentCallinfoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this).get(TrackerViewModel::class.java)

        _binding = FragmentCallinfoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val btnSubmit: Button = binding.btnSubmit;

        btnSubmit.setOnClickListener(View.OnClickListener {

            val edtExtension = binding.edtCallExtension
            val edtDescription = binding.edtCallDescription
            val spnDepartment = binding.spnCallDepartment

            if(spnDepartment.selectedItemPosition == 0) {
                Toast.makeText(this.context, "Department is required", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val call = Call(
                    time = LocalTime.now().toString().substring(0,5),
                    state = CallState.CREATED,
                    department = spnDepartment.selectedItem.toString(),
                    extension = edtExtension.text.toString().toIntOrNull(),
                    description = edtDescription.text.toString()
                )

                CallManager.addCall(call)

                // Toast notification
                Toast.makeText(this.context, "Phone Calls Updated!", Toast.LENGTH_SHORT).show()

                val manager: FragmentManager = requireActivity().supportFragmentManager
                manager.popBackStack()
            }
        })

        return root
    }
}