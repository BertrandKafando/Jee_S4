                   **Relation Many To Many**
    Exemple:role et User 
    Pour mapper la relation manyToMany il faut ajouter l'annotation @ManyToMany au dessus des attributs des classes;
    on lui ajoute la propriété mappedBy qui contient le nom d'attribut de l'autre classe.
    Ici mappedBy est placé au-dessus de l'attribut roles de la classe User.    

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