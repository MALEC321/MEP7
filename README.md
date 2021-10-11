# REST API diGLOsaure

API du jeu diGLOsaure qui permet de gérer un parc de dinosaures

L'application est contenu dans `game-api` package.


## Install

    mvn clean install

## Lancer app

    mvn exec:java -pl application

## Lancer les tests

    mvn test

# Voir le code coverage

Après avoir lancer les tests, le dossier target est généré avec différentes fichiers.

1- Aller dans : target / site / jacoco

2- Ouvrir le fichier index.html dans IntelliJ

3- Une barre d'outil apparaît en haut à droite dans le fichier index.html

4- Cliquer sur le navigateur de votre choix (Firefox, Chrome, Safari...)
## Éxecuter un tour

### Request

`Post /turn/`

    curl -i -H 'Accept: application/json' http://localhost:8181/turn

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Content-Type: application/json
    Content-Length: 1

    {
        "turnNumber": 1::int
    }

## Reset

### Request

`POST /reset/`

    curl -i -H 'Accept: application/json' http://localhost:8181/reset

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Content-Type: application/json
    Content-Length: 0
