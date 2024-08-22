# RickAndMorty

## Introduction

RickAndMorty is an application build with Kotlin (XML) that consume REST API from [Rick And Morty API](https://rickandmortyapi.com/documentation). This project implemented with Clean Architecture with MVVM (Model-View-ViewModel) pattern.

## Table of Content

- [Introduction](#introduction)
- [Features](#features)
- [Libraries](#libraries)
- [Project Structure](#project-structure)
  - [App Module](#app-module)
- [APK Link](#apk-link)

## Features

- List of character
- Detail character
- Favorite character (include show all favorite, add/remove favorite from detail page)
- Search character

## Libraries

- Kotlin (1.9.0)
- Koin as Dependency Injection
- Coil
- Retrofit & OkHttp3 for network client
- Logging Interceptor
- Room Database
- Android Navigation (include SafeArgs)
- Coroutine
- Mockito

## Project Structure

### App Module

- **`base`**
- **`data`**
  - **`character`**
    - **`local`**
    - **`model`**
    - **`remote`**
  - **`lib`**
- **`di`**
  - **`feature`**
- **`domain`**
  - **`character`**
    - **`mapper`**
    - **`model`**
- **`presentation`**
  - **`detail`**
  - **`favorite`**
  - **`home`**
  - **`search`**
  - **`splash`**
- **`utils`**
  - **`ext`**

## APK Link

[Google Drive](https://drive.google.com/file/d/1wy8h6lr8bpgqQy4x9R-XymUuNJleGiR6/view?usp=drive_link)
