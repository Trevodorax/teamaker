# Teamaker

Welcome to Teamaker, the app that lets you manage developer teams for projects.

## How to start

### Prerequisites

- Java 21
- A way to run a Maven project
- docker and docker compose

### Common instructions

Copy `.env.example` to `.env` and replace with the correct values for you.

### Instructions for dev mode

Start by doing the common instructions.

Copy `api-teamaker/src/main/resources/application-local.example.yml` to `api-teamaker/src/main/resources/application-local.yml` and replace with the correct values for you.

You will have to set your IDE to use this profile instead of the default one.

Copy the .env to api-teamaker/

Run docker-compose up in `api-teamaker`. It will start the dev db for you.

With IntelliJ, to do this this, you must edit the run configuration of Main, and add "local" to active profiles.

### Instructions for production

Start by doing the common instructions.

run `docker-compose up` at the root of the project
