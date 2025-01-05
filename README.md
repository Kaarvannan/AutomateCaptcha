# Captcha Automation Project

## Overview
For Captcha handling, refer to the `src/test/java/Pages/Signin.java` class (`extractText` function).

## Prerequisites
Before using this code, ensure the following tool is installed:

- **Tesseract-OCR**: An essential OCR (Optical Character Recognition) tool.

## Installation Steps

1. **Install Tesseract-OCR**
   - Download and install Tesseract-OCR from the [Tesseract GitHub repository](https://github.com/tesseract-ocr/tesseract).

2. **Add Tess4J Dependency**
   - Include the following dependency in your `pom.xml` file to integrate Tess4J:

   ```xml
   <!-- Tess4J Dependency -->
   <dependency>
       <groupId>net.sourceforge.tess4j</groupId>
       <artifactId>tess4j</artifactId>
       <version>5.4.0</version>
   </dependency>
