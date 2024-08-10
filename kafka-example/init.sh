docker exec -it kafka kafka-topics --create --topic employee-mail-notifications --partitions 3 --replication-factor 1 --if-not-exists --bootstrap-server localhost:9092
docker exec -it kafka kafka-topics --create --topic employee-list-mail-notifications --partitions 3 --replication-factor 1 --if-not-exists --bootstrap-server localhost:9092
