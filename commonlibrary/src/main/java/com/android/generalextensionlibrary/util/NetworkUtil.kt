package com.android.generalextensionlibrary.util

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.telephony.TelephonyManager
import java.net.NetworkInterface
import java.net.SocketException

class NetworkUtil private constructor() {
    init {
        throw UnsupportedOperationException("NetworkUtils constructor not allowed to be created")
    }

    companion object {
        const val NETWORK_WIFI = 1 // wifi network
        const val NETWORK_4G = 4 // "4G" networks
        const val NETWORK_3G = 3 // "3G" networks
        const val NETWORK_2G = 2 // "2G" networks
        const val NETWORK_UNKNOWN = 5 // unknown network
        const val NETWORK_NO = -1 // no network
        private const val NETWORK_TYPE_GSM = 16
        private const val NETWORK_TYPE_TD_SCDMA = 17
        private const val NETWORK_TYPE_IWLAN = 18

        fun checkNetworkState(context: Context): Boolean { //得到网络信息
            val manager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = manager.activeNetworkInfo
            return if (info != null && info.isAvailable) true else false
        }


        /**
         * 获取活动网络信息
         * @param context上下文
         * @return NetworkInfo
         */
        private fun getActiveNetworkInfo(context: Context): NetworkInfo {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo
        }

        /**
         * 打开或关闭移动数据
         * @param context 上下文
         * @param enabled
         * `true`: 打开<br></br>
         * `false`: 关闭
         */
        fun setDataEnabled(context: Context, enabled: Boolean) {
            try {
                val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                val setMobileDataEnabledMethod =
                    tm.javaClass.getDeclaredMethod(
                        "setDataEnabled",
                        Boolean::class.javaPrimitiveType
                    )
                setMobileDataEnabledMethod?.invoke(tm, enabled)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        /**
         * 判断网络是否是4G
         * @param context 上下文
         * @return `true`: 是<br></br>
         * `false`: 否
         */
        fun is4G(context: Context): Boolean {
            val info = getActiveNetworkInfo(context)
            return info != null && info.isAvailable && info.subtype == TelephonyManager.NETWORK_TYPE_LTE
        }

        /**
         * 判断wifi是否打开
         * @param context 上下文
         * @return `true`: 是<br></br>
         * `false`: 否
         */
        fun isWifiEnabled(context: Context): Boolean {
            val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
            return wifiManager.isWifiEnabled
        }

        /**
         * 打开或关闭wifi
         *
         * @param context
         * 上下文
         * @param enabled
         * `true`: 打开<br></br>
         * `false`: 关闭
         */
        fun setWifiEnabled(context: Context, enabled: Boolean) {
            val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
            if (enabled) {
                if (!wifiManager.isWifiEnabled) {
                    wifiManager.setWifiEnabled(true);
                }
            } else {
                if (wifiManager.isWifiEnabled) {
                    wifiManager.setWifiEnabled(false);
                }
            }
        }

        /**
         * 判断wifi是否连接状态
         * @param context
         * 上下文
         * @return `true`: 连接<br></br>
         * `false`: 未连接
         */
        fun isWifiConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm != null && cm.activeNetworkInfo != null && cm.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
        }

        /**
         * 获取网络运营商名称: 中国移动、如中国联通、中国电信
         * @param context 上下文
         * @return 运营商名称
         */
        fun getNetworkOperatorName(context: Context): String? {
            val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return tm?.networkOperatorName
        }

        /**
         * 获取当前的网络类型(WIFI,2G,3G,4G)
         * @param context 上下文
         * @return 网络类型
         *
         *  * [.NETWORK_WIFI] = 1;
         *  * [.NETWORK_4G] = 4;
         *  * [.NETWORK_3G] = 3;
         *  * [.NETWORK_2G] = 2;
         *  * [.NETWORK_UNKNOWN] = 5;
         *  * [.NETWORK_NO] = -1;
         *
         */
        fun getNetworkType(context: Context): Int {
            var netType = NETWORK_NO
            val info = getActiveNetworkInfo(context)
            if (info != null && info.isAvailable) {
                netType = if (info.type == ConnectivityManager.TYPE_WIFI) {
                    NETWORK_WIFI
                } else if (info.type == ConnectivityManager.TYPE_MOBILE) {
                    when (info.subtype) {
                        NETWORK_TYPE_GSM, TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN -> NETWORK_2G
                        NETWORK_TYPE_TD_SCDMA, TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP -> NETWORK_3G
                        NETWORK_TYPE_IWLAN, TelephonyManager.NETWORK_TYPE_LTE -> NETWORK_4G
                        else -> {
                            val subtypeName = info.subtypeName
                            if (subtypeName.equals(
                                    "TD-SCDMA",
                                    ignoreCase = true
                                ) || subtypeName.equals("WCDMA", ignoreCase = true)
                                || subtypeName.equals("CDMA2000", ignoreCase = true)
                            ) {
                                NETWORK_3G
                            } else {
                                NETWORK_UNKNOWN
                            }
                        }
                    }
                } else {
                    NETWORK_UNKNOWN
                }
            }
            return netType
        }

        /**
         * 获取当前的网络类型(WIFI,2G,3G,4G)
         * @param context
         * 上下文
         * @return 网络类型名称
         *
         *  * NETWORK_WIFI
         *  * NETWORK_4G
         *  * NETWORK_3G
         *  * NETWORK_2G
         *  * NETWORK_UNKNOWN
         *  * NETWORK_NO
         *
         */
        fun getNetworkTypeName(context: Context): String {
            return when (getNetworkType(context)) {
                NETWORK_WIFI -> "NETWORK_WIFI"
                NETWORK_4G -> "NETWORK_4G"
                NETWORK_3G -> "NETWORK_3G"
                NETWORK_2G -> "NETWORK_2G"
                NETWORK_NO -> "NETWORK_NO"
                else -> "NETWORK_UNKNOWN"
            }
        }

        /**
         * 检查当前网络是否可用
         *
         * @param context
         * @return
         */
        fun isNetworkAvailable(context: Context): Boolean { // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager == null) {
                return false
            } else { // 获取NetworkInfo对象
                val networkInfo = connectivityManager.allNetworkInfo
                if (networkInfo != null && networkInfo.size > 0) {
                    for (i in networkInfo.indices) { // 判断当前网络状态是否为连接状态
                        if (networkInfo[i].state == NetworkInfo.State.CONNECTED) {
                            return true
                        }
                    }
                }
            }
            return false
        }

        /**
         * 判断网络是否连接
         */
        fun isConnected(context: Context): Boolean {
            val connectivity =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (null != connectivity) {
                val info = connectivity.activeNetworkInfo
                if (null != info && info.isConnected) {
                    if (info.state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
            }
            return false
        }

        /**
         * 判断是否是wifi连接
         */
        fun isWifi(context: Context): Boolean {
            val connectivity =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    ?: return false
            return connectivity.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
        }

        /**
         * open setting
         */
        /* fun openSetting(activity: Activity) {
             val intent = Intent("/")
             val componentName = ComponentName("com.android.settings", "com.android.settings.WirelessSettings")
             intent.component = componentName
             intent.action = "android.intent.action.VIEW"
             activity.startActivityForResult(intent, 0)
         }*/
    }

}