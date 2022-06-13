For Documentation, visit [ProgressDialog Library Documentation](https://techinessoverloaded.github.io/progress-dialog/index.html)
### ProgressDialog Library Change Log
#### Version 1.5.1 (Feature Update)
##### Features
- Added Time Tracking feature for Determinate Mode ProgressDialog as suggested by [@vzool](https://github.com/vzool) in Issue [#13](https://github.com/techinessoverloaded/progress-dialog/issues/13). Time tracking can be enabled by passing `true` to the first parameter of `setOnShowListener` method. The time elapsed will be updated until the `progress` reaches `maxValue`.

##### Bug Fixes
- Fixed an Issue where unwanted views got displayed on the ProgressDialog, as pointed out by [@soenkegissel](https://github.com/soenkegissel) and [@mg2000](https://github.com/mg2000) in Issue [#16](https://github.com/techinessoverloaded/progress-dialog/issues/16).

##### Maintenance
- Merged Pull Requests [#11](https://github.com/techinessoverloaded/progress-dialog/pull/11), [#12](https://github.com/techinessoverloaded/progress-dialog/pull/12), [#14](https://github.com/techinessoverloaded/progress-dialog/pull/14), [#15](https://github.com/techinessoverloaded/progress-dialog/pull/15), [#17](https://github.com/techinessoverloaded/progress-dialog/pull/17) given by [@dependabot](https://github.com/dependabot) to update Material Version, Gradle Version, Gradle Build Action Version, ConstraintLayout Version and AppCompat Version. 

#### Version 1.4.4 (Maintenance Update)
- Rectified an error in Documentation regarding Android API Level.
- Removed some Boilerplate code by using Kotlin Extension Functions.
- Upgraded to Android Gradle Plugin 7.2 and Gradle version 7.4.2.
- Migrated from Groovy DSL to Kotlin DSL for Gradle Scripts.
- Updated dependencies to the latest version.

#### Version 1.4.3 (Bug Fix Update)
- Resolved an issue where `incrementValue` didn't work as expected.
- Updated KDoc Documentation for some properties and functions.
- API Documentation available in both Java and Kotlin for reference.

#### Version 1.4.1 (Major Update)
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

#### Version 1.3.1 (Bug Fix Update)
- Resolved an issue [#3](https://github.com/techinessoverloaded/progress-dialog/issues/3) where NegativeButton showed up even when not set.

#### Version 1.3.0 (Major Update)
- Added Support for AutoTheming.
- Added IntDef Annotation to Constants. 
- Changed TextView to MaterialButton for NegativeButton.

#### Version 1.2.2 (Feature Update)
- Added Negative Button: This can be used in cases where the User has to be given an option to stop a task through ProgressDialog interface.

#### Version 1.2.1 (Minor Patch)
- Minor Patch to Version 1.2.0

#### Version 1.2.0 (Feature Update)
- Added Setter and Getter for `theme` in order to prevent unnecessary creation of separate objects for each theme.
- Removed some boilerplate code.

#### Version 1.1.1 (Major Update)
- Have added new Constructors and new useful Methods. 
- Have added Javadoc Documentation to each public Method, public Attribute and public Constructor.

Switched to a more semantic Versioning Scheme.

#### Version 1.0.4 (Feature Update)
- Added Dark Theme Support.
- Minor Bug Fixes.

#### Version 1.0.3 (First Release)
- First Release of ProgressDialog Library.
