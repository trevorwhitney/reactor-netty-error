This repo recreates an error I saw with reactor-netty

Steps to recreate error:

### Happy Path
1. Run `./gradlew clean plain-netty`
1. The server will boot on port 8080
1. curl the server using mutual TLS `curl -k https://localhost:8080/ --cacert src/main/resources/certs/exporterCA.crt --cert src/main/resources/certs/prometheus.crt --key src/main/resources/certs/prometheus.key`
1. You should get a successful response with status 200 and the text "Hello World"

### Error Case
1. Run `./gradlew clean reactor-netty`
1. The server will boot on port 8080
1. curl the server using mutual TLS `curl -k https://localhost:8080/ --cacert src/main/resources/certs/exporterCA.crt --cert src/main/resources/certs/prometheus.crt --key src/main/resources/certs/prometheus.key`
1. You should get the error `curl: (56) LibreSSL SSL_read: error:1404C10B:SSL routines:ST_OK:wrong version number, errno 0`
