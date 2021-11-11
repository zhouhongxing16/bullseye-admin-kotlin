
#定义环境变量
ENV TIME_ZONE Asiz/Shanghai
#dockerfile增加命令
RUN \

#安装tzdata安装包

&& apk add --no-cache tzdata \
#设置时区
&& echo "${TIME_ZONE}" > /etc/timezone \
&& ln -sf /usr/share/zoneinfo/${TIME_ZONE} /etc/localtime \
# Docker image for springboot file run
# VERSION 0.0.1
# Author: jeremy
# 基础镜像使用java
FROM java:8
# 作者
MAINTAINER Chris <961860916@qq.com
# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为bullseye.jar
ADD bullseye-admin-kotlin.jar bullseye.jar
# 运行jar包
RUN bash -c 'touch /bullseye.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/bullseye.jar"]