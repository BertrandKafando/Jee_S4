                        Mapping Objet Relationnel avec Spring Data
    * Spring Data est un module de Spring qui a déjà créé des interfaces génériques et des implémentations
    génériques qui permettent de gérer les entités JPA.
    * on appelle plus EntityManager de JPA pour la persistance spring s'en occupe lui meme
    *Il suffit de créer une interface qui hérite de l’interface JPARepository pour hériter toutes les méthodes
    classiques qui permettent de gérer les entités JPA.

     Pour l'exemple, nous ferons une application de gestion de patients

    Creation d'entité:
    POur les getters ,les setters et les je les declare
    grace aux annotations du plugin lambock
![img.png](img.png)

    Creer entité/table avec les annotations de jpa
![img_1.png](img_1.png)

    Generation des entités
    Configuration de application propertie
![img_2.png](img_2.png)

    Excution de l'application SpringDataCourseApplication
    on peut voir la bd sur localhost:8082/h2-console
![img_3.png](img_3.png)

    Operation dans la bd 
    Pour gerer l'entité Patient creons un Jpa repository
![img_4.png](img_4.png)
    
    on implemente une interface CommandLineRunner pour les operations  après l'excecution
    et grace à @Autowired on fait l'injection des dependances du PatientRepository

.save() insertion
![img_5.png](img_5.png)

Résultat Excécution:
![img_6.png](img_6.png)

    .findById()
![img_10.png](img_10.png)

resultat:
![img_11.png](img_11.png)

    update avec .save()
![img_12.png](img_12.png)

resulat:
![img_13.png](img_13.png)

    .deleteById() suppression 
![img_14.png](img_14.png)

resultat: suppresion de bertrand
![img_15.png](img_15.png)


    .findAll()
    Cas 1:on peut recuper sous forme de List de patients
![img_7.png](img_7.png)
![img_8.png](img_8.png)

    Cas 2:recuperer sous forme de Page
    Il est utilise lorque le nombre de données est important, on a besion de faire de la pagination
![img_16.png](img_16.png)

Résultat: Retourne 5 enregistrement
![img_17.png](img_17.png)

                Nous avons vu les operations de base comment creer  nos propres méthodes
    il suffit d'aller  la declarer  dans l'interface jpa repository

    Cas 1:
    .findByMalade(m) sans pagination
Creation:
![img_20.png](img_20.png)

Main:
![img_18.png](img_18.png)

resultat:
![img_19.png](img_19.png)

    .findByMalade(m) avec pagination
Creation:
![img_21.png](img_21.png)

Main:
![img_22.png](img_22.png) 

Resultat: 5 personnes malades
![img_23.png](img_23.png)


    Cas 2: sql
Creation:
![img_26.png](img_26.png)
main:
![img_27.png](img_27.png)

resultat: on va retourner tous les bertrand qui ont un score inferieur à 50
![img_25.png](img_25.png)

                  Passer à Mysql
    on ajoute la dependances mysql et on modifie application.properties
![img_28.png](img_28.png)
![img_29.png](img_29.png)