package com.android.controlextension

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.generalextensionlibrary.ext.start
import com.android.generalextensionlibrary.ext.longToast
import com.android.generalextensionlibrary.ext.toast
import com.android.generalextensionlibrary.ext.click
import com.android.generalextensionlibrary.util.SharePreferenceUtil
import com.android.generalextensionlibrary.util.isIDCard
import com.android.generalextensionlibrary.util.isMobile
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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

        var spValue by SharePreferenceUtil ("key", "DefaultValue")
       button4.click {
            toast("spValue is " + spValue)
            spValue="value"
            toast("spValue2 is  " + spValue)
        }


    }


}
