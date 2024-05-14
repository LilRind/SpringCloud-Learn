# 学习微服务springcloud的相关知识的简单实践项目

## Mybatis-plus
提升开发效率，编写一些通用的开发模板，
注意不侵入业务原则

## Docker
成功安装docker

![image](https://github.com/LilRind/SpringCloud-Learn/assets/66501637/72b87ef2-55a1-4bbc-be1d-0b1577ef3ad9)

成功安装mysql

docker run 命令解读

![image](https://github.com/LilRind/SpringCloud-Learn/assets/66501637/e1e029d6-811d-4311-8551-bbde713c8a92)

### docker 基础命令

可以去DockerHub查看nginx镜像仓库及相关信息

拉取Nginx镜像

docker pull nginx

查看镜像

docker images

保存镜像为压缩包，最新版：

docker save -o nginx.tar nginx:latest

删除镜像：

docker rmi nginx:latest

读取保存的镜像压缩包：

docker load -i nginx.tar  

创建并允许Nginx容器

docker run -d --name nginx -p 80:80 nginx

查看运行中容器

docker ps

也可以加格式化方式访问，格式会更加清爽

docker ps --format "table {{.ID}}\t{{.Image}}\t{{.Ports}}\t{{.Status}}\t{{.Names}}"

访问网页，地址：http://虚拟机地址

停止容器

docker stop nginx

查看所有容器，正在运行的容器：

docker ps -a --format "table {{.ID}}\t{{.Image}}\t{{.Ports}}\t{{.Status}}\t{{.Names}}" 

全部容器，包括未运行：

docker ps -a --format "table {{.ID}}\t{{.Image}}\t{{.Ports}}\t{{.Status}}\t{{.Names}}" -a


显示当前日志log，不能实时显示日志：

docker log nginx

实时显示日志log：

docker log -f nginx

再次启动nginx容器

docker start nginx

再次查看容器

docker ps --format "table {{.ID}}\t{{.Image}}\t{{.Ports}}\t{{.Status}}\t{{.Names}}"

查看容器详细信息

docker inspect nginx

### 注意容器的内部是模拟的操作系统，类似于虚拟机Linux

进入容器,查看容器内目录

docker exec -it nginx bash

可以进入MySQL所在的容器并连接MySQL

docker exec -it mysql mysql -uroot -p

也可以先进入MySql所在的容器，再链接MySQL

docker exec -it mysql bash

mysql -uroot -p

删除容器

docker rm nginx

发现无法删除，因为容器运行中，强制删除容器

docker rm -f nginx

退出容器

exit


## Nacos

## OpenFeign
