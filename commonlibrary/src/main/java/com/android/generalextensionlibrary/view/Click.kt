
@file:Suppress("NOTHING_TO_INLINE")

package com.android.generalextensionlibrary.view

import android.view.View

/**
 * Registers the [block] lambda as [View.OnClickListener] to this View.
 *
 * If this View is not clickable, it becomes clickable.
 */
inline fun View.click(crossinline block: () -> Unit) = setOnClickListener { block() }

/**
 * Register the [block] lambda as [View.OnLongClickListener] to this View.
 * By default, [consume] is set to true because it's the most common use case, but you can set it
 * to false.
 * If you want to return a value dynamically, use [View.setOnLongClickListener] instead.
 *
 * If this view is not long clickable, it becomes long clickable.
 */
inline fun View.longClick(
    consume: Boolean = true,
    crossinline block: () -> Unit
) = setOnLongClickListener { block(); consume }
