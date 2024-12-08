package com.example.phonetreemanager

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
            return calls.map { it.name }
        }

        fun setStateAt(index: Int, state: CallState) {
            calls[index].state = state
        }
    }
}