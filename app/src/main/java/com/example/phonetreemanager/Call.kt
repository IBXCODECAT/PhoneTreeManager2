package com.example.phonetreemanager

enum class CallState {
    CREATED,
    PARKED,
    TRANSFERRED,
    ENDED
}

class Call(
    val name: String,
    var state: CallState,
    val extension: Number
)
