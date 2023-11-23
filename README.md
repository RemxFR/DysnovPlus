# DYSNOV+ API

## Fonctionnement
Contrôleur Rest pour gérer le CRUD de films, d'acteurs et de réalisateurs.
L'API reçoit des requêtes http et permet la persistance et le requêtage en bdd.
En appelant les différents endpoints "/acteur", "/real" et "/film", on a accès à toutes
les opérations relatives à chacun de ces objets.

On peut ajouter dans chacune des requêtes GET un paramètre, définissant la limite de résultats obtenus.
La limite par défaut est de 20.

## Installation

#### Avec docker
Ouvrez un terminal, assurez-vous d'être dans le dossier DysnovPlus et lancez la commande :

docker compose -f docker-compose.dev.yml up --build

Après cela, vous pourrez faire les requêtes via postman par exemple sur le port 8081.

#### Sans docker
#### JDK
L'API est développée en Java 17, pour la mettre en service, il faut installer la JDK 17 sur son ordinateur.
L'API est déployé sur un serveur Tomcat, via des IDE comme IntelliJ, il est automatiquement utilisé.
Via des IDEs comme Eclipse, il faut le sélectionner dans la section serveur.
Par défaut le port utilisé est le 8080.

#### Connexion à la bdd
Pour se connecter à la base de données, il faut utiliser une bdd MySQL.
Il suffit dans l'API de rajouter le nom de la base de données préalablement créée sur MySQL, à la fin de la ligne 1
du fichier application.properties et le username et le password au niveau des lignes 7 et 8 de ce même fichier.

#### Connexion au Front
Dans le cadre d'une connexion à une partie frontend, la méthode addCorsMapping dans la classe ConfigSecurity.java,
permet de gérer la problématique des CORS. Dans l'implémentation actuelle, tout front peut envoyer une requête à l'API,
il faudra donc bien indiquer la bonne origine, via l'url du front en paramètre de ".allowedOrigins".

### Requêtes
Pour faire des requêtes, vous pouvez passer par postman et utiliser l'url suivante :
http://localhost:8081/
#### Dans de nombreuses requêtes de cette api, vous aurez un paramètre dans l'url qui est limit={nbrDeRésultats} et qui correspond au nombre de résultats maximum que vous souhaitez recevoir, sans indication particulière, ce nombre sera de 20 par défaut.
    - Pour les requêtes concernant les acteurs, vous ajouterez à l'url : acteur/
    Puis :
    -> trouver/id={id} : pour les trouver par id avec à la place de {id}, l'id souhaité;
    -> trouver/nom={nom}&limit={limiteDesRésultatsSql} : pour les trouver par nom, avec la limite souhaité;
    -> trouver/nom={nom}&prenom={prenom}&limit={sqlRowLimit} : pour les trouver par nom et prénom;
    -> trouver-tous/limit={sqlRowLimit} : pour trouver tous les acteurs;
    -> creer : pour créer un acteur
    exemple de body : 
    {
        "nom": "Shirer",
        "prenom": "Priscilla",
        "age": 48,
        "dateNaissance": "1974-12-31",
        "nationalite": "Etats-Unienne",
        "genrePersonne": "Femme",
        "metiers": [{
            "metier": "Actrice"
        }]
    }
    A noter que pour les métiers, le champ "metier" accepte : "Acteur", "Actrice", "Réalisateur" et Réalisatrice".
    -> modifier/{id} : pour modifier un acteur;
    -> supprimer/{id} : pour supprimer un acteur.

    - Pour les requêtes concernant les réalisateurs, les urls et le body de la requête sont exactement les mêmes que pour les acteurs, on mettra simplement à la place de acteur/, real/ .
    
    - Pour les films, on commencera avec film/ puis :
    -> trouver/{id} : pour trouver par id;
    -> trouverTous/limit={nbrDeRésultats} : pour trouver tous les films;
    -> trouverFilm/nomFilm={nom}&limit={nbrDeRésultats} : pour trouver un film par nom;
    -> trouverFilmParPersonne/nom={nom}&limit={sqlRowLimit} : pour trouver un film par le nom d'une personne, acteur ou réalisteur;
    -> trouverFilmParPersonne/limit={sqlRowLimit} : pour trouver un film via une liste de personnes;
    -> creer : pour créer un film
    exemple de body :
    {
        "titre":"War Room",
        "description":"Tony et Elizabeth Jordan ont apparemment tout pour être heureux : des métiers qu'ils aiment, une belle maison, une fille adorable. Hélas, les apparences sont trompeuses. Tony et Elisabeth ont des problèmes de couple, leur mariage bat sérieusement de l'aile et leur fille se retrouve au milieu de cette situation. Dieu va mettre sur leur route Miss Clara qui leur apportera de sages conseils. Grâce à son expérience, Elizabeth découvre qu'elle peut se battre pour sa famille plutôt que contre elle.",
        "dateDeSortie":"2015-08-28",
        "personnes":[
            {
         "id":4
            }
        ],
        "genreFilm":"Chrétien",
        "duree":"120"
    }
    -> modifier/{id} : pour modifier un film;
    -> ajouter-personne/{id} : pour ajouter une personne à un film, on met l'id du film en paramètre et la personne dans le body;
    -> ajouter-acteur-par-id/filmId={idFilm}&acteurId={idActeur} : pour ajouter un acteur à un film via leur id respectif;
    -> ajouter-realisateur-par-id/filmId={idFilm}&realId={idReal} : idem pour un real;
    -> supprimer/{id} : pour supprimer un film;

    A noter qu'il est possible de rajouter également des types de métiers et des récompenses via deux controleurs distincts : recompense/ et metier/
    pour les deux, les url crud sont simples :
    -> ajouter
    -> afficher-tout
    -> trouver/{id} : pour les récompenses, trouver/{nomMétier} : pour les métiers;
    -> modifier/{id} : pour les récompenses, modifier/{nomMétier} : pour les métiers;
    -> supprimer/{id} : pour les récompenses, supprimer/{nomMétier} : pour les métiers;

    Dans les deux cas, pour la création, on ajoute dans le body le champ "metier" ou "recompense" et puis la valeur qui lui est relative, en string.
    exemple: 
    {
        "metier": "Réalisateur"
    }

