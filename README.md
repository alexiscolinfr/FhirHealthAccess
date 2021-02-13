# Fhir Health Access
Projet d'application de santé de Master 2 MIAGE à l'Université Claude Bernard Lyon 1.

## 📄 Description
Dans le cadre du cours de développement d'applications de gestion de santé de notre master, nous avons été amenés à réaliser une application permettant d’effectuer des requêtes FHIR sur un serveur via son API et d’interpréter les résultats reçus.

L'objectif de cette application est d'utiliser la ressource patient en vue de sa gestion (affichage, création, suppression, modification) et de l'affichage de la liste des patients.

Nous utilisons pour cela l'API du serveur FHIR de test [Pyrohealth](https://stu3.test.pyrohealth.net/fhir/).

## ⚙ Installation
Pour installer l'application sur un téléphone Android, veuillez suivre les insctructions suivante :
1. Allez dans les **paramètres**, puis **Sécurité**
2. Activez les **sources inconnues**
3. Recherchez l’APK sur votre téléphone (à l’aide d’un explorateur de fichiers par exemple)
4. Lancez le fichier APK et suivez les instructions

## 📋 Guide d'utilisation

### Connexion
Une authentification est requise pour accéder à l'application afin de protéger les données personnelles des patients. Les informations d'accès sont délivrées par l'administrateur afin de s'assurer que seules les personnes autorisées puissent se connecter.

Notre application étant en phase de test et utilisant uniquement des données de patients fictifs, vous pourrez accéder à l'application en utilisant les informations de connexion suivantes :

* Email : `admin@fhir.org`
* Mot de passe : `admin`

### Page d'accueil
Sur la page d'accueil de l'application, l'utilisateur peut visualiser la liste des patients, actualiser cette liste ou effectuer une recherche avec le nom ou prénom du patient (depuis la liste dans l'application ou le serveur directement).

Pour effectuer une recherche dans la liste des patients déjà présents sur l'application, il vous suffira d'entrer dans la barre de rechercher le nom ou prénom du patient. Un filtre en temps réel sera effectué sur la liste.

Si cette recherche ne retourne aucun résultat ou que les résultats ne correspondent pas au patient recherché, il vous faudra alors appuyer sur la petite loupe en bas à droite de votre clavier pour lancer une recherche depuis le serveur. 
Une fois cette recherche lancée, soit le ou les patients correspondant à votre recherche s'afficheront dans la liste, soit un message en bas de votre écran vous indiquera qu'aucun patient correspondant à votre recherche n'a été trouver sur le serveur.

### Détails d'un patient
Pour afficher toutes les informations de la fiche d'un patient (nom, prénom, statut, sexe, date de naissance, téléphone, ville, état civil et langue), vous devez sélectionner dans la liste des patients de la page d'accueil le patient qui vous intéresse.
Une nouvelle fenêtre va alors s'ouvrir contenant toutes les informations du patient. 

Si vous souhaitez modifier des informations sur le patient sélectionné, ou supprimer la fiche patient du serveur, il vous suffira d'appuyer sur le bouton correspondant dans le menu.

### Ajout d'un patient
Pour ajouter un nouveau patient, vous devez cliquer sur le bouton correspondant dans le menu de l'application. Vous pourrez ensuite renseigner tous les informations du patient et valider la fiche.

## 🎥 Démonstration
[![vidéo démonstration](https://i.ibb.co/X5cQKwj/vid.jpg)](https://youtu.be/P2mCOxJprFM)
