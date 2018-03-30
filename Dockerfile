FROM hypriot/rpi-java

VOLUME /tmp

ADD target/vpb-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 8050

ENTRYPOINT ["java","-Dspring.profiles.active=container","-jar","/app.jar"]
