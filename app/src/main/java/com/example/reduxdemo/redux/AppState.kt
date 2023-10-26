package com.example.reduxdemo.redux

data class AppState(
    val counterState: CounterState = CounterState()
)

data class CounterState(val count: Int = 0)

class CounterAppStore : AppStore<AppState>(
    initialState = AppState(),
    reducers = listOf(::reduceCounterState),
    middleware = listOf(::loggerMiddleware)
) {
    companion object {
        val instance by lazy {
            CounterAppStore()
        }
    }

}