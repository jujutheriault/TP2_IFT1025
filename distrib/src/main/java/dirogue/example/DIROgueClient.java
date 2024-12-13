/**
 * IFT1025 - Travail Pratique 2 
 * 13 décembre 2024 
 * 
 * Justine Theriault - 20231918
 * Heyun Li - 20269538
 */
package dirogue.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Classe représentant un client pour l'application DIROgue. Ce client se
 * connecte à un serveur spécifique et peut envoyer des commandes pour charger,
 * sauvegarder des fichiers ou quitter l'application.
 */
public class DIROgueClient {
	/**
	 *  
	 * Méthode principale qui établit la connexion au serveur et gère l'interaction avec l'utilisateurs pour envoyer 
	 * des commandes. 
	 * 
	 * @param args Arguments de la ligne de commande 
	 */
	public static void main(String[] args) {
		String serverAddress = "127.0.0.1"; // Adresse du serveur 
		int serverPort = 1370; // Port du serveur 

		Socket socket = null;
		PrintWriter out = null; // utilisé pour écrire dans le socket avec des commandes comme println()

		try {
			socket = new Socket(serverAddress, serverPort); // Connexion au Serveur avec le port 
			out = new PrintWriter(socket.getOutputStream(), true); // Autoflush activé pour envoyé les données immédiatement

			Scanner scanner = new Scanner(System.in); // Scanner qui lit les entrées de l'utilisateur 
			String input;

			while (true) {
				System.out.println("Entrer une commande (load, save, exit):");
				input = scanner.nextLine().trim();

				if (input.equals("load")) {
					System.out.println("Entrez le chemin du fichier que vous souhaitez charger :");
					String filePath = scanner.nextLine().trim();

					// Lecture du fichier donné par l'utilisateur selon le chemin spécifié
					try (BufferedReader filereader = new BufferedReader(new FileReader(filePath))) {
						String line;
						// Boucle qui parcourt le fichier ligne par ligne 
						while ((line = filereader.readLine()) != null) {
							out.println(line); // Envoie les commandes ligne par ligne 
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (input.equals("save")) {
					System.out.println(" Entrez le chemin où vous voulez sauvegarder le rapport :");
					var reportPath = scanner.nextLine().trim();
					out.println(input + " " + reportPath);

				} else if (input.equals("exit")) {
					out.println(input);
					break;
				} else {
					System.out.println("Commande non valide. Veuillez entrer 'load', 'save' ou 'exit'.");
				}
			}

			System.out.println("Sortie du programme.");
			scanner.close(); // Fermeture du scanner 
		}catch (IOException e){
			e.printStackTrace();
		}

		// Fermeture des ressources 
        if (out != null) {
            out.close();
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
