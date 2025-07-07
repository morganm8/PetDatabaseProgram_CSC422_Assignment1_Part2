# Pet Database Program
**Author:** Mitchel Morgan  
**Course:** CSC422 – Object-Oriented Software Engineering  
**Instructor:** Dr. Furtney  
**Assignment:** Week 1 – Assignment 1, Part 2  
**Due:** July 6, 2025

---

## Overview

This command-line Java program allows a user to manage a simple pet database. Users can:

- Add new pets by entering a name and age
- View all current pets in a formatted table
- Search pets by name (case-insensitive)
- Search pets by age
- Update a pet's name and age using its ID
- Remove a pet from the list using its ID

The program is structured using object-oriented design principles and utilizes Java’s `ArrayList` class for dynamic data storage.

---

## Structure

- `PetDataBase.java` – Main class with all functionality in a single file
- `Pet` – A static inner class that defines each pet’s name and age

---

## How to Run

1. Clone or download the repo
2. Compile and run using Eclipse or terminal:

```bash
javac src/petdatabase/PetDataBase.java
java petdatabase.PetDataBase
