# SS Setup
```bash
sudo apt-get update && sudo apt-get upgrade
sudo apt-get install python-pip
pip install git+https://github.com/shadowsocks/shadowsocks.git@master

touch config.json
vim config.json

# test ssserver
sudo ssserver -c `pwd`/config.json
ps -ef | grep python
kill -9 python-pip

# start ssserver
nohup ssserver -c `pwd`/config.json>ss.log &
tail -f ss.log
```
```bash
config.json
{        
    "server":"10.140.0.2",    # internal ip
    "server_port":3389,
    "local_address": "127.0.0.1",
    "local_port":1080,
    "password":"passwordpassword",
    "timeout":300,
    "method":"aes-256-cfb",
    "fast_open": false
}
```