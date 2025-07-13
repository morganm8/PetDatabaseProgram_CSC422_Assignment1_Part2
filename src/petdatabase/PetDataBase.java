/*
 * Mitchel Morgan
 * CSC422 - Dr. Furtney
 * Week 1 - Assignment 1 - Part 2
 * Due: 06-July-2025
 *
 * Program Description:
 * This version includes the ability to add, view, search, update, and remove pets.
 * It also loads and saves pet data from a text file and includes full error handling.
 * Milestone 4 adds input validation, limits, and friendly error messaging.
 *
 * Sources:
 * Bruegge, B., & Dutoit, A. H. (2010). Object-oriented software engineering: Using UML, patterns, and Java (3rd ed.). Prentice Hall.
 */

package petdatabase;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class PetDataBase {

    // Inner class that defines a Pet with name and age
    static class Pet {
        String name;
        int age;

        Pet(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    private static final int MAX_PETS = 5;           // Max allowed pets
    private static final String DATA_FILE = "pets.txt"; // File to load/save pet data
    private static ArrayList<Pet> petList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Mitch Morgan - morganm8@csp.edu");
        System.out.println("I certify that this is my own work.\n");
        System.out.println("Pet Database Program");

        loadFromFile();  // Load saved pets from file

        while (running) {
            printMenu();
            System.out.print("\nYour choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewPets();
                    break;
                case "2":
                    addPets(scanner);
                    break;
                case "3":
                    updatePet(scanner);
                    break;
                case "4":
                    removePet(scanner);
                    break;
                case "5":
                    searchByName(scanner);
                    break;
                case "6":
                    searchByAge(scanner);
                    break;
                case "7":
                    saveToFile();  // Save pets before exiting
                    System.out.println("\nGoodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid choice. Please enter a number 1–7.");
            }
        }

        scanner.close();
    }

    // Display main menu
    public static void printMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("Type 'done' to finish when entering pets.\n");
        System.out.println("1) View all pets");
        System.out.println("2) Add more pets");
        System.out.println("3) Update an existing pet");
        System.out.println("4) Remove an existing pet");
        System.out.println("5) Search pets by name");
        System.out.println("6) Search pets by age");
        System.out.println("7) Exit program");
    }

    // Add pets (name and age), with full input and error validation
    public static void addPets(Scanner scanner) {
        int addedCount = 0;

        while (true) {
            if (petList.size() >= MAX_PETS) {
                System.out.println("\nError: Database is full.");
                break;
            }

            System.out.print("\nAdd pet (name, age): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("done")) {
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("\nError: Invalid input. Use: Name Age");
                continue;
            }

            String name = parts[0];
            try {
                int age = Integer.parseInt(parts[1]);
                if (age < 1 || age > 20) {
                    System.out.println("\nError: " + age + " is not a valid age (must be 1–20).");
                } else {
                    petList.add(new Pet(name, age));
                    addedCount++;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nError: " + parts[1] + " is not a valid number.");
            }
        }

        System.out.println("\n" + addedCount + " pets added.");
    }

    // View all pets in a table
    public static void viewPets() {
        System.out.println("\n+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");

        for (int i = 0; i < petList.size(); i++) {
            Pet pet = petList.get(i);
            System.out.printf("| %2d | %-10s| %3d |\n", i, pet.name, pet.age);
        }

        System.out.println("+----------------------+\n");
        System.out.println(petList.size() + " rows in set.");
    }

    // Search pets by name
    public static void searchByName(Scanner scanner) {
        System.out.print("\nEnter a name to search: ");
        String search = scanner.nextLine().toLowerCase();
        int count = 0;

        System.out.println("\n+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");

        for (int i = 0; i < petList.size(); i++) {
            Pet pet = petList.get(i);
            if (pet.name.toLowerCase().equals(search)) {
                System.out.printf("| %2d | %-10s| %3d |\n", i, pet.name, pet.age);
                count++;
            }
        }

        System.out.println("+----------------------+\n");
        System.out.println(count + " rows in set.");
    }

    // Search pets by age
    public static void searchByAge(Scanner scanner) {
        System.out.print("\nEnter age to search: ");
        try {
            int age = Integer.parseInt(scanner.nextLine());
            int count = 0;

            System.out.println("\n+----------------------+");
            System.out.println("| ID | NAME      | AGE |");
            System.out.println("+----------------------+");

            for (int i = 0; i < petList.size(); i++) {
                Pet pet = petList.get(i);
                if (pet.age == age) {
                    System.out.printf("| %2d | %-10s| %3d |\n", i, pet.name, pet.age);
                    count++;
                }
            }

            System.out.println("+----------------------+\n");
            System.out.println(count + " rows in set.");
        } catch (NumberFormatException e) {
            System.out.println("\nError: Please enter a valid number.");
        }
    }

    // Update an existing pet
    public static void updatePet(Scanner scanner) {
        viewPets();
        System.out.print("\nEnter the ID of the pet to update: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());

            if (id < 0 || id >= petList.size()) {
                System.out.println("\nError: ID " + id + " does not exist.");
                return;
            }

            Pet pet = petList.get(id);
            System.out.print("\nEnter new name and age: ");
            String[] input = scanner.nextLine().split(" ");

            if (input.length != 2) {
                System.out.println("\nError: Invalid input. Use: name age");
                return;
            }

            String newName = input[0];
            int newAge = Integer.parseInt(input[1]);

            if (newAge < 1 || newAge > 20) {
                System.out.println("\nError: Age must be between 1 and 20.");
                return;
            }

            System.out.printf("%s %d changed to %s %d\n", pet.name, pet.age, newName, newAge);
            pet.name = newName;
            pet.age = newAge;

        } catch (NumberFormatException e) {
            System.out.println("\nError: Please enter a valid number.");
        }
    }

    // Remove a pet by ID
    public static void removePet(Scanner scanner) {
        viewPets();
        System.out.print("\nEnter the ID of the pet to remove: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());

            if (id < 0 || id >= petList.size()) {
                System.out.println("\nError: ID " + id + " does not exist.");
                return;
            }

            Pet removed = petList.remove(id);
            System.out.printf("\n%s %d is removed.\n", removed.name, removed.age);
        } catch (NumberFormatException e) {
            System.out.println("\nError: Please enter a valid number.");
        }
    }

    // Load pet data from pets.txt
    public static void loadFromFile() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine() && petList.size() < MAX_PETS) {
                    String line = fileScanner.nextLine().trim();
                    String[] parts = line.split(" ");
                    if (parts.length == 2) {
                        String name = parts[0];
                        int age = Integer.parseInt(parts[1]);
                        if (age >= 1 && age <= 20) {
                            petList.add(new Pet(name, age));
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("\nError loading pet data from file.");
            }
        }
    }

    // Save pet data to pets.txt
    public static void saveToFile() {
        try (PrintWriter writer = new PrintWriter(DATA_FILE)) {
            for (Pet pet : petList) {
                writer.println(pet.name + " " + pet.age);
            }
        } catch (IOException e) {
            System.out.println("\nError saving pet data to file.");
        }
    }
}
