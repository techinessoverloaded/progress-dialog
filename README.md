# ProgressDialog Library [![](https://jitpack.io/v/techinessoverloaded/progress-dialog.svg)](https://jitpack.io/#techinessoverloaded/progress-dialog)

An easily customisable ProgressDialog Library for Android API 24 and above provided by Techiness Overloaded (Developer name: Arunprasadh C). Quite Useful for showing progress during any operation. Has support for both Determinate and Indeterminate ProgressBar. Also supports Dark Theme. Has javadoc Documentation for all public Constructors, Attributes and Methods, making it easy to learn about the Library from Android Studio IDE.

## Key Features
- Highly Customisable.
- Has support for Dark Theme.
- Can be set in both Determinate and Indeterminate Mode.
- Has support for Negative Button, Title, and ProgressView.

## What's New in Version 1.2.2 ?
Negative Button has been added in Version 1.2.2. You can set and hide Negative Button as and when required with default or Custom OnClickListener.

## Steps to add ProgressDialog Library to your Android Studio Project

Include the following code in your Project-level **build.gradle** file at the end of repositories:
```groovy
allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		             }
	    }
```

Now, include the following dependency in your App-level **build.gradle** file:
#### Note: Current latest version is **1.2.2**. **Replace latest-version with 1.2.2 in the code below**
```groovy
dependencies {
	        implementation 'com.github.techinessoverloaded:progress-dialog:latest-version'
	     }
```
#### Or you can also define the version as a String like this(You can copy either this code or the above one):
```groovy
dependencies {
                def latest-version = "1.2.2"
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
**IMPORTANT** : If you want to Instantiate ProgressDialog object in a Fragment, use **requireContext()** method instead of **this** keyword for passing Context object. Similarly, for Instantiating ProgressDialog object in inner classes, use **YourActivity.this** instead of simple **this** keyword for passing Context object.
#### Code:
```java
ProgressDialog progressDialog = new ProgressDialog(this); //same as new ProgressDialog(this,ProgressDialog.THEME_LIGHT);
```
### Constructor for Alternate Theme 
#### This Constructor can be used for setting Dark Theme.
#### Code:
```java
ProgressDialog progressDialog = new ProgressDialog(this,ProgressDialog.THEME_DARK);
```
### Constructor for Alternate Mode 
#### Default mode is Indeterminate mode. Note: Mode can be changed as and when necessary using in-built methods.
#### Code:
```java
ProgressDialog progressDialog = new ProgressDialog(ProgressDialog.MODE_DETERMINATE,this); // for instantiating with Determinate mode
```
### Constructor for Alternate Mode and Theme
#### This constructor can be used to customise both Mode and Theme of ProgressDialog.
#### Code:
```java
ProgressDialog progressDialog = new ProgressDialog(ProgressDialog.MODE_DETERMINATE,this,ProgressDialog.THEME_DARK); 
```
## Simple Examples

### Indeterminate ProgressDialog without Title (Light Theme) 	
#### Code:
```java
ProgressDialog progressDialogLt = new ProgressDialog(this);
progressDialogLt.show();
```
#### Output:
<img src="./output/indeter.jpg" width=26% height=26%>

### Indeterminate ProgressDialog without Title (Dark Theme) 	
#### Code:
```java
ProgressDialog progressDialogDk = new ProgressDialog(this,ProgressDialog.THEME_DARK);
progressDialogDk.show();
```
#### Output:
<img src="./output/indeter_dark.jpg" width=26% height=26%>

### Determinate ProgressDialog without Title, without ProgressView (Light Theme)
#### Code:
```java
progressDialogLt.setMode(ProgressDialog.MODE_DETERMINATE);
progressDialogLt.setProgress(65);
progressDialogLt.hideProgressText();
progressDialogLt.show();
```
#### Output:
<img src="./output/deter_without_progress.jpg" width=26% height=26%>

### Determinate ProgressDialog without Title, without ProgressView (Dark Theme)
#### Code:
```java
progressDialogDk.setMode(ProgressDialog.MODE_DETERMINATE);
progressDialogDk.setProgress(65);
progressDialogDk.hideProgressText();
progressDialogDk.show();
```
#### Output:
<img src="./output/deter_without_progress_dark.jpg" width=26% height=26%>

### Determinate ProgressDialog without Title, with ProgressView as Percentage (Light Theme)
#### Code:
```java
progressDialogLt.setMode(ProgressDialog.MODE_DETERMINATE);
progressDialogLt.setProgress(65);
progressDialogLt.show();
```
#### Output:
<img src="./output/deter_percent.jpg" width=26% height=26%>

### Determinate ProgressDialog without Title, with ProgressView as Percentage (Dark Theme)
#### Code:
```java
progressDialogDk.setMode(ProgressDialog.MODE_DETERMINATE);
progressDialogDk.setProgress(65);
progressDialogDk.show();
```
#### Output:
<img src="./output/deter_percent_dark.jpg" width=26% height=26%>

