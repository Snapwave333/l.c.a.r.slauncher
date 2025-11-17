package com.lcars.launcher.services

import android.view.GestureDetector
import android.view.MotionEvent
import androidx.compose.ui.geometry.Offset
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.abs

/**
 * Service for detecting swipe gestures and double-taps
 */
@Singleton
class GestureDetectionService @Inject constructor() {

    private val _gestureEvents = MutableStateFlow<GestureEvent?>(null)
    val gestureEvents: StateFlow<GestureEvent?> = _gestureEvents.asStateFlow()

    private var initialTouch: Offset? = null
    private var lastTapTime: Long = 0
    private val doubleTapTimeout = 300L

    companion object {
        private const val SWIPE_THRESHOLD = 100f
        private const val SWIPE_VELOCITY_THRESHOLD = 100f
    }

    /**
     * Process touch events to detect gestures
     */
    fun processTouchEvent(
        event: MotionEvent,
        onSwipeUp: (() -> Unit)? = null,
        onSwipeDown: (() -> Unit)? = null,
        onSwipeLeft: (() -> Unit)? = null,
        onSwipeRight: (() -> Unit)? = null,
        onDoubleTap: (() -> Unit)? = null,
        onLongPress: (() -> Unit)? = null
    ): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                initialTouch = Offset(event.x, event.y)

                // Check for double tap
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastTapTime < doubleTapTimeout) {
                    onDoubleTap?.invoke()
                    _gestureEvents.value = GestureEvent.DoubleTap
                }
                lastTapTime = currentTime

                return true
            }
            MotionEvent.ACTION_UP -> {
                initialTouch?.let { start ->
                    val end = Offset(event.x, event.y)
                    val deltaX = end.x - start.x
                    val deltaY = end.y - start.y

                    // Detect swipe direction
                    if (abs(deltaX) > abs(deltaY)) {
                        // Horizontal swipe
                        if (abs(deltaX) > SWIPE_THRESHOLD) {
                            if (deltaX > 0) {
                                onSwipeRight?.invoke()
                                _gestureEvents.value = GestureEvent.SwipeRight
                            } else {
                                onSwipeLeft?.invoke()
                                _gestureEvents.value = GestureEvent.SwipeLeft
                            }
                        }
                    } else {
                        // Vertical swipe
                        if (abs(deltaY) > SWIPE_THRESHOLD) {
                            if (deltaY > 0) {
                                onSwipeDown?.invoke()
                                _gestureEvents.value = GestureEvent.SwipeDown
                            } else {
                                onSwipeUp?.invoke()
                                _gestureEvents.value = GestureEvent.SwipeUp
                            }
                        }
                    }
                }
                initialTouch = null
                return true
            }
        }
        return false
    }

    /**
     * Detect swipe from offsets
     */
    fun detectSwipe(start: Offset, end: Offset): GestureEvent? {
        val deltaX = end.x - start.x
        val deltaY = end.y - start.y

        return when {
            abs(deltaX) > abs(deltaY) && abs(deltaX) > SWIPE_THRESHOLD -> {
                if (deltaX > 0) GestureEvent.SwipeRight else GestureEvent.SwipeLeft
            }
            abs(deltaY) > SWIPE_THRESHOLD -> {
                if (deltaY > 0) GestureEvent.SwipeDown else GestureEvent.SwipeUp
            }
            else -> null
        }
    }

    fun clearEvent() {
        _gestureEvents.value = null
    }
}

sealed class GestureEvent {
    object SwipeUp : GestureEvent()
    object SwipeDown : GestureEvent()
    object SwipeLeft : GestureEvent()
    object SwipeRight : GestureEvent()
    object DoubleTap : GestureEvent()
    object LongPress : GestureEvent()
}
