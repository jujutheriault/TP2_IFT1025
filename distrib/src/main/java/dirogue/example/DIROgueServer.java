/**
 * IFT1025 - Travail Pratique 2 
 * 13 décembre 2024 
 * 
 * Justine Theriault - 20231918
 * Heyun Li - 20269538
 */
package dirogue.example;

import java.io.IOException;

import dirogue.example.code_squelette.*;

/**
 * Classe représentant le serveur pour l'application DIROgue.
 * Ce serveur écoute les commandes provenant du client, telles que la création
 * de pièces, l'ajout de corridors, la fin de la création du labyrinthe, la
 * sauvegarde du rapport d'aventure, etc.
 */
public class DIROgueServer {
	static boolean exterieurAjoute = false;
	static MonLabyrinthe2 l = new MonLabyrinthe2();
	static MonAventure m = null;

	/**
	 * 
	 * Méthode principale qui démarre le serveur DIROgueServer
	 * 
	 * @param args Arguents de la ligne de commande
	 */
	public static void main(String[] args) {

		try {
			Server s = new Server(1370);

			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("piece")) {
					if (cmdArgs.length == 2) {
						int id = Integer.parseInt(cmdArgs[0]);
						RencontreType type = RencontreType.valueOf(cmdArgs[1].toUpperCase());
						if (!exterieurAjoute) {
							l.ajouteEntree(Exterieur.getExterieur(), new Piece(id, type));
							exterieurAjoute = true;
						} else {
							l.ajoutePiece(new Piece(id, type));
						}
					}
				}
			});

			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("CORRIDORS")) {
					System.out.println("CORRIDORS commande recue...");
				}
			});

			// Gestionnaire d'évènements pour la commande "corridor" 
			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("corridor")) {
					// Creer un ID pour les pièces 
					int e1ID = Integer.parseInt(cmdArgs[0]);
					int e2ID = Integer.parseInt(cmdArgs[1]);
					try {
						// Ajoute un corridor entre les deux pièces identifiées 
						l.ajouteCorridor(e1ID, e2ID);
					} catch (PieceNotFoundException e){
						System.out.println("Les pièces données n'existent pas.");
					}
				}
			});

			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("FIN")) {
					System.out.println("FIN commande recue...");
					m = new MonAventure(l);
				}
			});

			// Gestionnaire d'évenements pour la commande "save" 
			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("save")) {
					if(cmdArgs.length > 0){
						// Prend le chemin du fichier à sauvegarder
						String path = cmdArgs[0];
						try{
							// Sauvegarde le rapport de l'aventure 
							m.sauvegarderRapport(path);
						} catch (IOException e){
							System.out.println("Erreur lors de la sauvegarde du rapport.");
						}
					}
				}
			});
			s.listen();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
