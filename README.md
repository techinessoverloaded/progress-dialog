# ProgressDialog
An easily customisable ProgressDialog Library for Android API 26 and above provided by Techiness Overloaded. Quite Useful for showing progress during any operation. Has support for both Determinate and Indeterminate ProgressBar. Also supports Dark Theme. Has javadoc Documentation

## Steps to add ProgressDialog Library to your Android Studio Project
Include the following code in your Project-level **build.gradle** file at the end of repositories:
```groovy
allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		      }
	   }
```

Now, include the following dependency in your App-level **build.gradle** file (Note: Current latest version is 1.1.1. Replace latest-version with 1.1.1 in the code below):
```groovy
dependencies {
	        implementation 'com.github.techinessoverloaded:progress-dialog:latest-version'
	           }
```
