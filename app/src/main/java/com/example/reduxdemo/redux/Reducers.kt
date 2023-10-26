package com.example.reduxdemo.redux

fun reduceCounterState(state: AppState, action: Action): AppState {
    return when (action) {
        is Increment -> state.copy(
            counterState = CounterState(
                state.counterState.count.inc()
            )
        )

        is Decrement -> state.copy(
            counterState = CounterState(
                state.counterState.count.dec()
            )
        )

        else -> state
    }
}