package service;

import model.Book;

public class Search {

    // RECHERCHE LINÉAIRE
    public static Book linearSearchByTitle(Book[] books, int count, String title) {
        for (int i = 0; i < count; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                return books[i];
            }
        }
        return null;
    }

    // RECHERCHE BINAIRE
    public static Book binarySearchByIsbn(Book[] books, int count, String isbn) {
        int low = 0;
        int high = count - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int res = books[mid].getIsbn().compareTo(isbn);
            if (res == 0) return books[mid];
            if (res < 0) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }
}
