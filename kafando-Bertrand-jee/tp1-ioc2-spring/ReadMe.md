                         **Injection des dépendances avec Spring(core,Context,Beans)**

L'interet d'utiliser un framework est de laisser le code technique  à ce dernier et s'occuper du code metier.
Il y'a principalement 02 manieres: Les annotations er l'utilisation d'un fichier xml

                                 **Utilisation d'un fichier config xml**
Cette methode consiste à definir un fichier de configuration xnl grace auquelle Spring va associer les bonnes dependances
Pour les dependances on peut utiliser un constructeur ou un setter.

    L'utilisation d'un setter: dans le fichier xml on declarer un bean pour chaque classe.pour declarer le setter on utilise la balise 
    poperty.
![img.png](img.png)
![img_15.png](img_15.png)

    Le main devient 
![img_1.png](img_1.png)

    L'utilisation d'un constructeur: pour le constructeur on utilise la balise constructor-org
![img_13.png](img_13.png)
![img_14.png](img_14.png)

    resultat:
    version bd
![img_11.png](img_11.png)
    version capteurs
![img_12.png](img_12.png)







                                  **Injection des dependances par Annotation @**

    la méthode consiste à utiliser des annotations pour specifier  au framework quel classe utiliser
 @componnent--> pour designer que les classes doivent implementer
![img_16.png](img_16.png)
![img_18.png](img_18.png)

Cas 1: Notation Autowired
    Dans l'implementation de IMetier les annontations peuvent varier
    on utilise @Autowired pour  faire l'injection 
![img_17.png](img_17.png)
     Dans le main 
![img_19.png](img_19.png)
resultat:
![img_5.png](img_5.png)



Lorque qu'il y'a plusieurs implementation ,il faut donner un nom à chaque component .
Dans ce cas on doit utiliser @qualifer("nom du compenet à instancier") pour choisir 
version bd
![img_6.png](img_6.png)

version capteurs:
![img_8.png](img_8.png)

IMetierImplementation
![img_9.png](img_9.png)

Resultat:
![img_10.png](img_10.png)


Cas 2: Injection des dependances par constructeur 
on garde @component 
![img_20.png](img_20.png)

on declare le constructeur
![img_21.png](img_21.png)

Lorsque y'a plusieurs composants , le contructeur choisit le premier qu'il trouve





