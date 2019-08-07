## Database
In this project I am using PostgreSQL, but since we are utilizing Liquibase, the database is agnostic.
You can choose which database you prefer.

### How to

```docker pull postgres```

Or download Docker PostgreSQL at: https://hub.docker.com/_/postgres.

Then for the first time, create the container with the following command.
```- docker run --name DB -e "POSTGRES_PASSWORD=postgres" -p 5432:5432 -d postgres```

The other times you just need to start the container with the following command:

``` docker start DB ```

After you start the database container you just need to create the "shortener" database.

``` create database shortener ```

When you bootRun the application, Spring Liquibase will read db.changelog-master.yaml and create
the necessary table and columns.

PS: I know you do not like the yaml syntax, sorry about that rsrs.