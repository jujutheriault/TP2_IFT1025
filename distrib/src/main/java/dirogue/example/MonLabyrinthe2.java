/**
 * IFT1025 - Travail Pratique 2 
 * 13 décembre 2024 
 * 
 * Justine Theriault - 20231918
 * Heyun Liu - 20269538
 */
package dirogue.example;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dirogue.example.code_squelette.*;

/**
 * La classe MonLabyrinthe2 implémente l'interface Labyrinthe et Serializable.
 * Elle représente un labyrinthe composé de pièces et de corridors qui les
 * relient.
 * Cette classe gère les pièces, les corridors, et fournit des méthodes pour
 * ajouter
 * de nouvelles pièces, relier des pièces par des corridors, obtenir des
 * informations
 * sur les pièces connectées et vérifier l'existence de corridors entre des
 * pièces.
 */
public class MonLabyrinthe2 implements Labyrinthe, Serializable {
    private List<Piece> pieces;
    private Map<Integer, List<Integer>> adjList;

     /**
     * Constructeur de la classe MonLabyrinthe2 initialisant la liste des pièces et l'adjacence.
     */
    public MonLabyrinthe2() {
        pieces = new ArrayList<>();
        adjList = new HashMap<>();
    }

    public Piece[] getPieces() {
        return pieces.toArray(new Piece[0]);
    }

    public int nombreDePieces() {
        return pieces.size();
    }

    public void ajouteEntree(Exterieur out, Piece e) {
        pieces.add(out);
        pieces.add(e);
        addEdge(out, e);
    }

    public void ajouteCorridor(Piece e1, Piece e2) {
        if (getPieceByID(e1.getID()) == null)
            pieces.add(e1);

        if (getPieceByID(e2.getID()) == null)
            pieces.add(e2);

        addEdge(e1, e2);
    }

    /**
     * Ajoute un corridor entre eux pièces identifiées par leur ID. 
     * 
     * @param e1ID ID de la première pièce 
     * @param e2ID ID de la deuxième pièce 
     * @throws PieceNotFoundException Si l'une des pièces n'existe pas.
     */
    public void ajouteCorridor(int e1ID, int e2ID) throws PieceNotFoundException {
        Piece p1 = getPieceByID(e1ID);
        Piece p2 = getPieceByID(e2ID);
        // Vérifie que les pièces existent 
        if(p1 == null || p2 == null){
            throw new PieceNotFoundException();
        }
        addEdge(p1, p2); // Ajoute un corridor entre les pièces 
    }

    /**
     * Méthode pour ajouter une nouvelle pièce au labyrinthe.
     *
     * @param e La pièce à ajouter.
     * @return L'index de la pièce ajoutée.
     */
    public int ajoutePiece(Piece e) {
        if (!pieces.contains(e)) {
            pieces.add(e);
        }
        return pieces.indexOf(e);
    }

    public boolean existeCorridorEntre(Piece e1, Piece e2) {
        return adjList.containsKey(e1.getID()) && adjList.get(e1.getID()).contains(e2.getID());
    }

    /**
     * Méthode pour obtenir les pièces connectées à une pièce spécifique.
     *
     * @param e La pièce dont les pièces connectées sont recherchées.
     * @return Un tableau de pièces connectées à la pièce donnée.
     */
    public Piece[] getPiecesConnectees(Piece e) {
        List<Piece> pieceConnected = new ArrayList<>(); // Liste qui stocke les pièces connectées à la pièce actuelle 
        int nb = 0;

        // Vérifie si la pièce actuelle possède des connexions dans la liste 
        if(adjList.containsKey(e.getID())){

            // Parcourt les ID des pièces connectées à la pièce actuelle 
            for(int i = 0; i < adjList.get(e.getID()).size(); i++ ){
                List<Integer> id = adjList.get(e.getID());

                // Pour chaque ID dans la liste 
                for(int j : id){
                    nb ++; // Incrémente le nombre de pièces connectées 
                    pieceConnected.add(getPieceByID(j)); // Ajoute la pièce actuelle à la liste des pièces connectées 
                }
            }
        }
        return pieceConnected.toArray(new Piece[nb]);  // Convertit et retourne un tableau de la liste des pièces connectées 
    }

    private void addEdge(Piece e1, Piece e2) {
        adjList.computeIfAbsent(e1.getID(), k -> new ArrayList<>()).add(e2.getID());
        adjList.computeIfAbsent(e2.getID(), k -> new ArrayList<>()).add(e1.getID());
    }

    private Piece getPieceByID(int ID) {
        for (Piece piece : pieces) {
            if (piece.getID() == ID) {
                return piece;
            }
        }
        return null;
    }
}
