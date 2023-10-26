package com.example.reduxdemo.redux

import android.util.Log

fun loggerMiddleware(
    state: AppState,
    action: Action,
    dispatch: Dispatch,
    next: Next<AppState>,
): Action {
    Log.d("middleware", "action in <-- $action")
    val newAction = next(state, action, dispatch)
    Log.d("middleware", "action out --> $newAction")
    return newAction
}