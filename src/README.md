# Système de Gestion de Bibliothèque (COMC-06)

Ce projet est une application de console Java permettant de gérer une collection de livres, de suivre l'historique des emprunts et d'enregistrer les activités récentes.

## 🚀 Fonctionnalités
* **Gestion des livres** : Ajouter, supprimer, mettre à jour et afficher les livres.
* **Recherche avancée** : Recherche par titre (Linéaire) et par ISBN (Binaire).
* **Tri de la collection** : Tri par titre, auteur ou année de publication.
* **Historique d'emprunt** : Suivi chronologique des emprunteurs pour chaque livre.
* **Journal d'activité** : Consultation des dernières actions effectuées.

---

## 🛠️ Structures de Données Utilisées
Le projet met en œuvre trois structures de données fondamentales :
1. **Tableaux (Arrays)** : Utilisés pour stocker la collection principale de livres (accès rapide par index).
2. **Listes Chaînées (Linked Lists)** : Utilisées pour l'historique d'emprunt de chaque livre (permet une croissance dynamique sans limite de taille).
3. **Pile (Stack)** : Utilisée pour le journal d'activité (principe LIFO - Dernier Entré, Premier Sorti).

---

## 📊 Algorithmes Implémentés

### Algorithmes de Tri
* **Bubble Sort (Tri à bulles)** : Utilisé pour le tri par **titre**.
* **Selection Sort (Tri par sélection)** : Utilisé pour le tri par **auteur**.
* **Quicksort (Tri rapide)** : Utilisé pour le tri par **année de publication** (très performant sur de grandes listes).

### Algorithmes de Recherche
* **Recherche Linéaire** : Utilisée pour trouver un livre par son titre.
* **Recherche Binaire** : Utilisée pour la recherche par ISBN (optimisée, nécessite un tableau trié).

---

## 💻 Installation et Utilisation
1. Assurez-vous d'avoir le **JDK 8** ou une version supérieure installé.
2. Compilez les fichiers :
   ```bash
   javac Main.java

3. Lancez l'application :
   ```bash
   java Main

✒️ Auteur

    [OTELE Andréa inès] - Étudiant en developpement web