package dirogue.example.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import dirogue.example.App;
import dirogue.example.view.MainView;
import dirogue.example.view.ViewBase;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

/**
 * Contrôleur principal pour la vue principale de l'application.
 * Ce contrôleur gère les interactions et les actions liées à la vue principale
 * de l'application.
 */
public class MainController extends ControllerBase {

    /**
     * Vue spécifique utilisée pour la vue principale.
     */
    private MainView mainView;

    /**
     * Constructeur pour le contrôleur principal.
     *
     * @param view La vue associée à ce contrôleur.
     */
    public MainController(ViewBase view) {
        super(view, null);
    }

    /**
     * Méthode d'initialisation du contrôleur principal.
     * Cette méthode configure les actions des boutons de la vue principale.
     */
    @Override
    protected void initialize() {
        this.mainView = (MainView) super.view;

        Button loadButton = mainView.getLoadButton();
        Button replayButton = mainView.getReplayButton();

        loadButton.setOnAction(event -> loadTextFile());

        replayButton.setOnAction(e -> {
            App.showView("Replay", mainView.getTextArea().getText());
        });
    }

    /**
     * Méthode privée pour charger un fichier texte dans la vue principale.
     * Cette méthode est déclenchée lorsqu'un utilisateur appuie sur le bouton de
     * chargement.
     * 
     */
    private void loadTextFile() {
        // Créer l'instance FileChooser pour aller chercher le raport 
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load report");

        // Affiche le dialogue et selectionne le fichier 
        File selectedFile = fileChooser.showOpenDialog(mainView.getRoot().getScene().getWindow());

        if (selectedFile != null) {
            // Initialise StringBuilder pour accumuler le contenu du fichier selectionné 
            StringBuilder sb = new StringBuilder();
            BufferedReader br = null;


            try {
                // Initialise le BufferedReader
                br = new BufferedReader(new FileReader(selectedFile));
                String line; 

                // Lecture de chaque lignes du fichier et append au StringBuilder
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n"); 
                }
            } catch (Exception e) {
                e.printStackTrace(); // Imprime le stack trace en cas d'erreur 
            } finally {
                if (br != null){
                    try {
                        br.close();
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
            
            mainView.getTextArea().setText(sb.toString());

            Button replayButton = mainView.getReplayButton();
            replayButton.setDisable(false);
        }
    }
}
