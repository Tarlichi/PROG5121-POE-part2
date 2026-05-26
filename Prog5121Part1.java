package com.mycompany.prog5121;

import java.util.Scanner;

public class Prog5121Part1 {

    // Username validation
    public boolean checkUserName(String userName) {
        return userName != null && userName.length() >= 1 && userName.length() <= 5 && userName.matches("^[a-zA-Z]+$");
    }

    // Password validation
    public boolean checkPassword(String password) {
        if (password.length() < 8)
            return false;

        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char checkLetters = password.charAt(i);

            if (Character.isUpperCase(checkLetters)) {
                hasUpper = true;
            } else if (Character.isDigit(checkLetters)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(checkLetters)) {
                hasSpecial = true;
            }
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    // Phone validation (+27 format)
    public boolean checkCellphone(String cellphone) {
        return cellphone != null &&
               cellphone.startsWith("+27") &&
               cellphone.length() == 12 &&
               cellphone.substring(1).matches("\\d+");
    }
    
    // Register new user
    public void registerUser(Scanner input) {
        System.out.println("\n=== USER REGISTRATION ===\n");
        
        // Username
        String username;
        do {
            System.out.print("Enter username (max 5 letters only): ");
            username = input.nextLine();
            
            if (!checkUserName(username)) {
                System.out.println(" Username is invalid! Must be 1-5 letters only.");
            }
        } while (!checkUserName(username));
        
        System.out.println("✅ Username is valid");
        
        // Password
        String password;
        do {
            System.out.print("Enter password (min 8 chars, uppercase, number, special char): ");
            password = input.nextLine();
            
            if (!checkPassword(password)) {
                System.out.println(" Password is weak! Must have at least 8 characters, one uppercase, one number, and one special character.");
            }
        } while (!checkPassword(password));
        
        System.out.println(" Password is valid");
        
        // Phone
        String phone;
        do {
            System.out.print("Enter cellphone (+27...): ");
            phone = input.nextLine();
            
            if (!checkCellphone(phone)) {
                System.out.println(" Phone number is invalid! Must start with +27 and be 12 digits total.");
            }
        } while (!checkCellphone(phone));
        
        System.out.println(" number is valid.");
        System.out.println("\n REGISTRATION SUCCESSFUL! 🎉");
        System.out.println("You can now login with your credentials.\n");
    }
    
    // Login user
    public void loginUser(Scanner input) {
        System.out.println("\n=== USER LOGIN ===\n");
        
        System.out.print("Enter username: ");
        String username = input.nextLine();
        
        System.out.print("Enter password: ");
        String password = input.nextLine();
        
        // For demo purposes, accept any valid format credentials
        if (checkUserName(username) && checkPassword(password)) {
            System.out.println(" LOGIN SUCCESSFUL!");
            System.out.println("Welcome " + username + "!");
        } else {
            System.out.println("LOGIN FAILED!");
            System.out.println("Invalid username or password format.");
        }
    }

    // MAIN METHOD
    public static void main(String[] args) {
        Prog5121Part1 reg = new Prog5121Part1();
        Scanner input = new Scanner(System.in);
        
        System.out.println("     WELCOME TO THE SYSTEM");
        
        int choice = 0;
        
        do {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("\nEnter your choice (1-3): ");
            
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input! Please enter a number.");
                continue;
            }
            
            switch (choice) {
                case 1:
                    reg.registerUser(input);
                    break;
                case 2:
                    reg.loginUser(input);
                    break;
                case 3:
                    System.out.println("\nThank you for using the system. Goodbye!");
                    break;
                default:
                    System.out.println(" Invalid choice! Please enter 1, 2, or 3.");
            }
        } while (choice != 3);
        
        input.close();
    }
}