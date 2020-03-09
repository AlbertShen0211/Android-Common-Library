package com.android.generalextensionlibrary.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import java.io.File


/**
 * Created by Albert on 2020/3/5.
 * Description:
 */
object PermissionUtil {
    fun getUriForFile(context: Context, file: File?): Uri? {
        var fileUri: Uri? = null
        fileUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            getUriForFile24(context, file)
        } else {
            Uri.fromFile(file)
        }
        return fileUri
    }

    fun getUriForFile24(context: Context, file: File?): Uri {
        return FileProvider.getUriForFile(context, context.packageName + ".android7.fileprovider", file!!)
    }

    fun setIntentDataAndType(
        context: Context,
        intent: Intent,
        type: String?,
        file: File?,
        writeAble: Boolean
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setDataAndType(getUriForFile(context, file), type)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            if (writeAble) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            }
        } else {
            intent.setDataAndType(Uri.fromFile(file), type)
        }
    }


}