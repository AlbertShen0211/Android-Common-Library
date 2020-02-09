package com.android.controlextension

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.generalextensionlibrary.activity.start
import com.android.generalextensionlibrary.toast.longToast
import com.android.generalextensionlibrary.toast.toast
import com.android.generalextensionlibrary.view.onClick
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val b=false;

        button1.onClick {
            if (b)
            toast("hello")
            else
                longToast("world")

        }

        button2.onClick {
            start<HelloActivity>()
        }


    }


}
