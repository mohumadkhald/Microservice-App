# microservice-app
Microservice-App use security keyclock generate jwt token have product service with mongo db have order service with mariadb and inventory with maria db and have notification use kafka discovery-server have user and password 
first create container of keyclock 
docker run -p --name keyclock 8181:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:24.0.3 start-dev
and create realm and client_id after enter http://localhost:8181
after create realm and client
edit in application.properites of gateway 
spring.security.oauth2.resourceserver.jwt.issuer-uri=the uri after create realm
get from http://localhost:8181/realms/spring-boot-full-project-relm/.well-known/openid-configuration
create zookeeper and kafka-broker from docker-compose for notification 
and run zipkin docker for tracing endpoint
http://127.0.0.1:9411/zipkin/

i make pathMatchers("/eureka/**","/api") any request from api if need try auth with keyclock remove api from and make this pathMatchers("/eureka/**")
and remove comment of oauth2ResourceServer from gateway/src/../config/SecurityConfig 

api route for product http://localhost:8080/api/product

api route for order http://localhost:8080/api/order



