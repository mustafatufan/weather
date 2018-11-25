# Weather Project (Spring Boot + PostgreSQL + JavaScript)

![screenshot](screenshot.png)

## Applications
### weather-web / WebApplication.java
- [localhost:8080](localhost:8080) serves home page
- [localhost:8080/api/forecast](localhost:8080/api/forecast) serves forecast JSON
- web.properties holds Thymeleaf settings

### weather-scheduler / SchedulerApplication.java 
- ForecastTask.java is triggered by Cron and insert prod. forecast
- scheduler.properties holds Cron & XML URL

### weather-repository / TestApplication.java 
- ForecastRepositoryTest.java inserts mock data
- repository.properties holds DataSource & JPA settings

## Settings
Preferences > Maven > Warnings > Disable "Version is duplicate of parent version" warning

