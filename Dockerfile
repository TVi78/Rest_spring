FROM tomcat:8.5-jdk21
WORKDIR /usr/local/tomcat
COPY task3.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]

