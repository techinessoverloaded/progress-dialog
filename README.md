# ProgressDialog
An easily customisable ProgressDialog Library for Android API 26 and above provided by Techiness Overloaded. Quite Useful for showing progress during any operation. Has support for both Determinate and Indeterminate ProgressBar. Also supports Dark Theme. Has javadoc Documentation for all public Constructors, Attributes and Methods, making it easy to learn about the Library from Android Studio IDE.

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
## Various Constructors available

### Simple Constructor 
#### Uses Light Theme by Default. Note: Theme cannot be changed after Instantiation.
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
ProgressDialog progressDialog = new ProgressDialog(ProgressDialog.MODE_DETERMINATE,this); // for initialising with Determinate mode
```
### Constructor for Alternate Mode and Theme
#### This constructor can be used to customise both Mode and Theme of ProgressDialog.
#### Code:
```java
ProgressDialog progressDialog = new ProgressDialog(ProgressDialog.MODE_DETERMINATE,this,ProgressDialog.THEME_DARK); 
```
