package com.iamsdt.themelibrary

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import androidx.content.edit
import kotlinx.android.synthetic.main.content_color.*

class ColorActivity : AppCompatActivity(),ClickListener {

    private val themes = ArrayList<MyTheme>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeUtils.initialize(this)
        setContentView(R.layout.activity_color)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val manager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false)

        color_recyclerView.layoutManager = manager

        //fill theme ids
        fillThemeIds()
        //adapter
        val adapter = ColorAdapter(themes, this, this)

        color_recyclerView.adapter = adapter

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /**
     * This method is for add new theme in arrayList
     * array list contain theme name and it's id
     */
    private fun fillThemeIds() {

        val list = getThemes()

        //if theme is not empty then add all
        if (list.isNotEmpty()){
            themes.addAll(list)
        }

        if (isDefaultEnabled){
            //fill array with styles ids
            themes.add(MyTheme("Default", R.style.LibraryAppTheme_NoActionBar))
            themes.add(MyTheme("Amber", R.style.amber_dark))
            themes.add(MyTheme("Purple", R.style.purple_dark))
            themes.add(MyTheme("Orange", R.style.orange))
            themes.add(MyTheme("Cyan", R.style.cyan))
            themes.add(MyTheme("Deep Orange", R.style.deeporange))
            themes.add(MyTheme("Green", R.style.green))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //buy calling android.R.id.home
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            //setResult(Activity.RESULT_OK)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(themeID: Int) {
        val themeCont = themes[themeID]

        val sp = getSharedPreferences(ConstantUtil.colorSp, Context.MODE_PRIVATE)
        sp.edit { putInt(ConstantUtil.themeKey,themeCont.id) }

        val restartIntent = Intent(this, ColorActivity::class.java)
        setResult(Activity.RESULT_OK)

        finish()

        startActivity(restartIntent)
        overridePendingTransition(0, 0)
    }

    companion object {
        private val myThemes = ArrayList<MyTheme>()

        private var isDefaultEnabled = true

        fun createIntent(context: Context): Intent {
            return Intent(context, ColorActivity::class.java)
        }

        fun addMoreThemes(themes:ArrayList<MyTheme>){

            //clear all data
            myThemes.clear()

            //add new data
            myThemes.addAll(themes)
        }

        fun addMoreThemes(themes:ArrayList<MyTheme>,defaultEnabled:Boolean){

            isDefaultEnabled = defaultEnabled

            //clear all data
            myThemes.clear()

            //add new data
            myThemes.addAll(themes)
        }


        private fun getThemes() = myThemes
    }

}
