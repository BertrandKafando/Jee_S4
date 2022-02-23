                         Injection des dépendances avec Spring


Utilisation d'un fichier config xml

Cette methode consiste à definir un fichier de configuration grace auquelle Spring va associer les bonnes dependances
![img.png](img.png)


Le main devient 
![img_1.png](img_1.png)


Injection des dependances par Annotation @

la méthode consiste à mettre @component au dessus de la classe à utiliser 
et @autowired devant la variable.

![img_2.png](img_2.png)
![img_3.png](img_3.png)
main:
![img_4.png](img_4.png)
resultat:
![img_5.png](img_5.png)

on peut aussi nommer les composants dans ce cas on doit utiliser @qualifer pour choisir 
version bd
![img_6.png](img_6.png)
version capteurs:
![img_8.png](img_8.png)

IMetierImplementation
![img_9.png](img_9.png)

Resultat:
![img_10.png](img_10.png)





Injection des dependances par constructeur



