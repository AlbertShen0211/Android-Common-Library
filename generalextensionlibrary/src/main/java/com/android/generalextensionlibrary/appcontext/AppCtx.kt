package com.android.generalextensionlibrary.appcontext

import android.annotation.SuppressLint
import android.content.Context

val appCtx: Context get() = internalCtx ?: initAndGetAppCtxWithReflection()

@SuppressLint("StaticFieldLeak")
private var internalCtx: Context? = null


/**
 * This method will return true on [Context] implementations known to be able to leak memory.
 * This includes [Activity], [Service], the lesser used and lesser known [BackupAgent], as well as
 * any [ContextWrapper] having one of these as its base context.
 */


@SuppressLint("PrivateApi")
private fun initAndGetAppCtxWithReflection(): Context {
    // Fallback, should only run once per non default process.
    val activityThread = Class.forName("android.app.ActivityThread")
    val ctx = activityThread.getDeclaredMethod("currentApplication").invoke(null) as Context
    internalCtx = ctx
    return ctx
}
