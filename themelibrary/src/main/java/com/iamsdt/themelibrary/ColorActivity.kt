package com.iamsdt.themelibrary

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import androidx.content.edit
import kotlinx.android.synthetic.main.activity_color.*
import kotlinx.android.synthetic.main.content_color.*

class ColorActivity : AppCompatActivity(),ClickListener {

    private val themes = ArrayList<MyTheme>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initialize theme
        ThemeUtils.initialize(this)

        setContentView(R.layout.activity_color)

        //toolbar
        if (extraTitle.isNotEmpty()) {
            toolbar.title = extraTitle
        }
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

        val list = getThemes()

        //if theme is not empty then add all
        if (list.isNotEmpty()){
            themes.addAll(list)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            android.R.id.home -> {
                onBackPressed()
                //setResult(Activity.RESULT_OK)
                finish()
                return true
            }

            R.id.nightMode ->{
                nightModeStatus = if (nightModeStatus){
                    //night mode on
                    //now off night mode
                    turnOffNightMode(this@ColorActivity)
                    setResult(Activity.RESULT_OK)
                    restartActivity()
                    false
                } else{
                    //night mode false
                    //now on night mode
                    turnOnNightMode(this@ColorActivity)
                    restartActivity()
                    true
                }

                return true
            }

            else ->
                return super.onOptionsItemSelected(item)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.color,menu)

        val nightMode = menu?.findItem(R.id.nightMode)

        if (nightModeStatus) {
            nightMode?.setIcon(R.drawable.ic_half_moon)
        } else {
            nightMode?.setIcon(R.drawable.ic_wb_sunny_black_24dp)
        }

        return true
    }

    override fun onItemClick(themeID: Int) {
        val themeCont = themes[themeID]

        val sp = getSharedPreferences(ConstantUtil.colorSp, Context.MODE_PRIVATE)
        sp.edit { putInt(ConstantUtil.themeKey,themeCont.id) }

        restartActivity()
    }

    private fun restartActivity(){
        val restartIntent = Intent(this@ColorActivity, ColorActivity::class.java)
        setResult(Activity.RESULT_OK)

        finish()

        startActivity(restartIntent)
        overridePendingTransition(0, 0)
    }

    companion object {

        private var nightModeStatus = false

        private val myThemes = ArrayList<MyTheme>()

        private var isDefaultEnabled = true

        private var extraTitle = ""

        /**
         * Create Intent for launcher class
         * @param context of the class
         * @return Intent
         */
        fun createIntent(context: Context): Intent {
            return Intent(context, ColorActivity::class.java)
        }

        /**
         * Add more theme to the list
         * and send to the adapter
         * @param themes new theme list
         */
        fun addMoreThemes(themes:ArrayList<MyTheme>){

            //clear all data
            myThemes.clear()

            //add new data
            myThemes.addAll(themes)
        }

        /**
         * Add more theme to the list
         * and send to the adapter
         * @param themes new theme list
         * @param extraTitle title for the activity
         */
        fun addMoreThemes(themes:ArrayList<MyTheme>,extraTitle:String){

            if (extraTitle.isNotEmpty()){
                this.extraTitle = extraTitle
            }

            //clear all data
            myThemes.clear()

            //add new data
            myThemes.addAll(themes)
        }

        /**
         * Add more theme to the list with more option
         * and send to the adapter
         * @param themes new theme list
         * @param defaultEnabled if developer want to enabled or disable the default theme
         */
        fun addMoreThemes(themes:ArrayList<MyTheme>,defaultEnabled:Boolean){

            isDefaultEnabled = defaultEnabled

            //clear all data
            myThemes.clear()

            //add new data
            myThemes.addAll(themes)
        }

        /**
         * Add more theme to the list with more option
         * and send to the adapter
         * @param themes new theme list
         * @param defaultEnabled if developer want to enabled or disable the default theme
         * @param extraTitle title for the activity
         */
        fun addMoreThemes(themes:ArrayList<MyTheme>,defaultEnabled:Boolean,extraTitle:String){

            isDefaultEnabled = defaultEnabled

            if (extraTitle.isNotEmpty()){
                this.extraTitle = extraTitle
            }

            //clear all data
            myThemes.clear()

            //add new data
            myThemes.addAll(themes)
        }


        /**
         * Getter method of theme list
         */
        private fun getThemes() = myThemes

        /**
         * Method for turn on night mode
         * this only change the value of sp
         * @param context for access sp
         */
        private fun turnOnNightMode(context: Context){
            val sharedPreferences = context.getSharedPreferences(ConstantUtil.NIGHT_MODE_SP_KEY,Context.MODE_PRIVATE)
            sharedPreferences.edit {
                putBoolean(ConstantUtil.NIGHT_MODE_VALUE_KEY,true)
            }
        }

        /**
         * Method for turn on night mode
         * this only change the value of sp
         * @param context for access sp
         */
        private fun turnOffNightMode(context: Context){
            val sharedPreferences = context.getSharedPreferences(ConstantUtil.NIGHT_MODE_SP_KEY,Context.MODE_PRIVATE)
            sharedPreferences.edit {
                putBoolean(ConstantUtil.NIGHT_MODE_VALUE_KEY,false)
            }
        }
    }

}
