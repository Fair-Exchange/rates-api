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
docker build -t rates-api .
```

#### Run container
```shell
docker run -p 8087:80 rates-api
```
