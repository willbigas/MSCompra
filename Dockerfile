FROM maven:3.6.3-openjdk-17 as build

WORKDIR /app

# copy the Project Object Model file
COPY ./pom.xml ./pom.xml

# fetch all dependencies
RUN mvn dependency:go-offline -B

# copy your other files
COPY ./src ./src

# build for release
# NOTE: my-project-* should be replaced with the proper prefix
RUN mvn clean package -Dmaven.test.skip=true && cp target/compra-*.war app.war

# smaller, final base image
FROM openjdk:17

# set deployment directory
WORKDIR /app

# copy over the built artifact from the maven image
COPY --from=build /app/app.war ./app.war

# set the startup command to run your binary
CMD ["java", "-jar", "/app/app.war"]
