Liste des choses à faire : 
- Changement de l'appel des directions ("up", "down", ...) par un enum (moins couteux en espace mémoire)
- Optimisation de performance pour le rendu des tiles à refaire & revoir (faire un rendu autour du joueur de juste un morceau de la map)
- Vérifier d'où vient le problème de vitesse dans un sens plutôt que dans l'autre


Questions à poser : 
- Est-ce que sortir le fichier main.java du package main améliorerait la structure du code ?
- Est-ce que mettre l'entièreté de la bibliothèque est recommandé ou non ?
- Est-ce que dans les notions à implémenter, le tableau est considéré comme à taille variable ou non ?
- faire un fichier de constantes
  mettre tous les objects dans un même dossier
  simplifier la méthode "touchingPlayer" dans la classe Ennemy (enlever le else et if)
  enlever la méthode isBlocking (utilisé qu'une fois dans une autre méthode)
  commenter les lignes de renan
  expliquer dans le keyHandler pourquoi notre choix d'utiliser des des if est dans notre cas plus interresant que d'utiliser une énumération
  Utiliser la méthode sleep dans la classe méthode run dans la classe Window