package com.janteadebowale.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


@Composable
fun <T> ConsumeNavEvents(
    eventFlow: Flow<T>,
    lifecycleOwner: LifecycleOwner,
    onEvent: (T) -> Unit
) {
    LaunchedEffect(eventFlow, lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                eventFlow.collect(onEvent)
            }
        }
    }
}