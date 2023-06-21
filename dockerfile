FROM openjdk:17
WORKDIR /app-java
ARG PORT=6000
ENV PORT=$PORT
ENV NAME_BD=mongo
EXPOSE $PORT
COPY . /app-java
CMD ["./mvnw", "spring-boot:run"]
