package repository;

import model.Book;

public class Library {
    private Book[] books;
    private int count;

    public  Library(int size){
        books = new Book[size];
        count = 0 ;
    }

    public  Book[] getBooks(){
        return books;
    }

    public  int getCount(){
        return count ;
    }

    public Book saveBook(Book book) {
        if (count >= books.length) {
            System.out.println("Erreur : La bibliothèque est pleine.");
            return null;
        }
        books[count] = book;
        count++;
        return book;
    }


    //ajout d'un nouveau livre
    public boolean removeBook(String isbn) {
       for (int i = 0; i < count; i++) {
            if (books[i].getIsbn().equals(isbn)) {

                for (int j = i; j < count - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;

    }

    //afficher tous les livres
    public void getAll(){
        if (count == 0){
            System.out.println("Aucun livre disponible.");
        }

        for (int i = 0; i < count; i++){
            System.out.println(books[i]);
        }
    }

    // Algorithme de Tri à Bulles (par Titre)
    public void bubbleSortByTitle() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (books[j].getTitle().compareToIgnoreCase(books[j + 1].getTitle()) > 0) {
                   swap(j, j + 1);
                }
            }
        }
        System.out.println("Bibliothèque triée par titre (Bubble Sort).");
    }

    // faire le tri par année
    public void quickSortByYear(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSortByYear(low, pi - 1);
            quickSortByYear(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = books[high].getPublicationYear();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (books[j].getPublicationYear() < pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        Book temp = books[i];
        books[i] = books[j];
        books[j] = temp;
    }

    //mise à jour du  livre
    public boolean updateBook(String isbn, String newTitle, String newAuthor, int newYear, String newGenre) {
        for (int i = 0; i < count; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                books[i].setTitle(newTitle);
                books[i].setAuthor(newAuthor);
                books[i].setPublicationYear(newYear);
                books[i].setGenre(newGenre);
                return true;
            }
        }
        return false;
    }

    //ranger les livres par ordre amphabetique d'auteur
    public void selectionSortByAuthor() {
        for (int i = 0; i < count - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < count; j++) {
                if (books[j].getAuthor().compareToIgnoreCase(books[minIndex].getAuthor()) < 0) {
                    minIndex = j;
                }
            }
            swap(minIndex, i);
        }
        System.out.println("Bibliothèque triée par auteur (Selection Sort).");
    }



}
