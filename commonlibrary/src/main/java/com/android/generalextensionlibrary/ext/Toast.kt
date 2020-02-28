@file:Suppress("NOTHING_TO_INLINE")

package com.android.generalextensionlibrary.ext

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.os.Build.VERSION.SDK_INT
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import kotlin.LazyThreadSafetyMode.NONE

inline fun Context.txt(@StringRes stringResId: Int): CharSequence = resources.getText(stringResId)
inline val Context.layoutInflater: LayoutInflater get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
inline val Context.windowManager get() = getSystemService(Context.WINDOW_SERVICE) as WindowManager

@PublishedApi
internal fun Context.createToast(text: CharSequence, duration: Int): Toast {
    val ctx = if (SDK_INT == 25) SafeToastCtx(
        this
    ) else this
    return Toast.makeText(ctx, text, duration)
}

@PublishedApi
internal fun Context.createToast(@StringRes resId: Int, duration: Int): Toast {
    return createToast(txt(resId), duration)
}

inline fun Context.toast(
    @StringRes msgResId: Int
) = createToast(msgResId, Toast.LENGTH_SHORT).show()

inline fun Fragment.toast(@StringRes msgResId: Int) = ctx.toast(msgResId)
inline fun View.toast(@StringRes msgResId: Int) = context.toast(msgResId)
inline fun toast(@StringRes msgResId: Int) = appCtx.toast(msgResId)

inline fun Context.toast(msg: CharSequence) = createToast(msg, Toast.LENGTH_SHORT).show()
inline fun Fragment.toast(msg: CharSequence) = ctx.toast(msg)
inline fun View.toast(msg: CharSequence) = context.toast(msg)
inline fun toast(msg: CharSequence) = appCtx.toast(msg)

inline fun Context.longToast(
    @StringRes msgResId: Int
) = createToast(msgResId, Toast.LENGTH_LONG).show()

inline fun Fragment.longToast(@StringRes msgResId: Int) = ctx.longToast(msgResId)
inline fun View.longToast(@StringRes msgResId: Int) = context.longToast(msgResId)
inline fun longToast(@StringRes msgResId: Int) = appCtx.longToast(msgResId)

inline fun Context.longToast(msg: CharSequence) = createToast(msg, Toast.LENGTH_LONG).show()
inline fun Fragment.longToast(msg: CharSequence) = ctx.longToast(msg)
inline fun View.longToast(msg: CharSequence) = context.longToast(msg)
inline fun longToast(msg: CharSequence) = appCtx.longToast(msg)

@PublishedApi
internal inline val Fragment.ctx: Context
    get() = context ?: appCtx

/**
 * Avoids [WindowManager.BadTokenException] on API 25.
 */
private class SafeToastCtx(ctx: Context) : ContextWrapper(ctx) {
    private val toastWindowManager by lazy(NONE) {
        ToastWindowManager(
            com.android.generalextensionlibrary.ext.systemservices.windowManager
        )
    }
    private val toastLayoutInflater by lazy(NONE) {
       layoutInflater.cloneInContext(this)
    }

    override fun getApplicationContext(): Context =
        SafeToastCtx(baseContext.applicationContext)
    override fun getSystemService(name: String): Any = when (name) {
        Context.LAYOUT_INFLATER_SERVICE -> toastLayoutInflater
        Context.WINDOW_SERVICE -> toastWindowManager
        else -> super.getSystemService(name)
    }

    private class ToastWindowManager(private val base: WindowManager) : WindowManager by base {

        @SuppressLint("LogNotTimber") // Timber is not a dependency here, but lint passes through.
        override fun addView(view: View?, params: ViewGroup.LayoutParams?) {
            try {
                base.addView(view, params)
            } catch (e: WindowManager.BadTokenException) {
                Log.e("SafeToast", "Couldn't add Toast to WindowManager", e)
            }
        }
    }
}
