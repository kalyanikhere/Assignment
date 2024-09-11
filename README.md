
# Login test automation
# Test execution steps

1. Install prerequisites :
- check whether Java JDK version is installed, ensure that it is install 
  properly.
- Download Selenium WebDriver for your browser.
- create a project of Maven or Java.

2. Set up the project :
- Download and set up appropriate WebDriver (e.g. ChromeDriver, EdgeDriver).
- Add Selenium and TestNG dependences for Maven project to pom.xml file.

3. Write test scripts :
- Create the test script for successful login and unsuccessful login.

4.Run tests :
- with the help of maven run the test (if use maven)
- or run the test with the help of your Integrated Development Environment(IDEA/Eclipse).

5. View Reports :
- After execution, check the output for TestNG report 
- or view the extend Report in the folder.

# Assumptions :
- The web application is working properly and login page will be easily identified.
- also with the help of valid credentials user can log in successfully to the application.
- valid and invalid credentials are used for testing purpose.

# Additional Information
- Limitations : the scripts are depends on the UI structure and specific element identity.
- Challenges : Web-element dynamically changes in UI, so it might require changes in the locators.
- Improvement :Add frameworks like data-driven testing to test multiple testcases and also use page object module better maintainability. 
