FROM gradle:7.2-jdk17 as builder
COPY . /usr/myapp
WORKDIR /usr/myapp
COPY . .

RUN gradle clean build --no-daemon -P docker -DskipTests
RUN ls -la /usr/myapp/build/libs/

FROM gradle:jdk17 as server
ARG JAVA_PARAM="-Xms256M -Xmx1024M -XX:+UseG1GC"
ARG APP_PATH=/opt/app/

ENV JAVA_PARAM=$JAVA_PARAM
WORKDIR /opt/app
COPY --from=builder /usr/myapp/build/libs/*.jar /opt/app/app.jar

RUN chmod 777 /opt/app/app.jar
ENV PATH=$PATH:/opt/app
EXPOSE 8080
CMD ["sh", "-c", "java ${JAVA_PARAM} -Dserver.port=8080 -jar /opt/app/app.jar"]
