# **Bank Account Kata**

## **Description**
Ce projet est une implémentation de l'exercice *Bank Account Kata*, développé en **Spring Boot** pour le backend. Il répond aux besoins exprimés dans les User Stories, permettant des opérations de dépôt, de retrait et de consultation d'un relevé bancaire.

---

## **Fonctionnalités**
- **Dépôt** : Permet à l'utilisateur de déposer de l'argent sur son compte.
- **Retrait** : Permet à l'utilisateur de retirer de l'argent de son compte (validation des fonds disponibles incluse).
- **Relevé de compte** : Affiche l'historique des transactions (date, type, montant, solde).

---

## **Prérequis**
- **Java 17+**
- **Maven**
- Un IDE compatible avec Java (IntelliJ IDEA, Eclipse, etc.)
- **Spring Boot** (inclus dans les dépendances Maven)

---

## **Installation**

1. Clonez ce dépôt :
   ```bash
   git clone https://github.com/votre-utilisateur/bank-account-kata.git
   ```
2.	Accédez au dossier du projet :
   ```bash
   cd bank-account-kata
   ```
3.	Installez les dépendances Maven :
   ```bash
   mvn clean install
   ```
4.	Lancez l’application Spring Boot :
   ```bash
   mvn spring-boot:run
   ```
---

## **Endpoints de l’API**
1. **Dépôt d’argent**
- **URL :** /api/account/deposit
- **Méthode :** POST
- **Body :**
    ```josn
   {
       "amount": 100.0
    }
   ```

- **Réponse :**
  - Succès : 200 OK - “Deposit successful”
  - Erreur : 400 Bad Request - Amount must be greater than 0
  
---

2. **Retrait d’argent**
- **URL :** /api/account/withdraw
- **Méthode :** POST
- **Body :**
    ```josn
   {
       "amount": 50.0
    }
   ```

- **Réponse :**
    - Succès : 200 OK - “Withdrawal successful”
    - Erreur : 400 Bad Request - Insufficient balance

---

2. **Consultation du relevé de compte**
- **URL :** /api/account/statement
- **Méthode :** GET
- **Body :**
    ```josn
   [
        {
          "date": "2024-12-23",
          "operationType": "DEPOSIT",
          "amount": 200.0,
          "balance": 200.0
        },
        {
          "date": "2024-12-23",
          "operationType": "WITHDRAW",
          "amount": 50.0,
          "balance": 150.0
        }
  ]
   ```

---

## **Tests**

Le projet inclut des tests unitaires pour valider les fonctionnalités principales. Pour exécuter les tests :
```bash
mvn test
```