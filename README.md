# TP3 - Plus courts chemins
## Introduction

### Objectifs du Projet
Le projet vise à explorer et comparer les
performances de deux versions de l'algorithme
de Dijkstra, utilisées pour trouver le plus 
court chemin dans un graphe pondéré. 
Plus précisément, nous mettrons en œuvre
une version naïve de l'algorithme conformément
aux principes enseignés en cours, puis la 
comparerons avec l'implémentation de Dijkstra
fournie par la bibliothèque GraphStream.

## RandomGenerator

Pour créer des graphes de grande taille, nous avons opté pour l'utilisation du générateur RandomGenerator de GraphStream, qui offre la flexibilité de construire des graphes de taille variable. Ce générateur permet de contrôler le degré moyen des nœuds, influençant ainsi la connectivité du graphe.

Pour ajuster la connectivité, nous avons paramétré le degré moyen lors de l'instanciation du générateur.
Generator g = new RandomGenerator(10, false, false, "", "cap");
Ici, le paramètre 10 représente le degré moyen initial, et en ajustant cette valeur, nous pouvons influencer la densité de connexions entre les nœuds du graphe généré.

## Algorithme de Dijkstra - Version Naïve   

La fonction djikstra implémente l'algorithme de Dijkstra pour trouver les plus courts chemins depuis un nœud source dans un graphe orienté pondéré. L'algorithme maintient
un ensemble de nœuds non visités avec des distances initialement fixées à une valeur maximale. Il commence par initialiser la distance du nœud source à zéro et l'ajoute à une
file de priorité basée sur ces distances. Ensuite, tant que la file de priorité n'est pas vide, l'algorithme extrait le nœud avec la plus petite distance actuelle, explore ses voisins non visités et met à jour leurs distances si un chemin plus court est découvert. Les distances mises à jour sont stockées dans les attributs des nœuds. La fonction maintient également une file de priorité pour optimiser la sélection du prochain nœud à explorer. Enfin, la fonction mesure le temps d'exécution total de l'algorithme et stocke cette information pour une éventuelle analyse des performances.

## Algorithme de Dijkstra - graphstream

Dans cette réalisation, j'ai intégré l'algorithme de Dijkstra à travers la bibliothèque GraphStream. L'utilisation de cette bibliothèque simplifie considérablement la mise en œuvre de l'algorithme, offrant une approche plus concise et moins sujette aux erreurs.

La fonction dijkstraGS commence par mesurer le temps de début d'exécution. Ensuite, elle initialise l'algorithme de Dijkstra à l'aide de la classe Dijkstra de GraphStream, en spécifiant que le calcul doit être basé sur les arêtes (Dijkstra.Element.EDGE) et en indiquant l'attribut représentant les poids des arêtes ("cap" dans ce cas). Après cette initialisation, l'algorithme est exécuté à l'aide de la méthode compute().

Les résultats de l'algorithme sont ensuite parcourus, affichant la distance entre le nœud source et chaque nœud atteignable dans le graphe. Si un chemin existe, ce dernier est également affiché. Enfin, le temps total d'exécution est mesuré et enregistré.

En résumé, l'implémentation avec GraphStream offre une approche simplifiée de l'algorithme de Dijkstra, avec une gestion transparente des aspects techniques, tels que la manipulation du graphe et le suivi des distances. Cela permet une meilleure lisibilité du code et facilite l'évaluation des performances de l'algorithme sur des graphes de différentes tailles.

## tests

Dans ces tests, j'ai évalué les performances de deux implémentations de l'algorithme de Dijkstra sur des graphes de tailles croissantes, allant de 0 à 10 000 nœuds. J'ai choisi d'augmenter la taille du graphe par incréments de 1 000 nœuds pour observer les variations de performances. Les temps d'exécution de chaque algorithme ont été mesurés, enregistrés dans un fichier CSV, et les résultats ont été affichés à chaque étape pour faciliter le suivi. Ces tests permettent d'analyser la manière dont les deux implémentations se comportent dans des scénarios divers, fournissant ainsi des informations cruciales sur leur efficacité

## Résultats
     ![Alt text](C:\Users\othma\OneDrive\Bureau\Screenshot 2023-11-27 191257.png)

Nous observons une corrélation entre l'augmentation de la taille du graphe et l'accroissement des temps d'exécution. Dans le cas de graphes de petite taille, la méthode naïve et l'approche utilisant GraphStream affichent des performances relativement similaires. Toutefois, à mesure que la taille du graphe approche 10 000 nœuds, la méthode naïve montre des signes de ralentissement significatifs. En revanche, l'implémentation GraphStream semble mieux gérer cette complexité croissante, préservant des temps d'exécution plus raisonnables même avec des graphes de grande envergure. Cette observation souligne la capacité de l'approche basée sur GraphStream à maintenir des performances stables face à des graphes de taille importante, par rapport à une implémentation naïve qui montre des limitations apparentes dans ces scénarios plus vastes.

## Conclusion

Ce projet a constitué une exploration approfondie des algorithmes de plus courts chemins, mettant en lumière deux approches distinctes de l'algorithme de Dijkstra. L'implémentation naïve, bien que conceptuellement simple, a révélé ses limitations lors de tests sur des graphes de grande envergure, montrant une tendance à des temps d'exécution considérablement augmenté. En revanche, l'utilisation de l'implémentation de Dijkstra fournie par la bibliothèque GraphStream a démontré une meilleure gestion de la complexité, maintenant des performances plus constantes à mesure que la taille du graphe augmentait.


