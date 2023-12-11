# Stage 1: Build stage
FROM maven:3.9.6-amazoncorretto-17 AS build
LABEL MAINTAINER = "Vinicius Ribeiro"
# create app folder for sources
RUN mkdir -p /build

WORKDIR /build

COPY pom.xml /build
#Download all required dependencies into one layer
RUN mvn -B dependency:resolve dependency:resolve-plugins
#Copy source code
COPY src /build/src
# Build application
RUN mvn package -DskipTests



# Used to analyze dependencies bacause jdeps from JDK 17 has a bug
# https://stackoverflow.com/questions/69943899/jdeps-cant-print-module-deps-due-to-a-multireleaseexception
FROM amazoncorretto:21.0.1-alpine as deps

# required for strip-debug to work
RUN apk add --no-cache binutils

COPY --from=build /build/target/*-SNAPSHOT.jar app.jar

RUN unzip ./app.jar && \
    $JAVA_HOME/bin/jdeps \
    --ignore-missing-deps \
    --print-module-deps \
    -q \
    --recursive \
    --multi-release 17 \
    --class-path="./BOOT-INF/lib/*" \
    --module-path="./BOOT-INF/lib/*" \
    ./app.jar > /deps.info

# Build small JRE image
RUN $JAVA_HOME/bin/jlink \
    --verbose \
    --add-modules $(cat /deps.info) \
    --strip-debug \
    --no-man-pages \
    --no-header-files \
    --compress=2 \
    --output /customjre

# main app image
FROM alpine:latest

ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"
ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en' LC_ALL='en_US.UTF-8' TZ="America/Sao_Paulo"
ENV JAVA_OPTS="-XX:MaxRAMPercentage=80 -XX:MinRAMPercentage=50 \
               -XX:+OptimizeStringConcat \
               -XX:+TieredCompilation \
               -XX:ReservedCodeCacheSize=256m"
# copy JRE from the base image
COPY --from=deps /customjre $JAVA_HOME

# Add app user
ARG APPLICATION_USER=appuser
RUN adduser --no-create-home -u 1000 -D $APPLICATION_USER

# Configure working directory
RUN mkdir /app && \
    chown -R $APPLICATION_USER /app

USER 1000

COPY --from=build --chown=1000:1000 /build/target/*-SNAPSHOT.jar /app/app.jar
WORKDIR /app

EXPOSE 8080

ENTRYPOINT /jre/bin/java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar
