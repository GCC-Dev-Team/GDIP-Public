# 使用基于 Ubuntu 20.04 的官方镜像
FROM ubuntu:20.04

# 更新apt源并安装MySQL、Redis、MongoDB和Minio
RUN apt-get update && \
    DEBIAN_FRONTEND="noninteractive" apt-get install -y mysql-server redis-server mongodb minio && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# 设置MySQL的root用户密码（注意这个命令会在安装MySQL时执行交互操作）
RUN echo "mysql-server mysql-server/root_password password abC123456@" | debconf-set-selections && \
    echo "mysql-server mysql-server/root_password_again password abC123456@" | debconf-set-selections

# 设置Redis密码
RUN sed -i 's/^requirepass.*$/requirepass abC123456@/' /etc/redis/redis.conf

# 开放MySQL、Redis和Minio远程端口
EXPOSE 3306 6379 9000

# 设置MongoDB用户名和密码
RUN mongo admin --eval 'db.createUser({user: "xiaoli", pwd: "abC123456@", roles: ["userAdminAnyDatabase"]});'

# 拷贝初始化SQL脚本到容器中
COPY init.sql /docker-entrypoint-initdb.d/init.sql

# 创建工作目录
WORKDIR /app

# 默认命令为启动MySQL、Redis、MongoDB和Minio服务
CMD ["sh", "-c", "mysqld --user=root --datadir=/var/lib/mysql --port=3306 --bind-address=0.0.0.0 && redis-server /etc/redis/redis.conf && mongod --bind_ip_all && minio server /data"]
