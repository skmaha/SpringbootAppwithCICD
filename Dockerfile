FROM eclipse-temurin:17-jdk-jammy
EXPOSE 8080
# Take the war and copy to webapps of tomcat
ADD target/sbtestapp.war sbtestapp.war
ENTRYPOINT [ "java", "-jar", "sbtestapp.war" ]
