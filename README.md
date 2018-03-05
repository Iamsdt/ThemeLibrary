# ThemeLibrary
A very easy Android theme library

###Getting Started

###Prerequisites
The minimum api for this library is **17** (Android 4.2)

###Installing
Add it in your root `build.gradle` at the end of repositories:
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
    implementation 'com.github.Iamsdt:ThemeLibrary:1.5'
}
````

###Configuration
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
#####Step 2:
Update **AndroidManifest.xml**
````
<application
    ....
    
    android:theme="@style/AppTheme"
    tools:replace="android:theme">
    
        ....
        
</application>
````
####Step 3:

