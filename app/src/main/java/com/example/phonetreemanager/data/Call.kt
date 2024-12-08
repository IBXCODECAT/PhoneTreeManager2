package com.example.phonetreemanager.data

enum class CallState {
    CREATED,
    PARKED,
    TRANSFERRED
}

data class Call(
    val time: String,
    val department: String,
    var state: CallState,
    val extension: Int?,
    var description: String?,
) {
    override fun toString(): String {
        return "$time - ${extension?.let { "(${it})" } ?: ""} - [$state] \n${description}"
    }
}
