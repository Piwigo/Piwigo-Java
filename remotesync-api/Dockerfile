FROM eclipse-temurin:17-alpine

WORKDIR /opt/app
COPY target/lib /opt/app/lib
COPY target/classes /opt/app

EXPOSE 8080
ENTRYPOINT ["java","-cp","/opt/app:/opt/app/lib/*","org.piwigo.remotesync.api.Main"]
