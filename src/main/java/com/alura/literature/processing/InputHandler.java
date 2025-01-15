package com.alura.literature.processing;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private final Scanner inputScanner = new Scanner(System.in);

    public UserChoice getUserChoice() {
        final String errorMessage = "Invalid Input\nChoice must be a number in [1, 6] range";
        UserChoice userChoice = null;

        while (userChoice == null) {
            System.out.println("""
					********************************
					Please select an option:
					1. Search book
					2. Search author
					3. Search author by date
					4. Filter books by language
					5. Search book by topic
					6. Exit
					********************************
					""");
            try {
                final int userInput = inputScanner.nextInt();
                inputScanner.nextLine();
                switch (userInput) {
                    case 1:
                        userChoice = UserChoice.SEARCH_BOOK;
                        break;
                    case 2:
                        userChoice = UserChoice.SEARCH_AUTHOR;
                        break;
                    case 3:
                        userChoice = UserChoice.SEARCH_AUTHOR_BY_DATE;
                        break;
                    case 4:
                        userChoice = UserChoice.SEARCH_BOOKS_BY_LANGUAGE;
                        break;
                    case 5:
                        userChoice = UserChoice.SEARCH_BOOK_BY_TOPIC;
                        break;
                    case 6:
                        userChoice = UserChoice.EXIT;
                        break;
                    default:
                        System.out.println(errorMessage);
                        break;
                }
            } catch (InputMismatchException e) {
                inputScanner.nextLine();
            }
        }

        return userChoice;
    }

    public String getInputString() {
        return inputScanner.nextLine().toLowerCase();
    }

    public String getInputAuthorName(){
        System.out.println("Enter author name:\n");
        return getInputString();
    }

    public String getInputBookName(){
        System.out.println("Enter book name:\n");
        return getInputString();
    }

    public String getInputTopic(){
        System.out.println("Enter book topic:\n");
        return getInputString();
    }

    public int getAuthorYear(boolean startYear) {
        String yearMessage = (startYear) ? "start year" : "end year";
        while (true) {
            System.out.println("Enter author " + yearMessage + "(format: yyyy, eg -> 2024):\n");
            try {
                int inputYear = inputScanner.nextInt();
                inputScanner.nextLine();
                return inputYear;
            } catch (InputMismatchException e) {
                System.out.println("Input error: " + e.getMessage());
            }
        }
    }

    public String getBookLanguage(){
        String bookLanguage;
        while (true) {
            System.out.println("Input 2 or 3 letter code for language:\n");
            bookLanguage = inputScanner.nextLine();
            if (!bookLanguage.isEmpty() && bookLanguage.length() < 4){
                return bookLanguage.toUpperCase();
            }
        }
    }
}