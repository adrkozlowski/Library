package Controller;

import Model.*;

import java.util.Scanner;


public class LibraryController {
    private User user;
    private Book book;
    private Scanner scanner = new Scanner(System.in);

    public int getOption() {
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    public String getText() {
        String text = scanner.nextLine();
        return text;
    }

    public void close() {
        scanner.close();
    }
}