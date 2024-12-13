IFT1025 - Travail Pratique 2 
13 Décembre 2024 

Justine Theriault - 20231918
Heyun Liu - 20269538

* Veuillez noter que nous avions toutes les deux un Mac pour effectuer ce projet *

Afin d'utiliser notre programme, veuillez premièrement ouvrir deux terminaux côte-à-côte et naviguer dans le fichier ou se trouve les fichiers DIROgueCleint.java et DIROgueServer.java (voir exemple qui s'applique à notre cas) : 

  cd distrib\src\main\java

Lorsqu'on se trouve dans le bon fichier, compiler le projet en incluant le chemin ou se trouvent les modules JavaFX pour aider à compiler. Il est très important de mentionner la bonne version du javafx-sdk qu'on a de téléchargé, qui peut varier selon l'utilisateur. Faire cette étape dans les deux terminaux. 

  javac --module-path "C:\javafx-sdk-23.0.1\lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics dirogue/example/*.java     dirogue/example/code_squelette/*.java

Ensuite, dans un des deux terminaux, éxécuter premièrement le fichier DIROgueServer.java avec la commande suivante : 

  java --module-path "C:\javafx-sdk-23.0.1\lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics dirogue.example.DIROgueServer

Attendez de recevoir les instructions du terminal, et éxécuter le fichier DIROgueClient.java dans le second terminal avec la commande suivante : 

  java --module-path "C:\javafx-sdk-23.0.1\lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics dirogue.example.DIROgueClient

Lorsque la connexion est établie entre le serveur et le port, veuillez suivre les instructions du terminal DIROgueClient et entrer une des commandes (load, save, exit). 

  load : entrer le chemin qui trouvera le fichier commands.txt (il faut sortir du fichier actuel pour avoir accès au commandes).
  ../../../commands.txt

  save : une fois les commandes reçues du côté du serveur, sauvegarder le chemin dans le rapport.txt en précisant le chemin.
  ../../../rapport.txt

  exit : Sortie du programme et finalisation du serveur.

Finalement, pour afficher l'interface graphique, simplement naviguer au fichier qui contient le pom.xml et éxécuter le code en utilisant Maven :

  cd ..\..\..
  mvn clean javafx:run 




