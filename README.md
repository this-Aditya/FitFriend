
<p>
  <a href="https://github.com/this-Aditya/SleepTracker/blob/master/LICENSE">
    <img src="https://img.shields.io/badge/License-Apache_2.0-blue.svg" alt="License">
  </a>
  <a href="https://github.com/this-Aditya/SleepTracker/issues">
    <img src="https://img.shields.io/github/issues/this-Aditya/FitFriend.svg" alt="GitHub Issues">
  </a>
  <a href="https://github.com/this-Aditya/SleepTracker/stargazers">
    <img src="https://img.shields.io/github/stars/this-Aditya/FitFriend.svg" alt="GitHub Stars">
  </a>
</p>

# FitFriend 🌟
<br><br>

<img src = "https://github.com/this-Aditya/FitFriend/assets/98681758/70c322c2-9e3b-4091-a0d5-4db5e0367d22" width = "160px">
<img src = "https://github.com/this-Aditya/FitFriend/assets/98681758/90676ab7-f111-4803-91fa-f37955786897" width = "160px">
<img src = "https://github.com/this-Aditya/FitFriend/assets/98681758/fdfa7a87-3763-45be-8a35-c013c05b255f" width = "160px">
<img src = "https://github.com/this-Aditya/FitFriend/assets/98681758/0eee5e78-8951-4102-a231-65242e052c20" width = "160px">
<img src = "https://github.com/this-Aditya/FitFriend/assets/98681758/8e6a054b-2720-4173-a3b4-c830b11d3d77" width = "160px">
<br><br>

#### FitFriend is a powerful wellness app with amazing Android device features. It incorporates authentication from Google Firebase and utilizes the Google Sleep API and Google Activity Recognition API to monitor sleep and activity data continuously. The app also integrates a robust and well-tested Spring Boot API, providing access to yogas, pranayams, and meditations.

#### Test-driven development was followed for both the Spring Boot API and the Android application. FitFriend is licensed under Apache License 2.0.

## Features (Android)

- **Junit 4** for testing
- **Dependency Injection** by Dagger Hilt
- **Coroutines** support
- **Room** for local data storage
- **Foreground services** to seamlessly track sleep and activity data
- **Google Activity Recognition API**: Tracks user activity data
- **Google Sleep API**: Tracks user sleep data, including start time, duration, and confidence
- **Broadcast Receivers**: For receiving event callbacks
- **Alarm Manager**: For scheduling the daily yoga time
- **Retrofit**: Fetches data from the FitFriend API
- **Notification Support**: Updates users about events
- [**Robinhood Spark Adapter**](https://github.com/robinhood/spark): Displays sleep data graphically
- **Diffutils**: Differentiates between data to update
- **ViewModels and LiveData**: Provides more control
- **SharedPreferences**: Stores the state of the app

## [FitFriend-API](https://github.com/this-Aditya/FitFriend/tree/main/fitfriend-api)

FitFriend-API is a Spring Boot REST API built with Kotlin. It offers endpoints for retrieving asanas, meditations, and pranayamas (both by ID and name), as well as for posting, deleting, and updating exercises. The API includes both unit and integration tests.

### Features (FitFriend-API)

- **Language**: Kotlin
- **Test Driven Development**: JUnit 5
- **Mockk**: Mocking library for testing
- **MockMvc**: Framework for testing controllers and endpoints

FitFriend-API is deployed on Render and can be accessed at [https://fitfriend.onrender.com/](https://fitfriend.onrender.com/). For more information, please refer to the [API documentation](https://github.com/this-Aditya/FitFriend/blob/main/fitfriend-api/README.md). Apologies, for the initial loading time on this API BASE-URL, we are working on upgrading our servers.


## Using the FitFriend API locally with in Android

To use the FitFriend API from your Android application while running the API on your laptop's localhost, you need to follow these steps:

1. Make sure your Android device and laptop are connected to the same network (e.g., Wi-Fi network).

2. Run the FitFriend API:
   - Open the terminal or command prompt.
   - Navigate to the `fitfriend-api` directory.
   - Run the appropriate command to start the Spring Boot API (e.g., `./gradlew build`).

3. Find the IP address of your laptop on the network:
   - On Windows: Open the command prompt and run the command `ipconfig`. Look for the IPv4 Address under the network adapter connected to the network.
   - On macOS or Linux: Open the terminal and run the command `ifconfig`. Look for the IP address under the network adapter connected to the network.

4. Update the API base URL in your Android application code:
   - Open this Android project in your preferred development environment.
   - Update the base url in this directory `app/src/main/java/com/aditya/fitfriend_android/utils/Utils.kt`
   - Replace the existing base URL with the IP address of your laptop, followed by the port number and API endpoint path. For example:
     ```kotlin
     val BASE_URL = "http://192.168.0.100:8080/api/"
     ```

5. Build and run your Android application on your device.

6. Your Android application should now be able to communicate with the FitFriend API running on your laptop's localhost. Ensure that the API endpoints are correctly defined and accessible.

**Note:** If you're using a firewall or antivirus software on your laptop, make sure to allow incoming connections to the API port (e.g., 8080) to ensure the Android device can communicate with the API.

## APK Availability

The APK file for the FitFriend application can be found [**here**](https://github.com/this-Aditya/FitFriend/blob/main/data/app-debug.apk).

## Common Errors and Solutions

- **Error: `CLEARTEXT communication to 192.168.1.33 not permitted by network security policy`**
  - Solution: If you are using HTTP instead of HTTPS in your URL, add the following line to the `AndroidManifest.xml` file within the `<application>` tag:
    ```xml
    android:usesCleartextTraffic="true"
    ```
    This should solve the problem and allow communication with the API.

## Contributing

If you wish to contribute to FitFriend and add new features or improvements, you can follow these steps:

1. Clone this repository to your local machine: 
git clone [https://github.com/this-Aditya/FitFriend.git](https://github.com/this-Aditya/FitFriend.git)
 
2. Create a new branch for your feature or bug fix:

3. Make your changes and add, commit, and push them to your forked repository:

4. Open a pull request on the original FitFriend repository, detailing your changes and the purpose of your pull request. 

5. We will review your changes and merge them if they align with the project's goals.

## License

FitFriend is released under the Apache License 2.0.
