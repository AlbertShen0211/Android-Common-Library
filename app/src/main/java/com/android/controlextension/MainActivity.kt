package com.android.controlextension

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.android.generalextensionlibrary.ext.click
import com.android.generalextensionlibrary.ext.longToast
import com.android.generalextensionlibrary.ext.start
import com.android.generalextensionlibrary.ext.toast
import com.android.generalextensionlibrary.util.*
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import timber.log.Timber
import java.io.File
import java.util.*

private const val RC_WRITE_PERM = 10
class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    val path= Environment.getExternalStorageDirectory().getPath()+"/test/app-release.apk"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val b = false;

        button1.click {
            if (b)
                toast("hello")
            else
                longToast("world")

        }

        button2.click {
            start<HelloActivity>()
        }

        button3.click {
            val mobileSimple = isMobile("18011111111")
            val idCard = isIDCard("33132319810211581X")
            toast("idCard is " + idCard)
        }

        var spValue by SharePreferenceUtil("key", "DefaultValue")
        button4.click {
            toast("spValue is " + spValue)
            spValue = "value"
            toast("spValue2 is  " + spValue)
        }
        button5.click {
            Timber.e("currentTimeMills-> " + currentTimeMills)
            Timber.e(" currentTimeString()-> " + currentTimeString())
            Timber.e(" currentDate-> " + currentDate)
            Timber.e(" format2String-> " + Date().format2String("yyyy-MM-dd HH:mm:ss"))
            Timber.e(" format2String-> " + 11111111111111.format2DateString())

        }

        button7.click {
            installApp(this, path)
        }

    }

    @AfterPermissionGranted(RC_WRITE_PERM)
    private fun installApp(context: Context, filePath: String) {
        if (hasStoragePermission()){
            val haveInstallPermission: Boolean
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                haveInstallPermission = packageManager.canRequestPackageInstalls()
                if (!haveInstallPermission) {
                        installPermissionSettingIntent()
                }
                else{
                    installIntent(context, filePath)
                }
            }
            else{
                installIntent(context, filePath)
            }


        }
        else{
            Toast.makeText(this,"No permission",Toast.LENGTH_LONG).show()
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.rationale_store),
              RC_WRITE_PERM,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
    }

    private fun installIntent(context: Context, filePath: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        // setFlags() must be placed before addFlags(), otherwise it will be overwritten
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        PermissionUtil.setIntentDataAndType(
            context,
            intent, "application/vnd.android.package-archive", File(filePath), true
        )
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }

    /**
     * 开启安装未知来源权限
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun installPermissionSettingIntent() {
        val packageURI: Uri = Uri.parse("package:$packageName")
        val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI)
        startActivityForResult(intent, 11)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 11) {
            Toast.makeText(this, "安装应用", Toast.LENGTH_SHORT).show()
            installIntent(this, path)
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        installApp(this, path)
    }

    private fun hasStoragePermission(): Boolean {
        return EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

}
