/**
 * IFT1025 - Travail Pratique 2 
 * 13 décembre 2024 
 * 
 * Justine Theriault - 20231918
 * Heyun Liu - 20269538
 */
package dirogue.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos; // Ajout de l'import pour le positionnement des boutons et du texte. Source : https://docs.oracle.com/javase/8/javafx/api/javafx/geometry/Pos.html
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Vue pour le mode de replay.
 * Cette vue permet la visualisation d'un rapport sauvegardé avec des
 * fonctionnalités de lecture.
 */
public class ReplayView extends ViewBase {
    /**
     * Conteneur principal de la vue de replay.
     */
    private VBox rootPane;

    /**
     * Label pour afficher le message associé à la rencontre en cours.
     */
    private Label messageLabel;

    /**
     * ImageView pour afficher l'image correspondant à la rencontre en cours.
     */
    private ImageView imageView;

    /**
     * Bouton pour passer à la rencontre suivante dans le rapport.
     */
    private Button forwardButton;

    /**
     * Bouton pour revenir à la rencontre précédente dans le rapport.
     */
    private Button backwardButton;

    /**
     * Bouton pour quitter le mode de replay.
     */
    private Button exitButton;

    /**
     * Constructeur par défaut pour la vue de replay.
     */
    public ReplayView() {
        super();
    }

    /**
     * Retourne le nom de la vue de replay.
     *
     * @return Le nom de la vue de replay ("Replay").
     */
    @Override
    public String getName() {
        return "Replay";
    }

    /**
     * Renvoie la racine (root) de la vue de replay.
     *
     * @return La racine (root) de la vue de replay.
     */
    @Override
    public Parent getRoot() {
        return rootPane;
    }

    /**
     * Renvoie le label pour afficher le message de la rencontre.
     *
     * @return Le label pour afficher le message.
     */
    public Label getMessageLabel() {
        return messageLabel;
    }

    /**
     * Renvoie l'ImageView pour afficher l'image de la rencontre.
     *
     * @return L'ImageView pour afficher l'image.
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Renvoie le bouton pour passer à la rencontre suivante.
     *
     * @return Le bouton pour avancer dans le rapport.
     */
    public Button getForwardButton() {
        return forwardButton;
    }

    /**
     * Renvoie le bouton pour revenir à la rencontre précédente.
     *
     * @return Le bouton pour reculer dans le rapport.
     */
    public Button getBackwardButton() {
        return backwardButton;
    }

    /**
     * Renvoie le bouton pour quitter le mode de replay.
     *
     * @return Le bouton pour sortir du mode replay.
     */
    public Button getExitButton() {
        return exitButton;
    }

    /**
     * Crée l'interface utilisateur pour la vue de replay.
     * Initialise les éléments graphiques tels que les boutons, l'image, et le label.
     */
    @Override
    protected void createUI() {
        // Création de l'interface Replay 
        rootPane = new VBox();
        rootPane.setPadding(new Insets(10));
        rootPane.setSpacing(10);
        /**
         * Utilisation de Pos pour centrer l'interface. Le code est plus facile à écrire et à lire en utilisant les Pos au lieu 
         * d'ajuster manuellement les Insets. 
         * 
         * La classe Pos permet de spcifier directement l'alignement des éléments dans un conteneur, 
         * rendant le positionnement plus intuitif. 
         * 
         * Voici les deux sources utilisées : 
         * Source : https://docs.oracle.com/javase/8/javafx/api/javafx/geometry/Pos.html
         * Source : https://www.geeksforgeeks.org/javafx-pos-class/
        */
        rootPane.setAlignment(Pos.CENTER);

        // Alignement du texte scénario et dimension de l'image associée 
        messageLabel = new Label();
        imageView = new ImageView();
        imageView.setFitWidth(300); 
        imageView.setFitHeight(300);

        // Création des boutons 
        backwardButton = new Button("Backward");
        forwardButton = new Button("Forward");
        exitButton = new Button("Exit");

        // Boite qui contient les boutons backward/forward
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(backwardButton, forwardButton);

        // Affichage de l'interface Replay avec tout ses attributs 
        rootPane.getChildren().addAll(messageLabel, imageView, buttonBox, exitButton); 


    }
}
