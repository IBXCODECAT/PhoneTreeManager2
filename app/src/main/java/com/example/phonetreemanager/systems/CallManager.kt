package com.example.phonetreemanager.systems

import com.example.phonetreemanager.data.Call
import com.example.phonetreemanager.data.CallState

class CallManager {
    companion object {
        private val calls = mutableListOf<Call>()

        fun addCall(call: Call) {
            calls.add(call)
        }

        fun removeCallAt(index: Int) {
            calls.removeAt(index)
        }

        fun getCallAt(index: Int): Call {
            return calls[index]
        }

        fun getCallNames(): List<String> {

            // Create a custom string for each call in the list
            val map = calls.map { call ->
                "${call.time} - ${call.department} - ${call.state} ${call.extension?.let { "(${it})" } ?: ""}\n${call.description}"
            }

            map.sortedDescending()
            return map
        }

        fun setStateAt(index: Int, state: CallState) {
            calls[index].state = state
        }
    }
}