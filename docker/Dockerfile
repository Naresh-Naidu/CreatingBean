FROM docker.prod.walmart.com/samstechinfra/alpine-nginx-jdk:0.1.6
VOLUME /tmp
ARG jarFileName
RUN mkdir /app
COPY proxy-intranet-ref-app.conf /etc/nginx/conf.d/proxy.conf
COPY $jarFileName /app/app.jar
RUN sh -c 'touch /app/app.jar'
ENV JAVA_OPTS=""
COPY startup.sh /app/startup.sh
RUN ["chmod", "+x", "/app/startup.sh"]
CMD ["/app/startup.sh"]