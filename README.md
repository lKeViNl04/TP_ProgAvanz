---

# 🐾 Pokémon Battle System

A Java-based Pokémon battle simulator with a Swing GUI, supporting type-based combat mechanics, trainer management, and persistent data storage using MySQL.

---

## ✅ Prerequisites

### 🗄️ Database Setup

**IMPORTANT:** Before running the application, you must have a MySQL database named `pokemon` configured.

1. Install **MySQL Server** (version 8.0 or higher)
2. Create a database named `pokemon`
3. Run the SQL script located at `src/main/resources/DataBase/Pokemon.sql` to create the required tables

### 🛠 Required Software

* Java 23 or higher
* MySQL 8.0 or higher
* Maven (for dependency management)

---

## ⚙️ Database Configuration

The application connects to MySQL using the following default settings:

* **Host**: `localhost:3306`
* **Database**: `pokemon`
* **Username**: `root`
* **Password**: *(empty)*

To change these values, update the constants in `PokemonImplMysql.java`.

---

## 🚀 Installation & Running the App

1. Clone this repository
2. Set up the MySQL database as described above
3. Run `mvn clean install` to build the project
4. Launch the application via `application.java`

---

## 🎮 Features

* **Turn-Based Battle System**
  Fight using 5 Pokémon types: Water, Fire, Electric, Grass, and Rock, with type effectiveness logic.

* **Trainer Management**
  Includes user registration, login, and trainer profiles.

* **Persistent Storage**
  Stores Pokémon, trainers, and battle data in a MySQL database.

* **Swing GUI**
  User-friendly interface built with Java Swing.

---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── ar/edu/davinci/Backend/   # Business logic and data access
│   │   └── ar/edu/davinci/Frontend/  # Swing GUI
│   └── resources/
│       └── DataBase/                 # SQL setup scripts
└── test/                             # Unit tests
```

---

## 🧪 Testing

Run unit tests with:

```bash
mvn test
```

---
