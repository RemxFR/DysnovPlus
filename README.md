# DYSNOV+ API

## Fonctionnement
Contrôleur Rest pour gérer le CRUD de films, d'acteurs et de réalisateurs.
L'API reçoit des requêtes http et permet la persistance et le requêtage en bdd.
En appelant les différents endpoints "/acteur", "/real" et "/film", on a accès à toutes
les opérations relatives à chacun de ces objets.

On peut ajouter dans chacune des requêtes GET un paramètre, définissant la limite de résultats obtenus.
La limite par défaut est de 20.

## Installation

#### JDK
L'API est développée en Java 17, pour la mettre en service il faut installer la JDK 17 sur son ordinateur.
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