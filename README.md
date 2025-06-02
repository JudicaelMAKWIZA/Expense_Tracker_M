# 📱 Expense Tracker – Projet de Génie Logiciel

**Application Android de gestion des dépenses personnelles, conçue comme exercice pratique d'application des principes de génie logiciel.**

## 🧠 Objectif

Mettre en œuvre les concepts clés de l’ingénierie logicielle (architecture en couches, séparation des responsabilités, design patterns, documentation, versionnement Git) dans une application concrète de suivi des dépenses.

## 🏗️ Architecture & Design

- **Architecture :** MVVM (Model – View – ViewModel)
- **Persistance :** Room (abstraction de SQLite) + DAO + Repository Pattern
- **Injection :** ViewModelFactory
- **Design Patterns utilisés :**
  - Singleton (instance unique de Room Database)
  - Repository (abstraction de la source de données)
  - Factory (création des ViewModels)
  - DAO (encapsulation des requêtes)
- **Principes SOLID** et autres : SRP, OCP, DRY, KISS…

## ⚙️ Stack Technique

| Composant         | Technologie             |
|-------------------|-------------------------|
| Langage           | Kotlin                  |
| UI                | Jetpack Compose         |
| Architecture      | MVVM                    |
| Persistance       | Room + SQLite           |
| Versionnement     | Git + GitHub            |
| Test              | JUnit + coroutines-test |

## 📂 Structure du projet

```
app/
 ├── data/
 │   ├── dao/
 │   ├── ExpenseDatabase.kt
 │   └── repository/
 ├── model/
 │   └── ExpenseEntity.kt
 ├── feature_ui/
 │   ├── home/
 │   ├── add/
 │   └── common/
 ├── utils/
 └── MainActivity.kt
```

## 🚀 Lancer le projet

### Prérequis

- Android Studio Giraffe (ou version ultérieure)
- Android SDK 33+
- Gradle 8.x
- Un émulateur ou un appareil physique (API 26 minimum)

### Installation

1. Cloner le dépôt :
   ```bash
   git clone https://github.com/JudicaelMAKWIZA/Expense_Tracker_M.git
   ```
2. Ouvrir le projet dans Android Studio.
3. Synchroniser le projet (Gradle Sync).
4. Lancer l'application (`Run > app`).

## 🔍 Fonctionnalités techniques

- Ajout d'une transaction avec :
  - montant, description, catégorie, type (solde/dépense), date automatique
- Calcul automatique :
  - solde total (`calculateBalance`)
  - total par type (`calculateTotalByType`)
- Liste dynamique des transactions (LiveData/Flow)
- Architecture testable (logique extraite dans le ViewModel)
- Données persistées localement via Room

## 🧪 Tests

- Tests unitaires de fonctions clés (`HomeViewModelTest.kt`)
  - `calculateBalance`
  - `calculateTotalByType`
- Testés via `JUnit4`, coroutines `Dispatchers.setMain` (avec `kotlinx-coroutines-test`)
- Résultats des tests disponibles dans `build/reports/tests/`

## 📈 Améliorations futures

- Suppression / édition des dépenses
- Affichage de graphiques (MPAndroidChart ou Compose Chart)
- Sauvegarde distante (Firebase ou backend REST)
- Authentification utilisateur
- Mode sombre

## 📎 Liens utiles

- 📘 Rapport PDF : [`Projet_Genie_Logiciel.pdf`](./Projet_Genie_Logiciel.pdf)
- 📑 Guide utilisateur (inclus dans le rapport)
- 🧾 Dépôt officiel : https://github.com/JudicaelMAKWIZA/Expense_Tracker_M/

## 🪪 Licence

Ce projet est open-source sous licence MIT.

---

> Ce dépôt représente l’implémentation complète d’un projet académique mobilisant les concepts de génie logiciel, tant au niveau de l’architecture logicielle que des pratiques de développement rigoureuses.
