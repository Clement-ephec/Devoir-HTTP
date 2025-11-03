/* Imports */
import java.net.*;
import java.io.*;

public class ServeurHTTPBasique {
    public static void main(String[] args) {
        int port = 8080; // Port d'écoute

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur démarré sur le port " + port);

            // 1. Attendre et accepter la connexion autorisée
            try (Socket clientSocket = serverSocket.accept()) {

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // 2. Lire la ligne de requête
                String requestLine = in.readLine();
                System.out.println("Requête reçue: " + requestLine);

                if (requestLine != null && requestLine.startsWith("GET")) {

                    // 3. Construire et envoyer la réponse HTTP
                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: text/html");
                    out.println("Connection: close");
                    out.println();
                    out.println("<html><body><h1>Reponse du serveur !</h1>");
                    out.println("<p>La requete etait : <b>" + requestLine + "</b></p></body></html>");
                }

            } catch (IOException e) {
                System.err.println("Erreur de communication avec le client: " + e.getMessage());
            }

            System.out.println("Serveur éteint après la première requête.");

        } catch (IOException e) {
            System.err.println("Erreur au démarrage du serveur: " + e.getMessage());
        }
    }
}
