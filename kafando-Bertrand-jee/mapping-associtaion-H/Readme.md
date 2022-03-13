                **Mapping des associations et de l'heritage JPA SPRING DATA



    creation des entités:
![img.png](img.png)  ![img_8.png](img_8.png)  

    Mapping des classes

    **Medecin RendezVous ManyTo One**
    RendezVous a un attribut medecin que j'utilise pour mapper l'association avec la notation @OneToMany 
    j'utilise un type LAZY pour ne pas chargé tous les rendezvous d'un medecin lorque je le charge.
![img_2.png](img_2.png)

    dans la classe Rendez on ajoute l'annotation @ManyToOne à l'emtité Medecin
![img_3.png](img_3.png)

    **Patient RendezVous ManyTo One **
    Dans l'entité RendezVous,au dessus de l'attribut patient , on ajoute l'annotation @ManyToOne pour exprimer la relation
    Dans  l'entité Medecin, au dessus de la collection de rendevous on ajoute l'annotation @OneToMany pou exprimer la relation
    Pour mapper l'association on ajoute à @OneMany les attributs **mappdBy** pour specifier le nom de l'attribut de l'entité en question
    dans l'autre table.et ** fecth type LAZY** pour eviter de charger les rendevous d'un patient lorsque je charge un patient
![img_4.png](img_4.png)
![img_5.png](img_5.png)

    **Consultation RendezVous OneToOne**
    Pour exprimer l'association on met l'annotation @OneToOne au-dessus des attributs entités .
    Pour mapper l'assoction on doit ajouter mappedBy dans l'une des annotations. Le choix de là ou il est placé determine
    la classe qui contiendra la clé étrangère. Il suffit de raisonner qui doit etre créer avant l'autre pour savoir là ou
    le placer.
    Ici, pour qu'un consultation aie lieu il faut d'abord prendre un rendezvous donc nous allons le placer au dessus de 
    au dessus de l'attribut consultation de la classe Rendez vous.Consultation aura la clé étrangère.
![img_6.png](img_6.png)

![img_7.png](img_7.png)


        **Creation d'un application**

    Creation des repositories pour chaque entité
![img_9.png](img_9.png)
![img_10.png](img_10.png)

    Test: creation d'une méthode qui retoune CommandLineRunner avec une annotation @Bean
    @Bean--> excution au demarrage qui va retourne  un object 

    **Creation de patient**
![img_11.png](img_11.png)
![img_12.png](img_12.png)

    **Creation de medecin**
![img_13.png](img_13.png)
![img_14.png](img_14.png)

    **Creation de Rendevous**
![img_19.png](img_19.png)
![img_16.png](img_16.png)

    **Creation de Consultation**
![img_18.png](img_18.png)
![img_17.png](img_17.png)

        **Bonne pratique:Creation d'une interface dans le package service offrant le besoin fonctionnel**
    *c'est la couche service qui est utilisé dans le main

![img_20.png](img_20.png)

    *utilisation UUID
![img_22.png](img_22.png)
![img_21.png](img_21.png)