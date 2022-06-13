package Controller;

import IO.DataReader;
import Model.Book;

import java.util.ArrayList;

public class BookController {

    private DataReader dataReader = new DataReader();
    private String bookPath = "bookList.txt";
    private String borrowedBooksPath = "borrowedBookList.txt";


    public void addBook(ArrayList<Book> books) {
        if(!books.isEmpty()) {
            dataReader.loadBooks(books);
        } else {
            books = dataReader.loadBooksFromFile(bookPath);
        }

        System.out.println("Tytuł: ");
        String title = dataReader.getString();
        System.out.println("Autor: ");
        String author = dataReader.getString();
        System.out.println("ISBN: ");
        Integer ISBN = dataReader.getInt();

        books.add(new Book(title,author,ISBN));
        dataReader.saveBooks(books, bookPath);
    }

    public void removeBook(ArrayList<Book> books) {
        System.out.println("podaj ID książki którą chcesz usunąć: ");
        int x = dataReader.getInt()-1;

        System.out.println("Książka " + books.get(x).getTitle() + " zostanie usunieta.");
        System.out.println("Jestes pewien? y/n");
        char c = dataReader.getChar();
        if (c == 'y') {
            books.remove(x);
        } else {
            System.out.println("Anulowano");
        }
        dataReader.saveBooks(books, bookPath);
    }

    public void borrowBook(ArrayList<Book> books, ArrayList<Book> borrowedBooks, int bookID, String user) {

        for(Book book: books) {
            if(book.getID() == bookID) {
                System.out.println("ksiazka " + book.getTitle() + " zostanie wypozyczona");
                borrowedBooks.add(new Book(book.getTitle(), book.getAuthor(), book.getISBN(), user));
                books.remove(book);
                break;
            }
        }
        dataReader.saveBooks(borrowedBooks, borrowedBooksPath);
        dataReader.saveBooks(books, bookPath);
    }

    public void returnBook(ArrayList<Book> books, ArrayList<Book> borrowedBooks, int bookID) {

        for(Book book: borrowedBooks) {
            if(book.getID() == bookID) {
                books.add(new Book(book.getTitle(), book.getAuthor(), book.getISBN()));
                borrowedBooks.remove(book);
                break;
            }
        }
    }
}