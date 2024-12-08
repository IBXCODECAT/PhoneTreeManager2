package com.example.phonetreemanager

enum class CallState {
    CREATED,
    PARKED,
    TRANSFERRED
}

class Call(
    val name: String,
    val department: String,
    var state: CallState,
    val extension: Int?
)
