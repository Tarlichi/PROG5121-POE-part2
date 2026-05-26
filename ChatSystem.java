package com.mycompany.prog5121;

import java.util.Scanner;

public class ChatSystem {
    
    // Use the Login class for registration
    private static Login authSystem = new Login();
    private static boolean isLoggedIn = false;
    private static String currentUser = null;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.println("     WELCOME TO QUICKCHAT");
       
       
        // PHASE 1: Register or Login
        boolean exit = false;
        
        while (!exit && !isLoggedIn) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option (1-3): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            switch (choice) {
                case 1:
                    // Register new user
                    boolean registered = authSystem.register(scanner);
                    if (registered) {
                        System.out.println(" Registration complete! Please login.\n");
                    }
                    break;
                    
                case 2:
                    // Login existing user
                    System.out.println("\n--- LOGIN ---");
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    
                    // Check credentials against stored values
                    if (username.equals(authSystem.getStoredUsername()) && 
                        password.equals(authSystem.getStoredPassword())) {
                        isLoggedIn = true;
                        currentUser = username;
                        System.out.println("\n login successful! Redirecting to QuickChat...\n");
                    } else {
                        System.out.println("\n Login failed! Invalid username or password.\n");
                    }
                    break;
                    
                case 3:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
        
        // PHASE 2: If logged in, launch messaging app
        if (isLoggedIn) {
            runMessagingApp(scanner);
        } else {
            System.out.println("Failed to login. Exiting...");
        }
        
        scanner.close();
    }
    
    // Messaging Application
    private static void runMessagingApp(Scanner scanner) {
      
        System.out.println("     WELCOME TO QUICKCHAT, " + currentUser.toUpperCase() + "!");
        
        
        // Ask how many messages user wants to send
        System.out.print("\nHow many messages do you want to send? ");
        int messageLimit = scanner.nextInt();
        scanner.nextLine();
        
        int messagesSent = 0;
        boolean running = true;
        
        while (running) {
            System.out.println("\n=== QUICKCHAT MENU ===");
            System.out.println("1. Send Message");
            System.out.println("2. View All Messages");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1: // Send Message
                    if (messagesSent >= messageLimit) {
                        System.out.println(" You have reached your message limit (" + messageLimit + " messages).");
                        System.out.println("Start a new session to send more messages.");
                        break;
                    }
                    
                    // Get recipient
                    System.out.print("\nEnter recipient's cell number (start with +27): ");
                    String recipient = scanner.nextLine();
                    
                    // Get message
                    System.out.print("Enter your message (max 250 characters): ");
                    String messageText = scanner.nextLine();
                    
                    // Validate message length
                    if (messageText.length() > 250) {
                        System.out.println(" Message too long! Please keep under 250 characters.");
                        break;
                    }
                    
                    // Create and send message
                    Message newMessage = new Message(recipient, messageText);
                    String validation = newMessage.checkRecipientCell();
                    System.out.println("\n" + validation);
                    
                    if (validation.contains("successfully")) {
                        newMessage.sendMessage();
                        messagesSent++;
                        System.out.println("Message sent successfully!");
                        System.out.println("Messages sent: " + messagesSent + "/" + messageLimit);
                        newMessage.printMessage();
                    }
                    break;
                    
                case 2: // View all messages
                    System.out.println("\n--- MESSAGE HISTORY ---");
                    Message.printAllMessages();
                    break;
                    
                case 3: // Quit
                   
                    System.out.println(" SESSION SUMMARY");
                    System.out.println("   User: " + currentUser);
                    System.out.println("   Messages sent: " + messagesSent + "/" + messageLimit);
                    System.out.println("   Total messages in system: " + Message.getTotalMessages());
                    System.out.println(" Goodbye " + currentUser + "! Thanks for using QuickChat.");
                    running = false;
                    break;
                    
                default:
                    System.out.println(" Invalid option. Please choose 1, 2, or 3.");
            }
        }
    }
}