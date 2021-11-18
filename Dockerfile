FROM openjdk:8
EXPOSE 8087
ADD /target/timesheet-1.0.war timesheet-1.0.war
ENTRYPOINT ["java","-jar","timesheet-1.0.war"]



