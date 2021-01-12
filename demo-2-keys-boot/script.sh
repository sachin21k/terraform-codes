#!/bin/bash

# sleep until instance is ready
until [[ -f /var/lib/cloud/instance/boot-finished ]]; do
  sleep 1
done

# install nginx
apt-get update
apt-get -y install nginx

# make sure nginx is started
echo "<h1>Welcome to SAC nginx </h1>" >  /var/www/html/index.html
service nginx start
