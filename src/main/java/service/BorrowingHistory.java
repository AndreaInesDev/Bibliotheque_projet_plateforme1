package service;

import java.util.ArrayList;
import java.util.List;

public class BorrowingHistory {

    private class Node {
        String borrower;
        Node next;

        Node(String borrower) {
            this.borrower = borrower;
        }
    }

        private Node head;
        //enregistrer un nouvel emprunteur dans l'historique d'un livre.
        public void addBorrower(String borrower) {
            Node newNode = new Node(borrower);

            if (head == null) {
                head = newNode;
                return;
            }

            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        //afficher les emprunteurs
        public List<String> getBorrowers() {
            List<String> borrowers = new ArrayList<>();
            Node current = head;

            while (current != null) {
                borrowers.add(current.borrower);
                current = current.next;
            }

            return borrowers;
        }

}
