[![Build Status](https://travis-ci.org/cryptomator/native-functions.svg?branch=develop)](https://travis-ci.org/cryptomator/native-functions)

# Cryptomator's Foreign Function Interface

This library contains both the Java part as well as the native libraries required to fulfill certain functionality that isn't available directly from Java.

# Building on Windows

## Requirements

* Windows 10
* mingw 8.1.0 (POSIX, SEH) (run `choco install mingw`)
* OpenJDK 11
* Maven 3

## Building

Navigate to the JNI directory and run `mvn clean install`.


# Building on macOS

## Requirements

* macOS 10.13+
* XCode Command Line Tools (run `xcode-select --install`)
* OpenJDK 11
* Maven 3

## Building

Navigate to the JNI directory and run `mvn clean install`.
