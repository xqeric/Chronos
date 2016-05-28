sudo docker run -d -p 2181:2181 -p 9092:9092 -v /sharefolder:/tmp --env ADVERTISED_HOST='192.168.1.112' --env ADVERTISED_PORT=9092 spotify/kafka
