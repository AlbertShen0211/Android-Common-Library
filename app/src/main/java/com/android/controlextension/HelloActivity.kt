package com.android.controlextension

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.generalextensionlibrary.ext.click
import com.android.generalextensionlibrary.ext.hideSoftInput
import com.android.generalextensionlibrary.ext.showSoftInput
import kotlinx.android.synthetic.main.activity_hello.*

class HelloActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)
        button.click {
            hideSoftInput(this)
        }
        button6.click {
            showSoftInput(editText)
        }
    }
}