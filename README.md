Captcha Automation Project
Overview
For the Captcha handling code, refer to the SignIn classc(extractText function) in the Pages package.

Prerequisites
Before using this code, ensure you have the following installed:

Tesseract-OCR: A required OCR (Optical Character Recognition) tool.
Installation
Install Tesseract-OCR

Download and install Tesseract-OCR from Tesseract GitHub.
Add Tess4J Dependency

Include the following dependency in your pom.xml file to integrate Tess4J:
xml
Copy code
<!-- Tess4J Dependency -->
<dependency>
    <groupId>net.sourceforge.tess4j</groupId>
    <artifactId>tess4j</artifactId>
    <version>5.4.0</version>
</dependency>
