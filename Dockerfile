FROM java:8
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/temperatures-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ADD deployment.sh deployment.sh
RUN bash -c 'chmod +x /deployment.sh'
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["/bin/bash", "/deployment.sh"]