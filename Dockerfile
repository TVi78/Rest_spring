FROM tomcat:8.5-jdk21
WORKDIR /usr/local/tomcat
COPY /target/*.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]

