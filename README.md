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

    curl -i -X POST http://localhost:8181/turn

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Content-Type: application/json
    Content-Length: 1
    {
        "turn": 1::int
    }

## Reset

### Request

`POST /reset/`

    curl -i -X POST http://localhost:8181/reset

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Content-Type: application/json
    Content-Length: 0

## Ajouter des ressources

### Request

`POST /resources`

    curl -i -X POST http://localhost:8181/reset
         -H 'Content-Type: application/json'
         -d '{
                "qtyBurger": 0::int,
                "qtySalad": 0::int,
                "qtyWater": 0::int 
            }'

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Content-Type: application/json
    Content-Length: 0

## Connaître la quantité de nos ressources

### Request

`GET /resources`

    curl -i http://localhost:8181/resources

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Content-Type: application/json
    Content-Length: 0
    {
        "fresh": {
            "qtyBurger": 0::int,
            "qtySalad": 0::int,
            "qtyWater": 0::int
        },
            "expired": {
            "qtyBurger": 0::int,
            "qtySalad": 0::int,
            "qtyWater": 0::int
        },  
            "consumed": {
            "qtyBurger": 0::int,
            "qtySalad": 0::int,
            "qtyWater": 0::int
        }
    }

## Ajouter de dinoraures

### Request

`POST /dinosaurs`

    curl -i -X POST http://localhost:8181/dinosaurs
         -H 'Content-Type: application/json'
         -d '{
                "name": ""::string,
                "weight": 0::int,
                "gender": f || m::string,
                "species": ""::string 
            }'

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Content-Type: application/json
    Content-Length: 0

## Affichage d’un dinosaure vivant

### Request

`GET /dinosaurs/{NAME}`

    curl -i http://localhost:8181/dinosaurs/{NAME}

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Content-Type: application/json
    Content-Length: 0
    {
        "name": ""::string,
        "weight": 0::int,
        "gender": f || m::string,
        "species": ""::string
    }   

## Affichage de tous les dinosaures vivants

### Request

`GET /dinosaurs`

    curl -i http://localhost:8181/dinosaurs

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Content-Type: application/json
    Content-Length: 0
    [
        {
            "name": ""::string,
            "weight": 0::int,
            "gender": "f" || "m"::string,
            "species":""::string
        },
        ...
        {
            "name": ""::string,
            "weight": 0::int,
            "gender": "f" || "m"::string,
            "species": ""::string
        }
    ]

## Ajouter un bébé dinosaure

### Request

`POST /breed`

    curl -i -X POST http://localhost:8181/breed
         -H 'Content-Type: application/json'
         -d '{
                "name": ""::string,
                "fatherName": ""::string,
                "motherName": ""::string
            }'

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Content-Type: application/json
    Content-Length: 0

## Combat de sumo-dinosaures

### Request

`POST /sumodino`

    curl -i -X POST http://localhost:8181/sumodino
         -H 'Content-Type: application/json'
         -d '{
                "challenger": ""::string,
                "challengee": ""::string
            }'

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    Content-Type: application/json
    Content-Length: 0