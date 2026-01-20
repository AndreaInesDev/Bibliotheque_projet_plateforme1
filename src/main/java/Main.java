import model.Book;
import repository.Library;
import service.ActivityStack;
import service.Search;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Library library = new Library(100);
    private static ActivityStack activityStack = new ActivityStack();
    private  static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        //menu utilisateur
        while (running) {
            System.out.println("\n--- SYSTEME DE GESTION DE BIBLIOTHEQUE --- ");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Afficher tous les livres");
            System.out.println("3. Rechercher un livre (Titre/ISBN)");
            System.out.println("4. Supprimer un livre");
            System.out.println("5. Mettre à jour un livre (Update)");
            System.out.println("6. Trier par Titre (Bubble Sort)");
            System.out.println("7. Trier par Auteur (Selection Sort)");
            System.out.println("8. Trier par Année (Quicksort)");
            System.out.println("9. Emprunter un livre");
            System.out.println("10. Historique d'emprunt");
            System.out.println("11. Voir les activités récentes");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addBook(); break;
                case 2: library.getAll(); break;
                case 3: searchBook(); break;
                case 4: deleteBook(); break;
                case 5: updateBook(); break;
                case 6:
                    library.bubbleSortByTitle();
                    activityStack.push("Affichage les livre par titre");
                    break;
                case 7:
                    library.selectionSortByAuthor();
                    activityStack.push("Affichage les livre par auteur");
                    break;
                case 8:
                    library.quickSortByYear(0, library.getCount() - 1);
                    System.out.println("affichage des livre par année.");
                    activityStack.push("Tri par année effectué.");
                    break;
                case 9: borrowBook(); break;
                case 10: viewBorrowingHistory(); break;
                case 11: activity(); break;
                case 0: running = false; break;
                default: System.out.println("Choix invalide");
            }
        }
    }

    private static void addBook(){
        System.out.println("Entrez le titre: ");
        String title = scanner.nextLine();

        System.out.println("Auteur");
        String author = scanner.nextLine();

        System.out.println("ISBN");
        String ISBN = scanner.nextLine();

        System.out.println("Year ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Genre ");
        String genre = scanner.nextLine();

        Book newBook = new Book(title, author, ISBN, year, genre);

        library.saveBook(newBook);
        activityStack.push("Livre ajouté: " + title);
        System.out.println("Livre enregistré avec succès");
    }

    private static void searchBook() {
        boolean searching = true;
        while (searching) {
            System.out.println("\n--- PAR TITRE OU PAR ISBN ---");
            System.out.println("1. Par titre");
            System.out.println("2. Par ISBN");
            System.out.println("0. Annuler");
            System.out.print("Choix : ");

            try {
                int choice1 = scanner.nextInt();
                scanner.nextLine();

                switch (choice1) {
                    case 1: parTitre(); break;
                    case 2: parIsbn(); break;
                    case 0: searching = false; break;
                    default: System.out.println("Choix non valide.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre (0, 1 ou 2), pas du texte !" + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void parTitre() {
        System.out.print("Entrez le titre du livre : ");
        String title = scanner.nextLine();
        activityStack.push("Recherche par titre : " + title);

        // Appel à la recherche linéaire
        Book found = Search.linearSearchByTitle(library.getBooks(), library.getCount(), title);

        if (found != null) {
            System.out.println("Livre trouvé : " + found);
        } else {
            System.out.println("Aucun livre trouvé avec ce titre.");
        }
    }
    private static void parIsbn() {
        System.out.print("Entrez l'ISBN du livre : ");
        String isbn = scanner.nextLine();
        activityStack.push("Recherche par ISBN : " + isbn);
        
        Book found = Search.binarySearchByIsbn(library.getBooks(), library.getCount(), isbn);

        if (found != null) {
            System.out.println("Livre trouvé : " + found);
        } else {
            System.out.println("Aucun livre trouvé avec cet ISBN.");
        }
    }



    private static void deleteBook(){
        System.out.println("Entrez l'ISBN du livre à supprimer");
        String ISBN = scanner.nextLine();


        if (library.removeBook(ISBN)){
            activityStack.push("Suppression du livre d'ISBN: " + ISBN );
            System.out.println("Livre supprimé avec succès");
        }else {
            System.out.println("Livre non trouvé");
        }

    }

    private static void activity(){
        System.out.println("---- DERNIERE ACTIVITES ---");
        if (activityStack.isEmpty()){
            System.out.println("Aucune activité ici");
        }else {
            System.out.println("Dernières actions " + activityStack.peek());
        }
    }

    private static void borrowBook() {
        System.out.println("Entrez l'ISBN du livre à emprunter : ");
        String isbn = scanner.nextLine();

        // Trouver le livre
        Book b = null;
        for (int i = 0; i < library.getCount(); i++) {
            if (library.getBooks()[i].getIsbn().equals(isbn)) {
                b = library.getBooks()[i];
                break;
            }
        }

        if (b != null) {
            System.out.println("Nom de l'emprunteur : ");
            String name = scanner.nextLine();
            // On ajoute à la liste chaînée du livre spécifique
            b.getHistory().addBorrower(name);
            activityStack.push("Emprunt de '" + b.getTitle() + "' par " + name);
            System.out.println("Emprunt enregistré ! Historique actuel : " + b.getHistory().getBorrowers());
        } else {
            System.out.println("Livre introuvable.");
        }
    }

    private static void viewBorrowingHistory() {
        System.out.println("Entrez l'ISBN du livre pour voir son historique : ");
        String isbn = scanner.nextLine();

        Book found = null;
        // Recherche du livre par ISBN
        for (int i = 0; i < library.getCount(); i++) {
            if (library.getBooks()[i].getIsbn().equals(isbn)) {
                found = library.getBooks()[i];
                break;
            }
        }

        if (found != null) {
            List<String> history = found.getHistory().getBorrowers();
            if (history.isEmpty()) {
                System.out.println("Aucun emprunt enregistré pour : " + found.getTitle());
            } else {
                System.out.println("Historique des emprunteurs pour '" + found.getTitle() + "' :");
                // On parcourt la liste récupérée de la liste chaînée
                for (String name : history) {
                    System.out.println("- " + name);
                }
            }
            activityStack.push("Consultation historique : " + found.getTitle());
        } else {
            System.out.println("Livre introuvable.");
        }
    }

    private static void updateBook() {
        System.out.print("Entrez l'ISBN du livre à modifier : ");
        String isbn = scanner.nextLine();

        // On demande les nouvelles infos
        System.out.print("Nouveau titre : ");
        String title = scanner.nextLine();
        System.out.print("Nouvel auteur : ");
        String author = scanner.nextLine();
        System.out.print("Nouvelle année : ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nouveau genre : ");
        String genre = scanner.nextLine();

        if (library.updateBook(isbn, title, author, year, genre)) {
            System.out.println("Livre mis à jour !");
            activityStack.push("Mise à jour du livre ISBN: " + isbn);
        } else {
            System.out.println("Livre non trouvé.");
        }
    }
}
