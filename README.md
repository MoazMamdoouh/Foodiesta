# Foodiesta

Welcome to **Foodiesta**! This app allows users to explore various meals, search by ingredients, countries, or categories, and interact with meal details. Users can also register, log in, and manage their favorite meals and calendar using Firebase. Additionally, the app supports **Offline Mode**, allowing users to continue using the app even without an internet connection.

## Features

- **Splash Screen**: Displays a welcome screen when the app is opened, leading to either the login screen or home screen.
  
- **Login and Registration**:
  - Users can log in or register for an account.
  - **Guest Mode**: If a user joins as a guest, they can still explore the home screen and view meal details.
  - Once registered, users can access their profile, which shows their name and a custom icon.

- **Home Screen**:
  - Displays cards with random meals.
  - Clicking on a card navigates to a details screen with meal information and a video at the bottom.

- **Details Screen**:
  - Shows detailed information about the meal.
  - Includes a YouTube video related to the meal at the end.

- **Search Functionality**:
  - Users can search for meals by ingredients, countries, or categories.
  - All meal cards are clickable and lead to the details screen.

- **Favorites**:
  - Users can add meals to their favorites.
  - The favorite meals are stored in Firebase Firestore and can be accessed from the profile.
  - Favorite meals are also saved locally in the Room database for offline access.

- **Calendar**:
  - Users can add meals to a specific date and manage them in the calendar.
  - Calendar events are stored in Firebase Firestore and Room, allowing users to view them offline.

- **Offline Mode**:
  - The app supports offline mode by storing meal data (random meals, favorites, calendar events) locally using **Room Database**.
  - When the app detects no internet connection, it will fetch meal data from the local database (Room) instead of the network.
  - Once the device is back online, the app syncs the local data with Firebase Firestore.

- **Profile Management**:
  - Users can view their profile, which includes their name and a custom icon.
  - Users can upload their favorite meals and calendar meals to Firebase when online.
  
- **Logout**:
  - Users can log out of the app, which will clear their session.

- **MVP Architecture**:
  - The app follows the **Model-View-Presenter (MVP)** architecture pattern for better separation of concerns and easier testing.

- **Retrofit**:
  - Retrofit is used for networking and interacting with APIs to fetch meal data.

- **Room Database**:
  - Used for local storage of meals and user-related data for offline access.
  - Local storage includes meal details, favorites, and calendar events.

- **Firebase Firestore**:
  - Firebase Firestore is used to store user data, including favorites and calendar meals.

- **Firebase Authentication**: For handling user registration and login.

- **YouTube API**: Embedded YouTube videos to provide meal-related videos on the details screen.

## Technologies Used

- **MVP Architecture**: Organizes code into three components: Model, View, and Presenter.
  
- **Retrofit**: Simplifies network calls to external APIs (e.g., fetching meal data).

- **Room Database**: Provides offline persistence for storing meal data and other local information.

- **Firebase Firestore**: Used for cloud-based storage of user-related data, such as profiles, favorites, and calendar events.

- **Firebase Authentication**: For managing user authentication.

- **YouTube API**: Embedded YouTube videos related to meals.

## App Flow

1. **Splash Screen**:
   - Displays the app logo and then navigates to the login screen.

2. **Login/Registration Screen**:
   - Users can log in with their credentials or register for a new account.
   - Option to **Join as Guest**: Allows users to access the home screen without creating an account.

3. **Home Screen**:
   - Displays random meal cards.
   - Clicking on any card opens the **Details Screen**.
   - If the app is offline, it will show previously cached meal data stored in the Room database.

4. **Details Screen**:
   - Displays detailed information about a selected meal.
   - At the bottom, there is a YouTube video related to the meal.
   
5. **Search Screen**:
   - Search meals by ingredients, countries, or categories.
   - Clicking on a search result navigates to the **Details Screen**.
   - If the app is offline, it will search through locally stored meals.

6. **Favorites Screen**:
   - Displays a list of meals marked as favorites.
   - Users can delete meals from their favorites list.
   - The favorite meals are saved both locally (Room) and remotely (Firestore).

7. **Calendar Screen**:
   - Displays meals added to a specific date.
   - Users can view and manage their meals scheduled for the day.
   - Calendar events are saved both locally (Room) and remotely (Firestore).

8. **Profile**:
   - After logging in, users can see their profile with their name and a custom icon.
   - Profile also includes a list of favorite meals and calendar events that have been saved to Firebase.
   - Users can upload their favorite meals and calendar meals to Firebase when online.

9. **Logout**:
   - Allows users to log out of their account and return to the splash screen.

