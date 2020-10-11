FROM openjdk:8-jdk-alpine

ARG JAR_FILE=zipsearch.jar

ARG ZIP_FILE=foc-slack-export.zip
ARG JAR2_FILE=jackson-annotations-2.11.3.jar
ARG JAR3_FILE=jackson-core-2.11.3.jar
ARG JAR4_FILE=jackson-databind-2.11.3.jar
# cd /usr/local/runme
WORKDIR /usr/local/runme

COPY ${JAR_FILE} zipsearch.jar
COPY ${JAR2_FILE} jackson-annotations-2.11.3.jar
COPY ${JAR3_FILE} jackson-core-2.11.3.jar
COPY ${JAR4_FILE} jackson-databind-2.11.3.jar
COPY ${ZIP_FILE} foc-slack-export.zip

# java -jar /usr/local/runme/zipsearch.jar
ENTRYPOINT ["java","-jar","zipsearch.jar"]
CMD ["-file","foc-slack-export.zip","-word",""I'm not sure if"","-size","15","-first","5"]
