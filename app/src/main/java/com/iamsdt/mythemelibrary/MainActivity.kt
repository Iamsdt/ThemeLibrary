package com.iamsdt.mythemelibrary

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.iamsdt.themelibrary.ColorActivity
import com.iamsdt.themelibrary.MyTheme
import com.iamsdt.themelibrary.ThemeUtils
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
            ColorActivity.addMoreThemes(getList(),"Color")
            startActivityForResult(ColorActivity.createIntent(this),themeRequestCode)
        }

        settingsBtn.setOnClickListener({
            startActivity(Intent(this@MainActivity,SettingsActivity::class.java))
        })
    }

    private fun getList(): ArrayList<MyTheme>{
        val list = ArrayList<MyTheme>()
        list.add(MyTheme("Red",R.style.red))
        list.add(MyTheme("Teal",R.style.teal))

        return list
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
