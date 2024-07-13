# Simple Toast Message Library
SimpleToast is a custom Android library for displaying customized toast messages as dialogs. This library allows you to create simple, colored, and image-based messages with various customization options such as text size, dialog size, duration, and animation effects.

## Features
### 1. Customizable Text and Dialog Size
- Adjust the text size and dialog dimensions to fit your needs.

### 2. Background Customization
- **Solid Color Background:** Change the background color of the pop-up to any solid color.

### 3. Text Customization
- **Text Color:** Change the text color to any color you desire.
- **Custom Font:** Apply a custom font to the text.

### 4. Image Integration
- Add an image to the toast message with customizable size.

### 5. Animation Effects
- **Slide-in and Slide-out:** Animate the dialog to slide in and out from the sides.

### 6. Close Button
- A close button to dismiss the dialog

- ### 7. Display Duration
- Set the duration for how long the message should be displayed.


## Installation

To include the Simple Toast Message Library in your project, follow these steps:

### Step 1: Add Maven Repository

Add the following code to the `dependencyResolutionManagement` section in your project's `settings.gradle` file:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
### Step 2: Add Dependency

Add the library dependency to your app module's build.gradle file:

```groovy
dependencies {
    implementation 'com.github.ShirZur:messagelibrary:Tag'
}
```

##Usage
### Simple Toast Message
```groovy
SimpleToastMessage.show(context, "Hello World", 16, Color.WHITE, 200, 3000, true);
```

### Toast Message with Image
```groovy
SimpleToastMessage.showWithImage(context, "Hello World", R.drawable.your_image, 16, 48, Color.WHITE, 200, 3000, true);
```
### Toast Message with Background Color
```groovy
SimpleToastMessage.showWithBackgroundColor(context, "Hello World", Color.RED, 16, Color.WHITE, 200, 3000, true);
```
### Toast Message with Custom Font
```groovy
Typeface customFont = Typeface.createFromAsset(context.getAssets(), "fonts/custom_font.ttf");
SimpleToastMessage.showCustomFont(context, "Hello World", 16, Color.WHITE, customFont, 200, 3000, true);

```

### Display Duration
```groovy
SimpleToastMessage.show(context, "Hello World", 16, Color.WHITE, 200, 5000, true); // 5000 milliseconds = 5 seconds


```

In these examples, context is the current context, message is the text to be displayed,
textSize is the size of the text, textColor is the color of the text, dialogSize is the size of the dialog, dialogDuration is the duration of the dialog in milliseconds, 
and withAnimation indicates whether to show the dialog with animation.
