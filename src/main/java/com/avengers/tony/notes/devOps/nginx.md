# Nginx

### Install
```bash
sudo apt-get install nginx -y
```

### Nginx config
```bash
#Edit  /etc/nginx/sites-enabled/default
server {
    listen 80 default_server;
    listen [::]:80 default_server;
    root /var/www/html;
    index index.html index.htm index.nginx-debian.html;

    server_name localhost;
    proxy_send_timeout 300s;
    proxy_read_timeout 600s;

    location / {
        proxy_pass http://localhost:9080;
    }
    location /api/IT {
        proxy_pass http://localhost:8083/api/IT;
    }
    location /api/MOBILE {
        proxy_pass http://localhost:8082/api/MOBILE;
    }
    location /api/AU {
        proxy_pass http://localhost:8081/api/AU;
    }
    location /api/BOLT {
        proxy_pass http://localhost:8080/api/BOLT;
    }
}
```
```bash
server {
    listen 80 default_server;
    listen [::]:80 default_server;
    root /var/www/html;

    index index.html index.htm index.nginx-debian.html;

    server_name localhost;

    location / {
            proxy_pass http://localhost:9080;
    }
    location /qaLabGrid/grid/admin/GetData {
            proxy_pass http://10.32.98.2:4444/grid/admin/GetData;
    }
    location /ecgCloudGrid2/grid/admin/NodeStatusServlet {
            proxy_pass http://10.41.159.4/grid/admin/NodeStatusServlet;
    }
    location /ecgCloudGrid3/grid/admin/NodeStatusServlet {
            proxy_pass http://10.41.159.25/grid/admin/NodeStatusServlet;
    }
}
```

### Modify the gzip config
Edit  /etc/nginx/nginx.conf.<br>
Uncomment all the  "Gzip Settings".<br>
```bash
##
# Gzip Settings
##
gzip on;

gzip_disable "msie6";
gzip_vary on;
gzip_proxied any;
gzip_comp_level 6;
gzip_buffers 16 8k;
gzip_http_version 1.1;
gzip_types text/plain text/css application/json application/javascript text/xml application/xml application/xml+rss text/javascript;
```
```bash
# Start the nginx
sudo service nginx start
sudo service nginx restart
sudo service nginx reload     #if the nginx is already start, please reload
```