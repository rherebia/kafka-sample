FROM maven:3.6.3-jdk-11-slim

ENV MAVEN_OPTS "-Duser.home=/var/maven"

COPY entrypoint.sh /usr/local/bin/entrypoint.sh
RUN apt-get update && apt-get install dos2unix && dos2unix /usr/local/bin/entrypoint.sh && chmod +x /usr/local/bin/entrypoint.sh

#Start application
WORKDIR /usr/src/mymaven
ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]
CMD ["bash"]