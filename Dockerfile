FROM eclipse-temurin:17-jdk-jammy
EXPOSE 8080
# Take the war and copy to webapps of tomcat
ADD target/SpringbootTestApp.war SpringbootTestApp.war
ENTRYPOINT [ "java", "-jar", "SpringbootTestApp.war" ]
