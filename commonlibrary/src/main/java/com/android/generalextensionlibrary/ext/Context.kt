
package com.android.generalextensionlibrary.ext

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent

/**
 * Starts the Activity [A], in a more concise way, while still allowing to configure the [Intent] in
 * the optional [configIntent] lambda.
 */
inline fun <reified A : Activity> Context.start(configIntent: Intent.() -> Unit = {}) {
    startActivity(Intent(this, A::class.java).apply(configIntent))
}

inline fun <reified A : Activity> Activity.startActivityForResult(requestCode: Int,configIntent: Intent.() -> Unit = {}) {
    startActivityForResult(Intent(this, A::class.java).apply(configIntent),requestCode)
}

/**
 * Starts an Activity that supports the passed [action], in a more concise way,
 * while still allowing to configure the [Intent] in the optional [configIntent] lambda.
 *
 * If there's no matching [Activity], the underlying platform API will throw an
 * [ActivityNotFoundException].
 *
 * If there is more than one matching [Activity], the Android system may show an activity chooser to
 * the user.
 */
@Throws(ActivityNotFoundException::class)
inline fun Context.startActivity(action: String, configIntent: Intent.() -> Unit = {}) {
    startActivity(Intent(action).apply(configIntent))
}


/**
 * dp--px
 */
fun Context.dp2px(dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun Context.px2dp(px: Int): Int {
    val scale = resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}

/**
 * The absolute width/height of the available display size in pixels
 */
val Context.screenWidth
    get() = resources.displayMetrics.widthPixels

val Context.screenHeight
    get() = resources.displayMetrics.heightPixels