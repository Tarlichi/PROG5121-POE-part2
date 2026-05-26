package com.mycompany.prog5121;

import java.util.ArrayList;
import java.util.Random;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;
    private static int totalMessages = 0;
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    
    public Message(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
        generateMessageID();
        totalMessages++;
        this.messageNumber = totalMessages;
        generateMessageHash();
    }
    
    private void generateMessageID() {
        Random rand = new Random();
        long randomNum = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        this.messageID = String.valueOf(randomNum);
    }
    
    private void generateMessageHash() {
        String hashSource = "";
        if (message.length() >= 6) {
            hashSource = message.substring(0, 6);
        } else {
            hashSource = message;
        }
        String idPart = messageID.substring(6);
        this.messageHash = (hashSource + idPart).toUpperCase();
    }
    
    public String checkRecipientCell() {
        if (recipient != null && recipient.startsWith("+27") && recipient.length() == 12) {
            boolean allDigits = true;
            for (int i = 3; i < recipient.length(); i++) {
                if (!Character.isDigit(recipient.charAt(i))) {
                    allDigits = false;
                    break;
                }
            }
            if (allDigits) {
                return "Cell phone number successfully captured.";
            }
        }
        return "Cell phone number incorrectly formatted.";
    }
    
    public void sendMessage() {
        sentMessages.add(this);
    }
    
    public void printMessage() {
        System.out.println("\n=== MESSAGE DETAILS ===");
        System.out.println("Message ID: " + messageID);
        System.out.println("Message Number: " + messageNumber);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + message);
        System.out.println("Message Hash: " + messageHash);
    }
    
    public static void printAllMessages() {
        if (sentMessages.isEmpty()) {
            System.out.println("No messages sent yet.");
        } else {
            System.out.println("\n=== ALL SENT MESSAGES ===");
            for (Message msg : sentMessages) {
                msg.printMessage();
            }
        }
    }
    
    public static int getTotalMessages() { 
        return totalMessages; 
    }
}
