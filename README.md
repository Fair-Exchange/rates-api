# rates-api

### Setup

Clone repository

#### Build jar file

```shell
cd rates-api
mvn clean package
```

#### Build docker image
```shell
sudo docker build -t rates-api .
```

#### Run container
```shell
sudo docker run -p 80:80 rates-api
```
