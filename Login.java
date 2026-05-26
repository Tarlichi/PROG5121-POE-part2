package com.mycompany.prog5121;

import java.util.Scanner;

public class Login {

    private String storedUsername;
    private String storedPassword;
    private String storedPhone;

    // Username validation
    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    // Password validation
    public boolean checkPassword(String password) {
        if (password.length() < 8) return false;

        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    // Phone validation
    public boolean checkPhone(String phone) {
        if (!phone.startsWith("+27")) return false;
        if (phone.length() != 12) return false;

        for (int i = 3; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // Register method
    public boolean register(Scanner sc) {
        System.out.println("\n=== REGISTER ===");

        System.out.print("Enter username (must contain _ and be max 5 chars): ");
        String username = sc.nextLine();

        if (checkUserName(username)) {
            System.out.println("Username successfully captured.");
            storedUsername = username;
        } else {
            System.out.println("Username is not correctly formatted.");
            return false;
        }

        System.out.print("Enter password (min 8 chars, uppercase, number, special char): ");
        String password = sc.nextLine();

        if (checkPassword(password)) {
            System.out.println("Password successfully captured.");
            storedPassword = password;
        } else {
            System.out.println("Password is not correctly formatted.");
            return false;
        }

        System.out.print("Enter cell phone (+27...): ");
        String phone = sc.nextLine();

        if (checkPhone(phone)) {
            System.out.println("Cell phone number successfully added.");
            storedPhone = phone;
        } else {
            System.out.println("Cell phone number incorrectly formatted.");
            return false;
        }

        System.out.println("Registration successful!\n");
        return true;
    }

    // Getter methods
    public String getStoredUsername() { 
        return storedUsername; 
    }
    
    public String getStoredPassword() { 
        return storedPassword; 
    }
    
    public String getStoredPhone() { 
        return storedPhone; 
    }
}