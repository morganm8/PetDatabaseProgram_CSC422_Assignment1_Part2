/*
 * Mitchel Morgan
 * CSC422 - Dr. Furtney
 * Week 1 - Assignment 1 - Part 2
 * Due: 06-July-2025
 *
 * Program Description:
 * This final version includes the ability to add, view, search, update, and remove pets.
 * Users can now maintain a simple pet database from the command line.
 *
 * Sources:
 * Bruegge, B., & Dutoit, A. H. (2010). Object-oriented software engineering: Using UML, patterns, and Java (3rd ed.). Prentice Hall.
 * 
 */

package petdatabase;

import java.util.ArrayList;
import java.util.Scanner;

public class PetDataBase {

    // This inner class defines a Pet with a name and an age.
    static class Pet {
        String name;
        int age;

        // Constructor to initialize a Pet object with a name and age.
        Pet(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    // This list holds all the Pet objects added by the user.
    private static ArrayList<Pet> petList = new ArrayList<>();

    public static void main(String[] args) {
        // Create a Scanner object to read user input from the console.
        Scanner scanner = new Scanner(System.in);

        // A boolean variable to control the main program loop.
        boolean running = true;

        // Display student and program information.
        System.out.println("Mitch Morgan - morganm8@csp.edu");
        System.out.println("I certify that this is my own work.\n");
        System.out.println("Pet Database Program");

        // This loop continues to run until the user chooses to exit.
        while (running) {
            // Display the main menu.
            printMenu();

            // Prompt the user to choose an action from the menu.
            System.out.print("\nYour choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the input buffer.

            // Use a switch statement to determine which method to call based on the user's choice.
            switch (choice) {
                case 1:
                    viewPets(); // View all pets.
                    break;
                case 2:
                    addPets(scanner); // Add new pets.
                    break;
                case 3:
                    updatePet(scanner); // Update an existing pet.
                    break;
                case 4:
                    removePet(scanner); // Remove a pet.
                    break;
                case 5:
                    searchByName(scanner); // Search for pets by name.
                    break;
                case 6:
                    searchByAge(scanner); // Search for pets by age.
                    break;
                case 7:
                    running = false; // Exit the program.
                    System.out.println("\nGoodbye!");
                    break;
                default:
                    // Handle any invalid menu choices.
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }

        // Close the scanner after the loop ends.
        scanner.close();
    }

    // This method displays the list of options that the user can choose from.
    public static void printMenu() {
        System.out.println("\nWhat would you like to do?\n");
        System.out.println("Type" + " 'done'" + " to exit the loop when entering. updating, or removing your pet data.\n");
        System.out.println("1) View all pets");
        System.out.println("2) Add more pets");
        System.out.println("3) Update an existing pet");
        System.out.println("4) Remove an existing pet");
        System.out.println("5) Search pets by name");
        System.out.println("6) Search pets by age");
        System.out.println("7) Exit program");
    }

    // This method allows the user to add multiple pets by entering their name and age.
    public static void addPets(Scanner scanner) {
        int addedCount = 0; // Keeps track of how many pets were added.

        while (true) {
            // Prompt the user to enter a pet's name and age.
            System.out.print("\nAdd pet (name, age): ");
            String input = scanner.nextLine();

            // If the user types "done", exit the loop.
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            // Split the input into name and age.
            String[] parts = input.split(" ");
            if (parts.length == 2) {
                String name = parts[0];
                try {
                    int age = Integer.parseInt(parts[1]); // Convert age to an integer.
                    petList.add(new Pet(name, age)); // Add a new Pet object to the list.
                    addedCount++;
                } catch (NumberFormatException e) {
                    // Handle input that isn't a valid number.
                    System.out.println("\nInvalid age. Please enter a whole number.");
                }
            } else {
                // Handle improperly formatted input.
                System.out.println("\nInvalid format. Use: name age");
            }
        }

        // Display how many pets were added.
        System.out.println("\n" + addedCount + " pets added.");
    }

    // This method prints out all pets in a table format.
    public static void viewPets() {
        System.out.println("\n+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");

        // Loop through each pet and print their details.
        for (int i = 0; i < petList.size(); i++) {
            Pet pet = petList.get(i);
            System.out.printf("| %2d | %-10s| %3d |\n", i, pet.name, pet.age);
        }

        System.out.println("+----------------------+\n");
        System.out.println(petList.size() + " rows in set.");
    }

    // This method allows the user to search for pets by their name.
    public static void searchByName(Scanner scanner) {
        // Prompt the user to enter the name to search for.
        System.out.print("\nEnter a name to search: ");
        String nameSearch = scanner.nextLine().toLowerCase(); // Convert input to lowercase for case-insensitive comparison.
        int count = 0;

        System.out.println("\n+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");

        // Loop through the pet list and print matches.
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

    // This method allows the user to search for pets by a specific age.
    public static void searchByAge(Scanner scanner) {
        // Prompt the user to enter an age to search for.
        System.out.print("\nEnter age to search: ");
        try {
            int ageSearch = Integer.parseInt(scanner.nextLine()); // Convert the input to an integer.
            int count = 0;

            System.out.println("\n+----------------------+");
            System.out.println("| ID | NAME      | AGE |");
            System.out.println("+----------------------+");

            // Loop through the pet list and print matches.
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
            // Handle input that isn't a valid number.
            System.out.println("\nInvalid input. Please enter a valid number.");
        }
    }

    // This method allows the user to update an existing pet's name and age.
    public static void updatePet(Scanner scanner) {
        viewPets(); // Display the list of pets to choose from.

        // Prompt the user to enter the ID of the pet to update.
        System.out.print("\nEnter the ID of the pet you want to update: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());

            if (id >= 0 && id < petList.size()) {
                Pet pet = petList.get(id); // Get the selected pet.
                System.out.print("\nEnter new name and age: \n");
                String[] input = scanner.nextLine().split(" ");

                if (input.length == 2) {
                    String newName = input[0];
                    int newAge = Integer.parseInt(input[1]);

                    // Print the change and update the pet’s info.
                    System.out.printf("%s %d changed to %s %d\n", pet.name, pet.age, newName, newAge);
                    pet.name = newName;
                    pet.age = newAge;
                } else {
                    // Handle improper input formatting.
                    System.out.println("\nInvalid input. Use: name age");
                }
            } else {
                // Handle invalid ID selection.
                System.out.println("\nInvalid ID.");
            }

        } catch (NumberFormatException e) {
            // Handle non-numeric input.
            System.out.println("\nPlease enter a valid number.");
        }
    }

    // This method allows the user to remove a pet from the list using its ID.
    public static void removePet(Scanner scanner) {
        viewPets(); // Show the current pet list.

        // Prompt the user to select a pet by ID.
        System.out.print("\nEnter the ID of the pet to remove: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());

            if (id >= 0 && id < petList.size()) {
                Pet removed = petList.remove(id); // Remove the selected pet from the list.
                System.out.printf("\n%s %d is removed.\n", removed.name, removed.age);
            } else {
                // Handle invalid ID input.
                System.out.println("\nInvalid ID.");
            }

        } catch (NumberFormatException e) {
            // Handle input that is not a valid number.
            System.out.println("\nPlease enter a valid number.");
        }
    }
}