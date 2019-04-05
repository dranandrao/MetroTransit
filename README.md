# MetroTransit

## Problem Statement

In a language of your choice, write a program which will tell you how long it is until the next bus on �BUS ROUTE� leaving from �BUS STOP NAME� going �DIRECTION� using the api defined at http://svc.metrotransit.org/ �BUS ROUTE� will be a substring of the bus route name which is only in one bus route �BUS STOP NAME� will be a substring of the bus stop name which is only in one bus stop on that route �DIRECTION� will be �north� �east� �west� or �south�.

## Solution

This project is built using J2EE frameworks i.e., Servlets,JSP. It uses HttpURLConnection for HTTP connection with the API and GSON for parsing JSON response.

## Prerequisites

This project requires Java, Apache-Tomcat, Eclipse IDE and GSON.jar file.


## Installing 

This part explains the installing the prerequisites.

* [Java](https://www.java.com/en/download/) - Language used in the project.
* [Apache-Tomcat](https://tomcat.apache.org/download-80.cgi) - Server to host the application.
* [Eclipse](https://www.eclipse.org/downloads/) - IDE to run the application.
* [GSON](http://www.java2s.com/Code/Jar/g/Downloadgson222jar.htm) - Parse the JSON response from server.

## Environment Setup

This part explains the environment required to host application.

* Set the PATH and JAVA_HOME environment variables in both Windows and Linux Machines. [For help (https://www.javatpoint.com/how-to-set-path-in-java).
* Attach the server to the eclipse, import the cloned or downloaded project into the workspace.

## Running

Extract the both eclipse and gson zip files.Add the GSON.jar into the /WebContent/WEN-INF/lib folder if the GSON.jar file is not available. Run the application and select the server to be hosted.Welcome page is displayed in eclipse browser.Feed in the Route, from and direction into the page, submit the form.If any error or server down, error messages are displayed in the browser.If no errors the the arrival time is displayed based on scheduled time/real time departures.




