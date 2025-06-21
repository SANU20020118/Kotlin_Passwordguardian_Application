# Password Guardian 🔒

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpack-compose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![ExoPlayer](https://img.shields.io/badge/ExoPlayer-F8F9FA?style=for-the-badge&logo=google-play&logoColor=black)](https://exoplayer.dev/)
[![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/)

## Project Overview

Password Guardian is an Android application designed to help users create and manage strong passwords. It provides real-time feedback on password strength and can generate secure passwords. A unique feature of this app is its dynamic video feedback, which visually represents the strength of the entered password.

## Features

* **Password Strength Calculation:** Analyzes the entered password based on length, presence of uppercase letters, lowercase letters, digits, and special characters.
* **Real-time Strength Indicator:** A visual bar and text update instantly as the user types, indicating if the password is "Weak," "Fair," or "Strong."
* **Secure Password Generation:** Automatically generates strong, random passwords of a specified length.
* **Dynamic Video Feedback:** Displays different video animations (weak, good, strong) in real-time, corresponding to the calculated password strength, providing an intuitive visual cue.
* **Show/Hide Password:** Toggles visibility of the entered password for convenience and security.

## Technologies Used

* **Kotlin:** The primary programming language for Android development.
* **Jetpack Compose:** Modern Android UI toolkit for building native interfaces.
* **ExoPlayer:** A powerful media player library for Android, used for smooth video playback based on password strength.
* **AndroidX Libraries:** Essential support libraries for Android development.

## Project Structure (Key Files)

* `app/src/main/java/com/example/passwordguardian/model/PasswordUtils.kt`: Contains utility functions for calculating password strength and generating secure passwords.
* `app/src/main/java/com/example/passwordguardian/ui/theme/PasswordScreen.kt`: Implements the main UI for the password input, strength indicator, and password generation using Jetpack Compose.
* `app/src/main/java/com/example/passwordguardian/ui/theme/PasswordStrengthVideo.kt`: A Composable function responsible for playing the dynamic video feedback using ExoPlayer based on password strength.
* `app/src/main/java/com/example/passwordguardian/ui/MainActivity.kt`: The main activity that sets up the Jetpack Compose content for `PasswordScreen`.
* `app/src/main/res/raw/`: (You will need to place your video files here) Contains the video assets (`weak.mp4`, `good.mp4`, `strong.mp4`) used for dynamic feedback.

## How to Run

To run this project on your local machine:

1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/your-username/PasswordGuardian.git](https://github.com/your-username/PasswordGuardian.git)
    cd PasswordGuardian
    ```
    (Replace `your-username` and `PasswordGuardian` with your actual GitHub username and repository name after you upload the project.)

2.  **Add Video Assets:**
    * Create a `raw` folder inside `app/src/main/res/` if it doesn't exist.
    * Place your video files named `weak.mp4`, `good.mp4`, and `strong.mp4` into the `app/src/main/res/raw/` directory. These videos are crucial for the app's dynamic feedback feature.

3.  **Open in Android Studio:**
    * Launch Android Studio.
    * Select `File > Open` and navigate to the cloned `PasswordGuardian` project directory.

4.  **Build and Run:**
    * Android Studio will automatically sync the project.
    * Connect an Android device or start an emulator.
    * Click the `Run` button (green triangle icon) in the toolbar to build and deploy the application.

## Contributing

Contributions are welcome! If you find a bug or want to add a new feature, please open an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](https://opensource.org/licenses/MIT) file for details.
