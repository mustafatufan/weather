# Weather
:partly_sunny: Weather is an interview project.

## Introduction
There is an XML weather API accessible at `http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng`

Build a Spring-Boot application consisting of at least two Maven modules.

### Module 1 - background process:
This module has to contain a scheduled method *(with the @Scheduled annotation)* that will consume the weather API and save the results in a PostgreSQL database.

You can ignore any wind/sea data in the API result as this is not important to this task.

The scheduled method should run every 30 minutes.

The module should set up the database schema by itself on startup if one does not exist already. Assume that the application will have superuser access to a PostgreSQL database with the following properties:
```
Address: localhost:5432
Database: weather
Username: weather
Password: weather
```
### Module 2 - web:
This module should serve a simple web page.

The web page should show the user the latest weather forecast *(read from the database)*.

The user should be able to easily look up current weather for their location, by entering the name of the location in a search bar.

Optionally, you can create a third module that would contain code and/or settings common to both applications, so that the two previously mentioned modules can re-use some of the same code.

No limitations are set on the front-end framework, so use whatever framework you want and design the page however you see fit.

The application has to use Maven as the build environment and should be easy to compile and run.

After building the application, please send us the source code for evaluation *(or host it in a git repository)*. Also include a readme file with instructions on how to compile and run the application.
## Applications
![screenshot](screenshot.png)
## Applications
### weather-web / WebApplication.java
- [localhost:8080](localhost:8080) serves home page
- [localhost:8080/api/forecast](localhost:8080/api/forecast) serves forecast JSON
- web.properties holds Thymeleaf settings
### weather-scheduler / SchedulerApplication.java 
- ForecastTask.java is triggered by Cron and insert prod. forecast from [this url](http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng).
- scheduler.properties holds Cron & XML URL
### weather-repository / TestApplication.java 
- ForecastRepositoryTest.java inserts mock data
- repository.properties holds DataSource & JPA settings
## Settings
### Database
The application need a superuser access to a PostgreSQL database with the following properties:
```sh
Address: localhost:5432
Database: weather
Username: weather
Password: weather
```
