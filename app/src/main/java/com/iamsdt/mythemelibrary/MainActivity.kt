package com.iamsdt.mythemelibrary

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val themeRequestCode = 121

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeUtils.initialize(this)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        mainBtn.setOnClickListener {
            //startActivityForResult(ColorActivity.createIntent(this),themeRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (themeRequestCode == requestCode){
            if (resultCode == Activity.RESULT_OK) {
                recreate()
            }
        }
    }
}
