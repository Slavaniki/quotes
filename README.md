# QuotesApp

The API allow:

Creation of a user account. 
User properties are name, email, password and date of creation;

Addition, viewing (including a method to get a random quote), modification, and deletion of quotes. 
Quote properties include content, date of creation / update, link to user who posted it, link to votes;

Voting on quotes (upvote or downvote);

View of the top and worse 10 quotes.

### Pulling image from DockerHub
To pull image:
```bash
docker pull slavaniki/quotes:ver1
```

### Running All Services
To start both the backend and H2 database services as defined in the `docker-compose.yml` file:
```bash
docker-compose -f docker-compose.yml up -d
```

### Rebuild containers after changes
```bash
docker-compose -f docker-compose.yml build
```
### Stopping Services
To stop all running containers:
```bash
docker-compose -f docker-compose.yml down
```

### Remove all volumes (including database data):
```bash
docker-compose -f docker-compose.yml down -v
```