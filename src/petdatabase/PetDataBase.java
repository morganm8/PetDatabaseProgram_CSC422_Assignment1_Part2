/*
 * Mitchel Morgan
 * CSC422 - Dr. Furtney
 * Week 1 - Assignment 1 - Part 2
 * Due: 06-July-2025
 *
 * Program Description:
 * This version includes searching for pets by name and by age.
 * Users can now filter and view pets based on specific criteria entered at runtime.
 *
 * Sources:
 * Bruegge, B., & Dutoit, A. H. (2010). Object-oriented software engineering: Using UML, patterns, and Java (3rd ed.). Prentice Hall.
 */

package petdatabase;

import java.util.ArrayList;
import java.util.Scanner;

public class PetDataBase {

    // Inner class to represent a Pet object
    static class Pet {
        String name;
        int age;

        Pet(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    // List to store all pets
    private static ArrayList<Pet> petList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Pet Database Program");

        while (running) {
            printMenu();
            System.out.print("\nYour choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear input buffer

            switch (choice) {
                case 1:
                    viewPets();
                    break;
                case 2:
                    addPets(scanner);
                    break;
                case 3:
                    searchByName(scanner);
                    break;
                case 4:
                    searchByAge(scanner);
                    break;
                case 5:
                    running = false;
                    System.out.println("\nGoodbye!");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // Menu options updated to include search features
    public static void printMenu() {
        System.out.println("\nWhat would you like to do?\n");
        System.out.println("1) View all pets");
        System.out.println("2) Add more pets");
        System.out.println("3) Search pets by name");
        System.out.println("4) Search pets by age");
        System.out.println("5) Exit program");
    }

    // Allows the user to add pets
    public static void addPets(Scanner scanner) {
        int addedCount = 0;

        while (true) {
            System.out.print("\nAdd pet (name, age): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("done")) {
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length == 2) {
                String name = parts[0];
                try {
                    int age = Integer.parseInt(parts[1]);
                    petList.add(new Pet(name, age));
                    addedCount++;
                } catch (NumberFormatException e) {
                    System.out.println("\nInvalid age format. Please enter an integer.");
                }
            } else {
                System.out.println("\nInvalid input. Please enter name and age.");
            }
        }

        System.out.println("\n" + addedCount + " pets added.");
    }

    // Displays the list of all pets
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

    // Search pets by name (case-insensitive)
    public static void searchByName(Scanner scanner) {
        System.out.print("\nEnter a name to search: ");
        String nameSearch = scanner.nextLine().toLowerCase(); // lower case for case-insensitive search
        int count = 0;

        System.out.println("\n+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");

        for (int i = 0; i < petList.size(); i++) {
            Pet pet = petList.get(i);
            if (pet.name.toLowerCase().equals(nameSearch)) {
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
            int ageSearch = Integer.parseInt(scanner.nextLine());
            int count = 0;

            System.out.println("\n+----------------------+");
            System.out.println("| ID | NAME      | AGE |");
            System.out.println("+----------------------+");

            for (int i = 0; i < petList.size(); i++) {
                Pet pet = petList.get(i);
                if (pet.age == ageSearch) {
                    System.out.printf("| %2d | %-10s| %3d |\n", i, pet.name, pet.age);
                    count++;
                }
            }

            System.out.println("+----------------------+\n");
            System.out.println(count + " rows in set.");

        } catch (NumberFormatException e) {
            System.out.println("\nInvalid input. Please enter a valid number.");
        }
    }
}