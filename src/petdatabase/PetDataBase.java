/*
 * Mitchel Morgan
 * CSC422 - Dr. Furtney
 * Week 1 - Assignment 1 - Part 2
 * Due: 06-July-2025
 *
 * Program Description:
 * This program allows a user to add and view pet information (name and age).
 * The user can add multiple pets and then view them in a formatted table.
 *
 * Sources:
 * Bruegge, B., & Dutoit, A. H. (2010). Object-oriented software engineering: Using UML, patterns, and Java (3rd ed.). Prentice Hall.
 * 
 */

package petdatabase;

import java.util.ArrayList;
import java.util.Scanner;

public class PetDataBase {
    
    // This inner class represents a Pet object with name and age
    static class Pet {
        String name;
        int age;

        // Constructor to create a new Pet object
        Pet(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    // This ArrayList stores all the pets the user adds
    private static ArrayList<Pet> petList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner to read user input
        boolean running = true;

        System.out.println("Pet Database Program");

        // Main program loop: keeps running until user chooses to exit
        while (running) {
            printMenu(); // Display the menu options
            System.out.print("\nYour choice: ");
            int choice = scanner.nextInt(); // Read user menu choice
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    viewPets(); // Option to view pets
                    break;
                case 2:
                    addPets(scanner); // Option to add pets
                    break;
                case 3:
                    running = false; // Exit the program
                    System.out.println("\nGoodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close(); // Close scanner when program ends
    }

    // Prints the main menu options
    public static void printMenu() {
        System.out.println("\nWhat would you like to do?\n");
        System.out.println("1) View all pets");
        System.out.println("2) Add more pets");
        System.out.println("3) Exit program");
    }

    // This method allows the user to add new pets until they type "done"
    public static void addPets(Scanner scanner) {
        int addedCount = 0;

        while (true) {
            System.out.print("\nAdd pet (name, age): ");
            String input = scanner.nextLine();

            // If the user types "done", we stop adding pets
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length == 2) {
                String name = parts[0];
                try {
                    int age = Integer.parseInt(parts[1]);
                    petList.add(new Pet(name, age)); // Add new pet to list
                    addedCount++;
                } catch (NumberFormatException e) {
                    System.out.println("\nInvalid age format. Please enter an integer.");
                }
            } else {
                System.out.println("\nInvalid input. Please enter name and age.");
            }
        }

        System.out.println("\n"+addedCount + " pets added.");
    }

    // This method prints the formatted table of all pets
    public static void viewPets() {
        System.out.println("\n+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");

        for (int i = 0; i < petList.size(); i++) {
            Pet pet = petList.get(i);
            // Print using formatted string (ID, name, age)
            System.out.printf("| %2d | %-10s| %3d |\n", i, pet.name, pet.age);
        }

        System.out.println("+----------------------+\n");
        System.out.println(petList.size() + " rows in set.");
    }
}
