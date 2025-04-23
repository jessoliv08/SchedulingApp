package com.example.schedulingapp

import kotlinx.coroutines.flow.StateFlow

interface ButtonViewModel {
    fun action()
    val state: StateFlow<State?>

    data class State(
        val title: String,
        val enable: Boolean
    )
}