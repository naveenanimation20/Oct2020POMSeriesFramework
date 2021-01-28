FROM maven:3.6.0-jdk-8-alpine

COPY src /home/SeleniumTestFramework/src

COPY pom.xml /home/SeleniumTestFramework

RUN mvn -f /home/SeleniumTestFramework/pom.xml clean test -DskipTests=true