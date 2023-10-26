package com.example.reduxdemo.ui

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.reduxdemo.redux.AppState
import com.example.reduxdemo.redux.CounterAppStore
import com.example.reduxdemo.redux.Decrement
import com.example.reduxdemo.redux.Dispatch
import com.example.reduxdemo.redux.Increment
import com.example.reduxdemo.redux.Unsubscribe

@Composable
fun CounterScreen() {
    var dispatch: Dispatch? = null
    val context = LocalContext.current
    val isLoading = remember {
        mutableStateOf(false)
    }
    var number by remember { mutableStateOf(0) }


    DisposableEffect(Unit) {
        val unsubscribe: Unsubscribe =
            CounterAppStore.instance.subscribe { state: AppState, lDispatch: Dispatch ->
                dispatch = lDispatch
                number = state.counterState.count
            }
        onDispose {
            unsubscribe.invoke()
            dispatch = null
            Log.d("DisposableEffect", "disposed: $unsubscribe")
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
//        if (isLoading.value) {
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier
//                    .wrapContentSize()
//                    .background(Color.White)
//            ) {
//                CircularProgressIndicator()
//            }
//        }

        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp, alignment = Alignment.CenterVertically
            ), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Current number: ${number}")
            Button(onClick = { dispatch?.invoke(Increment) }) {
                Text("Increment")
            }
            Button(onClick = { dispatch?.invoke(Decrement) }) {
                Text("Decrement")
            }
        }

    }


}