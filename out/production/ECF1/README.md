# ECF1 -

Développer une interface utilisateur de type Desktop

Critères d’évaluation : les compétences suivantes seront évaluées
-	Le maquettage
-	Le diagramme de classes des classes Métier
-	L’algorithmie
-	Les bases du langage java
-	Les concepts de la Programmation Orientée Objet 
-	La documentation en Javadoc et les commentaires techniques
-	Les collections Java
-	Les exceptions Java
-	Les tests sur les classes Métier

 
Cahier des charges

Gestion de clients-prospects pour l’entreprise REVERSO

L’entreprise REVERSO veut pouvoir créer, modifier, supprimer ou afficher ses clients ou ses prospects.

	Sur la page d’accueil :
-	L’utilisateur devra choisir entre la gestion des clients et la gestion des prospects
-	Il choisira ensuite entre la création, la modification, la suppression ou l’affichage. Un label devra lui indiquer si il gère des clients ou des prospects
-	L’utilisateur pourra quitter l’application ou revenir au choix de clients/prospects à tout moment
-	Si l’utilisateur choisit la modification ou la suppression, une liste déroulante apparaîtra sur cette page pour lui permettre de choisir la société suivant sa raison sociale.

	Pour la création, suppression, modification, une nouvelle page s’affichera
-	Pour la création, tous les champs seront vides hormis l’identifiant 
-	Pour la modification, tous les champs seront affichés et pourront être modifiés hormis l’identifiant
-	Pour la suppression, tous les champs seront affichés mais aucun champ ne pourra être modifié. Une boite de dialogue demandera confirmation de la suppression après validation
-	L’utilisateur devra pouvoir quitter l’application ou revenir à la page d’accueil à tout moment

	Pour l’affichage, une nouvelle page s’affichera

-	Celle-ci affichera les clients de l’entreprise ou ses prospects suivant le choix fait en page d’accueil. La liste sera triée par raison sociale du client ou du prospect
-	Toutes les données des clients ou des prospects devront apparaître, sauf les commentaires
-	L’utilisateur devra pouvoir quitter l’application ou revenir à la page d’accueil à tout moment

Le client aura comme données : un identifiant, sa raison sociale, son adresse, son numéro de téléphone, son adresse mail de contact, son chiffre d’affaire et son nombre d’employés. L’utilisateur pourra rentrer des commentaires concernant le client. 

Le prospect aura comme données : un identifiant, sa raison sociale, son adresse, son numéro de téléphone, son adresse mail de contact, la date où il a été prospecté et si il a semblé intéressé. L’utilisateur pourra rentrer des commentaires concernant le prospect.
 
Spécifications techniques

-	Le développement se fera en Java avec la bibliothèque graphique Swing
-	L’affichage de la liste des clients ou des prospects se fera avec une JTable 
-	Une Javadoc sera fournie
-	La gestion des données se fera à l’aide de Collections Java

Spécifications fonctionnelles

1.	Créez une classe Societe abstraite avec les attributs suivants : 

-	Identifiant : entier
-	Raison sociale : chaîne de caractères
-	Numéro de rue : chaine de caractères
-	Nom de rue : chaîne de caractères
-	Code postal : chaîne de caractères 
-	Ville : chaîne de caractères
-	Téléphone : chaîne de caractères
-	Adresse mail : chaîne de caractères
-	Commentaires : chaîne de caractères
Contraintes :
-	L’identifiant sera affecté et incrémenté automatiquement en commençant par 1. Il ne pourra pas être modifié par l’utilisateur. Il sera visible en cas d’affichage, de modification ou de suppression pour information
-	La raison sociale devra être saisie
-	Tous les champs de l’adresse devront être renseignés
-	Le téléphone devra être renseigné et avoir au moins 10 caractères 
-	L’adresse mail devra être renseignée et avoir au moins le caractère « @ »
-	Les commentaires ne seront pas obligatoires

2.	Créez une classe Client héritant de la classe Societe avec comme attributs supplémentaires : 

-	Le chiffre d’affaire du client : double
-	Le nombre d’employés : entier

Contraintes :
-	Le chiffre d’affaire devra être renseigné et être supérieur à 200
-	Le nombre d’employés devra être renseigné et être strictement supérieur à zéro
 

3.	Créez une classe Prospect héritant de la classe Societe avec comme attributs supplémentaires : 

-	La date de prospection : format date (LocalDate) 
-	Prospect intéressé : chaîne de caractères ou entier

Contraintes :
-	La date doit être renseignée et avoir le format « jj/mm/aaaa »
-	Les valeurs affichées de « Prospect intéressé » seront « oui » ou « non ». Gérer une énumération et une liste déroulante dans le formulaire, ou des CheckBox

4.	Les collections des objets devront être gérées dans deux classes à part, une pour les clients, une pour les prospects. Il ne devra y avoir qu’une seule liste déroulante : La liste déroulante de la page d’accueil n’affichera que les clients ou les prospects suivant le choix de l’utilisateur. Les collections peuvent être initialisées avec quelques objets au démarrage de l’application pour faciliter les tests

5.	L’application aura 3 Frames : Il ne devra y avoir qu’une seule Frame pour la création, modification et suppression des clients et des prospects. Celle-ci devra donc s’adapter aux choix de l’utilisateur

6.	Il ne devra y avoir qu’une seule Frame pour afficher la liste des clients ou des prospects. Celle-ci devra donc s’adapter au choix de l’utilisateur

7.	En option :
-	Gérer la persistance des données avec un fichier
-	Permettre à l’utilisateur de sélectionner un client ou un prospect dans la page d’affichage, lui afficher la page formulaire pour pouvoir le modifier ou le supprimer 

Travail demandé :
-	Un diagramme de classes Métier, comportant les attributs et les méthodes pour chaque classe, sera effectué en amont du développement avec un utilitaire de votre choix et me sera envoyé sur ma boite mail office 365 pour validation.
-	Un maquettage de l’application sera réalisé sur papier ou avec un utilitaire de votre choix et me sera envoyé sur ma boite mail office 365 pour validation.
-	Le code me sera envoyé au plus tard le … sur ma boite mail office 365.



