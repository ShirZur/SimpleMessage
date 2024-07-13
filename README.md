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


## Installation

To include the Simple Toast Message Library in your project, follow these steps:

### Step 1: Add Maven Repository

Add the following code to the `dependencyResolutionManagement` section in your project's `settings.gradle` file:

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
