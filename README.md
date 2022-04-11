# ProgressDialog Library [![](https://img.shields.io/github/v/release/techinessoverloaded/progress-dialog?color=green&label=JitPack)](https://jitpack.io/#techinessoverloaded/progress-dialog/1.4.1) [![](https://img.shields.io/github/v/release/techinessoverloaded/progress-dialog?color=green&label=Latest%20Release)](https://github.com/techinessoverloaded/progress-dialog/releases/latest)


An easily customisable ProgressDialog Library for Android API 24 and above provided by Techiness Overloaded (Developer name: Arunprasadh C). Quite Useful for showing progress during any operation. Has support for both Determinate and Indeterminate ProgressBar. Also supports Dark Theme. Has javadoc Documentation for all public Constructors, Attributes and Methods, making it easy to learn about the Library from Android Studio IDE.

**NOTE:** It is highly recommended to use the Latest Release Version of the Library and it is strongly recommended NOT to use any Pre-release versions of the library as they are used for testing out changes and are not production-ready. It is readily observable that Pre-release versions have "a" in their version code (Example: Version 1.4.0a4).

**Usage examples available at** [Usage Examples](#steps-to-add-progressdialog-library-to-your-android-studio-project) 
</br>**Java Documentation of Class and Methods available at** [Java Documentation of Library](https://techinessoverloaded.github.io/progress-dialog/java/progressdialoglibrary/com.techiness.progressdialoglibrary/-progress-dialog/index.html)
</br>**Kotlin Documentation of Class and Methods available at** [Kotlin Documentation of Library](https://techinessoverloaded.github.io/progress-dialog/kotlin/progressdialoglibrary/com.techiness.progressdialoglibrary/-progress-dialog/index.html)

## Key Features
- Highly Customisable.
- Has support for Dark Theme.
- Has support for AutoTheming from Android 11 (API Level 30).
- Can be set in both Determinate and Indeterminate Mode.
- Has support for Negative Button, Title, and ProgressView.
- Desgined for usage in both Java and Kotlin Android Projects.

## What's New in Version 1.4.1 ?
- Changed Target Android Version to Android 12.1 (API Level 32).
- Migrated to Gradle version 7.2 and JDK 11.
- Migrated from `findViewById()` to **Android ViewBinding**.
- Migrated the `ProgressDialog` class from **Java** to **Kotlin**.
- Migrated variables to Kotlin `var` Properties. So, Property Syntax can be used by **Kotlin** users and usual Getters/Setters can be used by **Java** Users.
- Still, Properties which have overloaded setters (Example `setMessage()`) or are Set-Only Properties don't support Property Syntax of **Kotlin**. Usual Getter/Setter should only be used.
- `null` is not accepted as an argument anymore in any of the methods (excluding those which support Default Listeners).
- Solved an issue where `ProgressDialog.THEME_FOLLOW_SYSTEM` didn't work when set with Constructor.
- Added Getters for Title and Message of `ProgressDialog`.
- Added Getters and Setters for Secondary Progress Tint List.
- Removed Boolean Return Types for some Setters and made some Setters to throw `UnsupportedOperationException` when called on undesirable scenarios.

### The following changes were made in the previous version (1.3.1)
- Resolved an issue where **NegativeButton** showed up even when the `setNegativeButton()` method was not called

## How can you contribute to this Project ?
- You can point out any issue which you faced while using this library.
- You can suggest new features to be added to this library.
- You can give pull requests with any reasonable changes or feature additions.
- I haven't done JUnit testing for this library. You can help out with the same.
- You can share about this library to your peer Android developers.

## Steps to add ProgressDialog Library to your Android Studio Project

**Make Sure that you are using Gradle version 7.0 and above and JDK Version 11**</br></br>
Include the following code in your Project-level **build.gradle** file at the end of repositories:
```groovy
allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		             }
	    }
```

Now, include the following dependency in your App-level **build.gradle** file:
#### Note: Current latest version is **1.4.1**.
```groovy
dependencies {
	        implementation 'com.github.techinessoverloaded:progress-dialog:1.4.1'
	     }
```
#### Or you can also define the version as a String like this(You can copy either this code or the above one):
```groovy
dependencies {
                def latest-version = "1.4.1"
	        implementation "com.github.techinessoverloaded:progress-dialog:$latest-version"
	     }
```
Now import ProgressDialog class in your Activity/Fragment:
```java
import com.techiness.progressdialoglibrary.ProgressDialog;
```

## Various Constructors available

### Simple Constructor 
#### Uses Light Theme by Default. Note: Theme can be changed after Instantiation using setTheme(int themeConstant) method. 
**IMPORTANT** : If you want to Instantiate ```ProgressDialog``` Class in a **Fragment**, use ```requireContext()``` method instead of ```this``` keyword for passing ```Context``` object. Similarly, for Instantiating ```ProgressDialog``` Class in **Inner Classes**, use ```YourActivity.this``` in **Java** or ```this@YourActivity``` in **Kotlin** instead of simple ```this``` keyword for passing ```Context``` object.
#### Java Code:
```java
ProgressDialog progressDialog = new ProgressDialog(this); //same as new ProgressDialog(this, ProgressDialog.THEME_LIGHT);
```
#### Kotlin Code:
```kotlin
val progressDialog = ProgressDialog(this) //same as ProgressDialog(this, ProgressDialog.THEME_LIGHT)
```

### Constructor for Alternate Theme 
#### This Constructor can be used for setting Dark Theme.
#### Java Code:
```java
ProgressDialog progressDialog = new ProgressDialog(this, ProgressDialog.THEME_DARK);
```
#### Kotlin Code:
```kotlin
val progressDialog = ProgressDialog(this, ProgressDialog.THEME_DARK)
```

### Constructor for Alternate Mode 
#### Default mode is Indeterminate mode. Note: Mode can be changed as and when necessary using in-built methods.
#### Java Code:
```java
ProgressDialog progressDialog = new ProgressDialog(ProgressDialog.MODE_DETERMINATE,this); // for instantiating with Determinate mode
```
### Kotlin Code:
```kotlin
val progressDialog = ProgressDialog(ProgressDialog.MODE_DETERMINATE,this) // for instantiating with Determinate mode
```

### Constructor for Alternate Mode and Theme
#### This constructor can be used to customise both Mode and Theme of ProgressDialog.
#### Java Code:
```java
ProgressDialog progressDialog = new ProgressDialog(ProgressDialog.MODE_DETERMINATE,this,ProgressDialog.THEME_DARK); 
```
#### Kotlin Code:
```kotlin
val progressDialog = ProgressDialog(ProgressDialog.MODE_DETERMINATE,this,ProgressDialog.THEME_DARK)
```
## Simple Examples

#### Note: These examples are for simple illustration of ProgressDialog Library. For completely knowing about the Library, refer to the JavaDoc/KDoc Documentation of the Library through Android Studio.

### How to use `ProgressDialog.THEME_FOLLOW_SYSTEM` with Constructor ?
#### Java Code:
```java
if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) //Check if Android API Level is greater than or equal to 30
{
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_FOLLOW_SYSTEM); //This is optional. This will enable Android's Autotheming for the entire App
ProgressDialog progressDialog = new ProgressDialog(this,ProgressDialog.THEME_FOLLOW_SYSTEM); // Enables AutoTheming for the ProgressDialog instance.
}
else //Autotheming not compatible
{
ProgressDialog progressDialog = new ProgressDialog(this,ProgressDialog.THEME_DARK); // or any other constructors mentioned above
}
```
#### Kotlin Code:
```kotlin
if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) //Check if Android API Level is greater than or equal to 30
{
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_FOLLOW_SYSTEM) //This is optional. This will enable Android's Autotheming for the entire App
val progressDialog = ProgressDialog(this,ProgressDialog.THEME_FOLLOW_SYSTEM) // Enables AutoTheming for the ProgressDialog instance.
}
else //Autotheming not compatible
{
val progressDialog = ProgressDialog(this,ProgressDialog.THEME_DARK) // or any other constructors mentioned above
}
```

### How to use `ProgressDialog.THEME_FOLLOW_SYSTEM` with `setTheme(int themeConstant)` method ?
#### Java Code:
```java
if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) //Check if Android API Level is greater than or equal to 30
{
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_FOLLOW_SYSTEM); //This is optional. This will enable Android's Autotheming for the entire App
progressDialog.setTheme(ProgressDialog.THEME_FOLLOW_SYSTEM); // Enables AutoTheming for the ProgressDialog instance.
}
else //Autotheming not compatible
{
progressDialog.setTheme(ProgressDialog.THEME_DARK); // or ProgressDialog.THEME_LIGHT
}
```
#### Kotlin Code:
```kotlin
if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) //Check if Android API Level is greater than or equal to 30
{
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_FOLLOW_SYSTEM) //This is optional. This will enable Android's Autotheming for the entire App
progressDialog.theme = ProgressDialog.THEME_FOLLOW_SYSTEM) // Enables AutoTheming for the ProgressDialog instance.
}
else //Autotheming not compatible
{
progressDialog.theme = ProgressDialog.THEME_DARK // or ProgressDialog.THEME_LIGHT
}
```

### Indeterminate ProgressDialog without Title (Light Theme) 	
#### Java Code:
```java
ProgressDialog progressDialog = new ProgressDialog(this);
progressDialog.show();
```
#### Kotlin Code:
```kotlin
val progressDialog = ProgressDialog(this)
progressDialog.show()
```
#### Output:
<img src="./output/indeter.jpg" width=26% height=26%>

### Indeterminate ProgressDialog without Title (Dark Theme) 	
#### Java Code:
```java
progressDialog.setTheme(ProgressDialog.THEME_DARK);
progressDialog.show();
```
#### Kotlin Code:
```kotlin
with(progressDialog) 
{
   theme = ProgressDialog.THEME_DARK
   show()
}
```
#### Output:
<img src="./output/indeter_dark.jpg" width=26% height=26%>

### Determinate ProgressDialog without Title, without ProgressView, with Secondary Progress (Light Theme)
#### Java Code:
```java
progressDialog.setTheme(ProgressDialog.THEME_LIGHT);
progressDialog.setMode(ProgressDialog.MODE_DETERMINATE);
progressDialog.setProgress(65);
progressDialog.setSecondaryProgress(80);
progressDialog.hideProgressText();
progressDialog.show();
```
#### Kotlin Code:
```kotlin
with(progressDialog)
{
   theme = ProgressDialog.THEME_LIGHT
   mode = ProgressDialog.MODE_DETERMINATE
   progress = 65
   secondaryProgress = 80
   hideProgressText()
   show()
}
```
#### Output:
<img src="./output/deter_without_progress.jpg" width=26% height=26%>

### Determinate ProgressDialog without Title, without ProgressView, with Secondary Progress (Dark Theme)
#### Java Code:
```java
progressDialog.setTheme(ProgressDialog.THEME_DARK);
progressDialog.setMode(ProgressDialog.MODE_DETERMINATE);
progressDialog.setProgress(65);
progressDialog.setSecondaryProgress(80);
progressDialog.hideProgressText();
progressDialog.show();
```
#### Kotlin Code:
```kotlin
with(progressDialog)
{
   theme = ProgressDialog.THEME_DARK
   mode = ProgressDialog.MODE_DETERMINATE
   progress = 65
   secondaryProgress = 80
   hideProgressText()
   show()
}
```
#### Output:
<img src="./output/deter_without_progress_dark.jpg" width=26% height=26%>

### Determinate ProgressDialog without Title, with ProgressView as Percentage (Light Theme)
#### Java Code:
```java
progressDialog.setTheme(ProgressDialog.THEME_LIGHT);
progressDialog.setMode(ProgressDialog.MODE_DETERMINATE);
progressDialog.setProgress(65);
progressDialog.show();
```
#### Kotlin Code:
```kotlin
with(progressDialog)
{
   theme = ProgressDialog.THEME_LIGHT
   mode = ProgressDialog.MODE_DETERMINATE
   setprogress = 65
   show()
}
```
#### Output:
<img src="./output/deter_percent.jpg" width=26% height=26%>

### Determinate ProgressDialog without Title, with ProgressView as Percentage (Dark Theme)
#### Java Code:
```java
progressDialog.setTheme(ProgressDialog.THEME_DARK);
progressDialog.setMode(ProgressDialog.MODE_DETERMINATE);
progressDialog.setProgress(65);
progressDialog.show();
```
#### Kotlin Code:
```kotlin
with(progressDialog)
{
   theme = ProgressDialog.THEME_DARK
   mode = ProgressDialog.MODE_DETERMINATE
   progress = 65
   show()
}
```
#### Output:
<img src="./output/deter_percent_dark.jpg" width=26% height=26%>

### Indeterminate ProgressDialog with Title (Light Theme)
#### Java Code:
```java
progressDialog.setTheme(ProgressDialog.THEME_LIGHT);
progressDialog.setMode(ProgressDialog.MODE_INDETERMINATE);
progressDialog.setTitle("Indeterminate");
progressDialog.show();
```
#### Kotlin Code:
```kotlin
with(progressDialog)
{
   theme = ProgressDialog.THEME_LIGHT
   mode = ProgressDialog.MODE_INDETERMINATE
   setTitle("Indeterminate")
   show()
}
```
#### Output:
<img src="./output/indeter_with_title.jpg" width=26% height=26%>

### Indeterminate ProgressDialog with Title (Dark Theme)
#### Java Code:
```java
progressDialog.setTheme(ProgressDialog.THEME_DARK);
progressDialog.setMode(ProgressDialog.MODE_INDETERMINATE);
progressDialog.setTitle("Indeterminate");
progressDialog.show();
```
#### Kotlin Code:
```kotlin
with(progressDialog)
{
   theme = ProgressDialog.THEME_DARK
   mode = ProgressDialog.MODE_INDETERMINATE
   setTitle("Indeterminate")
   show()
}
```
#### Output:
<img src="./output/indeter_with_title_dark.jpg" width=26% height=26%>

### Determinate ProgressDialog with Title, Secondary Progress and ProgressView as Fraction (Light Theme)
#### Java Code:
```java
progressDialog.setTheme(ProgressDialog.THEME_LIGHT);
progressDialog.setMode(ProgressDialog.MODE_DETERMINATE);
progressDialog.setTitle("Determinate");
progressDialog.setProgress(65);
progressDialog.setSecondaryProgress(80);
progressDialog.showProgressTextAsFraction(true);
progressDialog.show();
```
#### Kotlin Code:
```kotlin
with(progressDialog)
{
   theme = ProgressDialog.THEME_LIGHT
   mode = ProgressDialog.MODE_DETERMINATE
   setTitle("Determinate")
   progress = 65
   secondaryProgress = 80
   showProgressTextAsFraction(true)
   show()
}
```
#### Output:
<img src="./output/deter_title.jpg" width=26% height=26%>

### Determinate ProgressDialog with Title, Secondary Progress and ProgressView as Fraction (Dark Theme)
#### Java Code:
```java
progressDialog.setTheme(ProgressDialog.THEME_DARK);
progressDialog.setMode(ProgressDialog.MODE_DETERMINATE);
progressDialog.setTitle("Determinate");
progressDialog.setProgress(65);
progressDialog.setSecondaryProgress(80);
progressDialog.showProgressTextAsFraction(true);
progressDialog.show();
```
#### Kotlin Code:
```kotlin
with(progressDialog)
{
   theme = ProgressDialog.THEME_DARK
   mode = ProgressDialog.MODE_DETERMINATE
   setTitle("Determinate")
   progress = 65
   secondaryProgress = 80
   showProgressTextAsFraction(true)
   show()
}
```
#### Output:
<img src="./output/deter_title_dark.jpg" width=26% height=26%>

### Indeterminate ProgressDialog with NegativeButton and Custom OnClickListener for NegativeButton (Light Theme)
##### Note: Enabling NegativeButton will automatically enable TitleView.
#### Java Code:
```java
progressDialog.setTheme(ProgressDialog.THEME_LIGHT);
progressDialog.setMode(ProgressDialog.MODE_INDETERMINATE);
progressDialog.setNegativeButton("Dismiss","Indeterminate",v -> {
                    Toast.makeText(this,"Custom OnClickListener for Indeterminate",Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                });
progressDialog.show();
```
#### Kotlin Code:
```kotlin
with(progressDialog)
{
   theme = ProgressDialog.THEME_LIGHT
   mode = ProgressDialog.MODE_INDETERMINATE
   setNegativeButton("Dismiss", "Determinate") {
     Toast.makeText(this@KotlinActivity, "Custom OnClickListener for Indeterminate", Toast.LENGTH_LONG).show()
     dismiss()
   }   
   show()
}
```
#### Output:
<img src="./output/indeter_with_negativebtn.jpg" width=26% height=26%>

### Indeterminate ProgressDialog with NegativeButton and Custom OnClickListener for NegativeButton (Dark Theme)
##### Note: Enabling NegativeButton will automatically enable TitleView.
#### Java Code:
```java
progressDialog.setTheme(ProgressDialog.THEME_DARK);
progressDialog.setMode(ProgressDialog.MODE_INDETERMINATE);
progressDialog.setNegativeButton("Dismiss","Indeterminate",v -> {
                    Toast.makeText(this,"Custom OnClickListener for Indeterminate",Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                });
progressDialog.show();
```
#### Kotlin Code:
```kotlin
with(progressDialog)
{
   theme = ProgressDialog.THEME_DARK
   mode = ProgressDialog.MODE_INDETERMINATE
   setNegativeButton("Dismiss", "Determinate") {
     Toast.makeText(this@KotlinActivity, "Custom OnClickListener for Indeterminate", Toast.LENGTH_LONG).show()
     dismiss()
   }
   show()
}
```
#### Output:
<img src="./output/indeter_with_negativebtn_dark.jpg" width=26% height=26%>

### Determinate ProgressDialog with NegativeButton and Default OnClickListener for NegativeButton (Light Theme)
##### Note: Enabling NegativeButton will automatically enable TitleView.
#### Java Code:
```java
progressDialog.setTheme(ProgressDialog.THEME_LIGHT);
progressDialog.setMode(ProgressDialog.MODE_DETERMINATE);
progressDialog.setProgress(54);
progressDialog.showProgressTextAsFraction(true);
progressDialog.setNegativeButton("Cancel","Determinate",null);
progressDialog.show();
```
#### Kotlin Code:
```kotlin
with(progressDialog)
{
   theme = ProgressDialog.THEME_LIGHT
   mode = ProgressDialog.MODE_DETERMINATE
   progress = 54
   showProgressTextAsFraction(true)
   setNegativeButton("Cancel","Determinate",null)
   show()
}
```
#### Output:
<img src="./output/deter_with_negativebtn.jpg" width=26% height=26%>

### Determinate ProgressDialog with NegativeButton and Default OnClickListener for NegativeButton (Dark Theme)
##### Note: Enabling NegativeButton will automatically enable TitleView.
#### Java Code:
```java
progressDialog.setTheme(ProgressDialog.THEME_DARK);
progressDialog.setMode(ProgressDialog.MODE_DETERMINATE);
progressDialog.setProgress(54);
progressDialog.showProgressTextAsFraction(true);
progressDialog.setNegativeButton("Cancel","Determinate",null);
progressDialog.show();
```
#### Kotlin Code:
```kotlin
with(progressDialog)
{
   theme = ProgressDialog.THEME_DARK
   mode = ProgressDialog.MODE_DETERMINATE
   progress = 54
   showProgressTextAsFraction(true)
   setNegativeButton("Cancel","Determinate",null)
   show()
}
```
#### Output:
<img src="./output/deter_with_negativebtn_dark.jpg" width=26% height=26%>
