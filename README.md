### Running the database as a container.
## Starting a MySQL instance

#### 1. Using Docker Compose
To start the MySQL container using Docker Compose, simply run:
```bash
docker-compose up
```

#### 2. Alternatively, you can start the MySQL container via the terminal using the following command:


```bash
 docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=todo_database -e TZ=UTC -p 3306:3306 -v mysql-data:/var/lib/mysql -d mysql 
 ```
(application properties, user :root)


### Swagger
After running the application you can go to the: http://localhost:8080/swagger-ui.html.