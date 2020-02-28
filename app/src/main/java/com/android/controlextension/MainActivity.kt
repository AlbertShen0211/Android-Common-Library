package com.android.controlextension

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateUtils
import com.android.generalextensionlibrary.ext.*
import com.android.generalextensionlibrary.util.*
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.util.*

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


    }


}
