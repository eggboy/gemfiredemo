# Spring Data GemFire Demo
============================

This project provides Spring Boot app to demonstrate how Spring Data Gemfire works. It's desinged to work locally or on Pivotal Cloud Foundry. 

# Quickstart

gfsh start locator --name=locator1 \
   --enable-cluster-configuration=false  \
  --dir=$GEMFIRE_SERVER_DIR/locator1 \
  --port=15001 \
  --log-level=config \
  --J=-Dgemfire.jmx-manager=true \
  --J=-Xms256m \
  --J=-Xmx256m \
  --J=-Dcom.sun.management.jmxremote \
  --J=-Dcom.sun.management.jmxremote.port=15666 \
  --J=-Dcom.sun.management.jmxremote.ssl=false \
  --J=-Dcom.sun.management.jmxremote.authenticate=false \
  --J=-Dcom.sun.management.jmxremote.local.only=false
  
  gfsh start server \
   --name=server1 \
   --use-cluster-configuration=false \
   --server-port=0 \
   --dir=$GEMFIRE_SERVER_DIR/server1 \
   --locators=localhost[15001] \
   --J=-Xms256m \
   --J=-Xmx256m \
   --properties-file=$CONF_DIR/gemfire.properties \
   --spring-xml-location=$GEMFIRE_SERVER_DIR/server-cache.xml

* mvn spring-boot:run

# Run on Cloud Foundry

* cf push -f manifest.yml

# Quick Test

curl -X POST -H "Content-Type:application/json" -d '{
    "customerId": 3,
    "firstName": "Jay",
    "lastName": "Lee",
    "emailAddress": "jaylee@pivotal.io"
  }' http://localhost:8080/customers

curl -X GET localhost:8080/customers



