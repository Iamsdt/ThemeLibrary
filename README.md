# ThemeLibrary
A very easy Android theme library for choose theme at runtime.

### Getting Started
Very easy to provide an option to the user to change the theme of their choice. This library has already some prebuilt theme.
So if you want to provide more option it's very easy.
On the other hand, you can customize according to your requirement.

##### See some screenshot </br>

| ![Sample 1][sample1] | ![Sample 2][sample2] | ![Night mode][sample3] | ![Preference Fragment][sample4] |
|--- | --- | --- | ---|
| Sample 1 | Sample 2 | Night mode | Preference Fragment| 

[sample1]: ../master/img/sample1.png "Deafult theme list"
[sample2]: ../master/img/sample2.png "Deafult theme list2"
[sample3]: ../master/img/nightmode.png "Night mode"
[sample4]: ../master/img/preference.png "Preference theme"


### Prerequisites
The minimum API for this library is **17** (Android 4.2)

### Installing
Add it to your root `build.gradle` at the end of repositories:
````
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
        }   
    }
````
now add the dependency
````
dependencies {
    implementation 'com.github.Iamsdt:ThemeLibrary:2.0'
}
````

### Configuration
##### Step 1:
Change your parent of app theme to **LibraryAppTheme**
>Note: No need to add primary color, primary dark color and accent color.
 it's already added in the **LibraryAppTheme**, unless you want to update.
````
<!-- Base Theme -->
<style name="AppTheme" parent="LibraryAppTheme">
    .....
</style>
````
##### Step 2:
Update **AndroidManifest.xml**
````
<application
    ....
    
    android:theme="@style/AppTheme"
    tools:replace="android:theme">
    
        ....
        
</application>
````
#### Step 3:
Start ColorActivity
````
startActivityForResult(ColorActivity.createIntent(this),121)
````
now override `onActivityResult()` method and recreate the activity
````
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 121){
            if (resultCode == Activity.RESULT_OK) {
                recreate()
            }
        }
    }
````

### Customization
>This library is highly customizable... Let's customize with your needs

If you want to provide your custom theme list(not default theme list)
First crate theme in **Style.xml**
````
<style name="teal" parent="LibraryAppTheme.NoActionBar">
    <item name="colorPrimary">@color/teal_500</item>
    <item name="colorPrimaryDark">@color/teal_700</item>
    <item name="colorAccent">@color/blue_500</item>
    <item name="colorControlHighlight">@color/cyan_500</item>
</style>
````
Create an ArrayList of MyTheme and fill the array
````
val list = ArrayList<MyTheme>()
list.add(MyTheme("Red",R.style.red))
````
call AddMoreThemes() Method and start Activity
````
ColorActivity.addMoreThemes(getList())
startActivityForResult(ColorActivity.createIntent(this),121)
````
If you want to remove default theme
````
ColorActivity.addMoreThemes(list,false)
````
If you want to change toolbar title with custom list
````            
ColorActivity.addMoreThemes(list,"Color")
````
or,
````
ColorActivity.addMoreThemes(list,false,"Color")
````
only change toolbar title
````
ColorActivity.updateToolbarTitle("Color")
````
want to hide night mode icon
````
ColorActivity.hideNightModeIcon()
````

### Licence
####MIT License

Copyright (c) 2018 **Shudipto Trafder**

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.