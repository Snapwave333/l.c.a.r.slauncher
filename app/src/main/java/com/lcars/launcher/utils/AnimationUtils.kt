package com.lcars.launcher.utils

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.ui.unit.IntOffset

/**
 * Animation utilities for LCARS transitions
 */
object AnimationUtils {

    /**
     * Default animation duration
     */
    const val DEFAULT_DURATION = 300

    /**
     * LCARS wipe transition (left to right)
     */
    fun lcarsWipeTransition(
        duration: Int = DEFAULT_DURATION
    ): EnterTransition {
        return slideInHorizontally(
            initialOffsetX = { fullWidth -> -fullWidth },
            animationSpec = tween(duration, easing = FastOutSlowInEasing)
        ) + fadeIn(animationSpec = tween(duration))
    }

    /**
     * LCARS wipe exit transition (left to right)
     */
    fun lcarsWipeExitTransition(
        duration: Int = DEFAULT_DURATION
    ): ExitTransition {
        return slideOutHorizontally(
            targetOffsetX = { fullWidth -> fullWidth },
            animationSpec = tween(duration, easing = FastOutSlowInEasing)
        ) + fadeOut(animationSpec = tween(duration))
    }

    /**
     * Panel slide in from bottom
     */
    fun panelSlideIn(
        duration: Int = DEFAULT_DURATION
    ): EnterTransition {
        return slideInVertically(
            initialOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(duration, easing = EaseOutCubic)
        ) + fadeIn(animationSpec = tween(duration))
    }

    /**
     * Panel slide out to bottom
     */
    fun panelSlideOut(
        duration: Int = DEFAULT_DURATION
    ): ExitTransition {
        return slideOutVertically(
            targetOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(duration, easing = EaseInCubic)
        ) + fadeOut(animationSpec = tween(duration))
    }

    /**
     * Alert pulse animation (for Red Alert mode)
     */
    fun alertPulseAnimation(): InfiniteRepeatableSpec<Float> {
        return infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    }

    /**
     * Accent pulse animation
     */
    fun accentPulseAnimation(): InfiniteRepeatableSpec<Float> {
        return infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    }

    /**
     * Color modulation animation
     */
    fun colorModulationAnimation(
        durationMillis: Int = 3000
    ): InfiniteRepeatableSpec<Float> {
        return infiniteRepeatable(
            animation = tween(durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    }

    /**
     * Shimmer effect animation
     */
    fun shimmerAnimation(): InfiniteRepeatableSpec<Float> {
        return infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    }

    /**
     * Bounce animation for interactive elements
     */
    fun bounceAnimation(): FiniteAnimationSpec<Float> {
        return spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    }

    /**
     * Scale animation for buttons
     */
    fun buttonPressAnimation(): FiniteAnimationSpec<Float> {
        return tween(
            durationMillis = 100,
            easing = FastOutSlowInEasing
        )
    }
}

/**
 * Custom easing curves
 */
val EaseOutCubic = CubicBezierEasing(0.215f, 0.610f, 0.355f, 1.0f)
val EaseInCubic = CubicBezierEasing(0.55f, 0.055f, 0.675f, 0.19f)
