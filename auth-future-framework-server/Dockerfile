FROM openjdk:17-jdk
COPY target/auth-future-framework-server-1.0.0.jar auth-future-framework-server-1.0.0.jar
VOLUME /tmp
EXPOSE 9093
# 暴露应用运行的端口
ENV JAVA_OPTS="\
-Xmn512M \
-Xms1024M \
-Xmx1024M \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=/errorlog/demo-docker-springboot-heapdump.hprof"
ENTRYPOINT java ${JAVA_OPTS} -jar -Duser.timezone=GMT+8  auth-future-framework-server-1.0.0.jar