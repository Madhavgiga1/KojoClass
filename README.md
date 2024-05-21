![firstkojo](https://github.com/Madhavgiga1/KojoClass/assets/77728555/2ab61a1a-8a15-4919-bdd7-69e0a3ce5ed1)

# Classroom Management App

Welcome to the Classroom Management App! This full-stack Android application is designed to streamline educational management for teachers and students.

## Overview

The app offers dedicated interfaces for both teachers and students:

### Students
- **Assignments**: View and submit assignments.
- **Quizzes**: Participate in quizzes with real-time proctoring.
- **Announcements**: Stay updated with class announcements.
- **Study Materials**: Access course-related materials.

![stufirstkojo](https://github.com/Madhavgiga1/KojoClass/assets/77728555/036e16f6-cda4-42d9-bf7b-5b8891b99b2c)
![stu2kojo](https://github.com/Madhavgiga1/KojoClass/assets/77728555/a98c6f1b-e7ba-4cad-ad11-eddebfee5760)

### Teachers
- **Class Management**: Oversee all classes being taught.
- **Assignments & Quizzes**: Upload and manage assignments and quizzes.
- **Announcements**: Post announcements for the class.
- **Performance Monitoring**: Track student performance on quizzes and assignments.

![secondteachkojo](https://github.com/Madhavgiga1/KojoClass/assets/77728555/6853f34f-36a2-4e40-bfa5-a361812015e8)
![teach2kojo](https://github.com/Madhavgiga1/KojoClass/assets/77728555/23690bfc-bd70-4f3d-ac38-e7ae800d2972)

## Features

### For Students
- **Assignments**: View and submit assignments.
- **Quizzes**: Participate in quizzes with real-time proctoring.
- **Announcements**: Stay updated with class announcements.
- **Study Materials**: Access course-related materials.

### For Teachers
- **Class Management**: Oversee all classes being taught.
- **Assignments & Quizzes**: Upload and manage assignments and quizzes.
- **Announcements**: Post announcements for the class.
- **Performance Monitoring**: Track student performance on quizzes and assignments.

## Technical Overview

### Frontend
- **Architecture**: MVVM (Model-View-ViewModel) architecture for a scalable and maintainable codebase.
- **Technologies**: Kotlin, LiveData, ViewModel, Jetpack Navigation Component, Retrofit, Room, Hilt for Dependency Injection.
- **UI**: Built using modern Android design principles with Activities, Fragments, and a clean, intuitive interface.

### Backend
- **Primary Backend**: Django REST Framework with PostgreSQL.
  - **Features**: User authentication, API endpoints for assignments, quizzes, announcements, and student performance.
  - **Technologies**: Docker, Docker Compose, ViewSets, APIViews, Serializers.
- **Secondary Backend**: Firebase Realtime Database.
  - **Usage**: Announcements and study materials sharing.
  - **Benefits**: Real-time updates with event listeners and LiveData integration.

## Future Enhancements
- **Machine Learning**: Integrate ML models for quiz proctoring.
- **Deployment**: Deploy the backend REST API on AWS Cloud.
- **Distribution**: Upload the app to the Google Play Store.
- **Cross-Platform Support**: Integrate Kotlin Multiplatform (KMP) to support iOS
