package com.android.generalextensionlibrary.ext

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.android.generalextensionlibrary.ext.systemservices.inputMethodManager

fun Context.showSoftInput(view: View) {
    view.isFocusable = true
    view.isFocusableInTouchMode = true
    view.requestFocus()
    inputMethodManager?.showSoftInput(view, InputMethodManager.SHOW_FORCED)
}

fun Context.hideSoftInput(view: View) {
    inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.hideSoftInput(activity: Activity) {
    val view: View = activity.currentFocus ?: activity.window.decorView
    inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.isSoftInputActive(): Boolean {
    return inputMethodManager?.isActive ?: false
}

/*
  ---------- Fragment ----------
 */

fun Fragment.showSoftInput(view: View) {
    activity?.showSoftInput(view)
}

fun Fragment.hideSoftInput(view: View) {
    activity?.hideSoftInput(view)
}

fun Fragment.hideSoftInput() {
    activity?.hideSoftInput(activity!!)
}

fun Fragment.isSoftInputActive() {
    activity?.isSoftInputActive()
}

fun androidx.fragment.app.Fragment.showSoftInput(view: View) {
    activity?.showSoftInput(view)

}

fun androidx.fragment.app.Fragment.hideSoftInput(view: View) {
    activity?.hideSoftInput(view)
}

fun androidx.fragment.app.Fragment.hideSoftInput() {
    activity?.hideSoftInput(activity!!)
}

fun androidx.fragment.app.Fragment.isSoftInputActive() {
    activity?.isSoftInputActive()
}

/*
  ---------- View ----------
 */
fun View.showSoftInput(view: View) {
    context.showSoftInput(view)
}

fun View.hideSoftInput(view: View) {
    context.hideSoftInput(view)
}

fun View.isSoftInputActive() {
    context.isSoftInputActive()
}