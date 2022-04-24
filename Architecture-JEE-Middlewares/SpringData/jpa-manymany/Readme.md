                   **Relation Many To Many**
    Exemple:role et User 
    =>Pour mapper la relation manyToMany il faut ajouter l'annotation @ManyToMany au dessus des attributs des classes;
    on lui ajoute la propriété mappedBy qui contient le nom d'attribut de l'autre classe.
    Ici mappedBy est placé au-dessus de l'attribut roles de la classe User.    
    =>on peut aussi utiliser @JoinTable pour redefinir le nom de la table
    => Le principe pour creer des users avec leurs roles ici est la suivante: il faut creer le User d'abord; creer les 
    les roles et ensuite  associer ces roles au User voulu. soit trois fonctions différentes.

    Creation d'entité:
User:

![img.png](img.png)

Role:

![img_1.png](img_1.png)

    Création d'interface!

UserRepository:

![img_3.png](img_3.png)


RoleRepository:

![img_2.png](img_2.png)


    Couche Metier:
    package service
Interface Service

![img_4.png](img_4.png)

Implementation:

    ->Injection des dependances
![img_5.png](img_5.png)

    -> .save() pour Ajouter un user
![img_6.png](img_6.png)

    ->.save() pour ajouter un role
![img_7.png](img_7.png)

    -> Associer des roles à des users
    la fonction recupère les roles du User et ajoute le ou les nouveaux roles.
    Si on veut faire une relation birectionnel on recupère les utilisateurs du role et on ajoute le User à la liste des
    users du role. c'est pas nécessaire JPA s'en charge
![img_8.png](img_8.png)

    ->Authentification
![img_15.png](img_15.png)



        Phase de Test:

Creation de Users

![img_9.png](img_9.png)
![img_12.png](img_12.png)

Creation de Roles:

![img_10.png](img_10.png)
![img_13.png](img_13.png)

Affecter les roles aux users

![img_11.png](img_11.png)
![img_14.png](img_14.png)

Authentification

![img_16.png](img_16.png)
![img_17.png](img_17.png)



      Test avec un api Rest
    =>on utilise l'annotation @RestController pour specifier que c'un controler Rest
    =>on fait un injection de dependance avec le constructeur
    =>avec l'annotation @GetMapping() je specifie le chemin
    
![img_18.png](img_18.png)

![img_19.png](img_19.png)
