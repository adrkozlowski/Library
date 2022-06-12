package App;

import Controller.BookController;
import Controller.UserController;
import IO.DataReader;
import Model.User;
import Model.Book;

import java.util.ArrayList;

public class Menu {

    private UserController userController = new UserController();
    private BookController bookController = new BookController();
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<Book> borrowedBooks = new ArrayList<Book>();
    private DataReader dataReader = new DataReader();
    private int option;

    public void mainMenu() {

        if(!users.isEmpty() && !books.isEmpty()) {
            dataReader.loadUsers(users);
            dataReader.loadBooks(books);
            dataReader.loadBooks(borrowedBooks);
        }
        else {
            users = dataReader.loadUsersFromFile();
            books = dataReader.loadBooksFromFile("bookList.txt");
            borrowedBooks = dataReader.loadBooksFromFile("borrowedBookList.txt");
        }

        do {
            System.out.println("============================================================");
            System.out.println("Witamy w naszej bibliotece. Zaloguj się jako: ");
            System.out.println("1 - admin");
            System.out.println("2 - użytkownik");
            System.out.println("0 - wyjscie z programu");
            System.out.println("============================================================");
            option = dataReader.getInt();
            switch (option) {
                case 1:
                    System.out.println("Logowanie admin:");
                    System.out.println("Podaj login: ");
                    String name = dataReader.getString();
                    System.out.println("Podaj hasło: ");
                    String password = dataReader.getString();

                    if(userController.findUser(name,password,users) != null) {
                        User admin = userController.findUser(name, password, users);

                        if(!admin.isAdmin()) {
                            System.out.println("Brak uprawnień");
                        }
                        else {
                            adminMenu(admin);
                        }
                    } else {
                        System.out.println("Uzytkownik nie istnieje");
                    }
                    break;
                case 2:
                    System.out.println("Logowanie użytkownika: ");
                    System.out.println("Podaj login: ");
                    name = dataReader.getString();
                    System.out.println("Podaj hasło: ");
                    password = dataReader.getString();

                    if(userController.findUser(name,password,users) != null) {
                        User user = userController.findUser(name, password, users);
                        userMenu(user,books, borrowedBooks);

                    } else {
                        System.out.println("Uzytkownik nie istnieje");
                    }
                    break;
                case 0:
                    System.out.println("Koniec programu");
                    break;
                default:
                    System.out.println("niepoprawny wybor, sprobuj ponownie");
                    break;

            }

        } while (option != 0);

        dataReader.close();
    }

    private void adminMenu(User admin) {
        System.out.println("czesc " + admin.getName());
        do {
            System.out.println("Wybierz opcje: " +
                    "\n============================================================" +
                    "\n1 - wyświetl użytkowników" +
                    "\n2 - dodaj nowego admina" +
                    "\n3 - dodaj użytkownika" +
                    "\n4 - usuń użytkownika" +
                    "\n5 - dodaj książkę" +
                    "\n6 - usuń książkę" +
                    "\n7 - wyloguj" +
                    "\n0 - wyjscie z programu" +
                    "\n============================================================");

            option = dataReader.getInt();

            switch (option) {
                case 1:
                    System.out.println("lista uzytkownikow: ");
                    for(User user:users) {
                        System.out.println(user.userInfo());
                    }
                    break;
                case 2:
                    userController.addAdmin(users);
                    break;
                case 3:
                    userController.addUser(users);
                    break;
                case 4:
                    userController.removeUser(users, admin);
                    break;
                case 5:
                    bookController.addBook(books);
                    break;
                case 6:
                    bookController.removeBook(books);
                    break;
                case 7:
                    mainMenu();
                    break;
                case 0:
                    System.out.println("koniec porgramu");
                    break;
                default:
                    System.out.println("spróbuj ponownie");
            }
        }while (option != 0);
    }

    private void userMenu(User user, ArrayList<Book> books, ArrayList<Book> borrowedBooks) {

        int bookID;
        System.out.println("czesc " + user.getName());
        do {
            System.out.println("Wybierz opcje: " +
                    "\n============================================================" +
                    "\n1 - dostępne książki" +
                    "\n2 - wypożycz książkę" +
                    "\n3 - moje wypożyczone książki" +
                    "\n4 - oddaj książkę" +
                    "\n5 - wyloguj" +
                    "\n0 - wyjscie z programu" +
                    "\n============================================================");

            option = dataReader.getInt();

            switch (option) {
                case 1:
                    System.out.println("lista dostępnych książek: ");
                    for(Book book:books) {
                        System.out.println(book.bookInfo());
                    }
                    break;
                case 2:
                    System.out.println("Podaj numer ID książki którą chcesz wypożyczyć");
                    bookID = dataReader.getInt();

                    bookController.borrowBook(books, borrowedBooks,bookID, user.getID());
                    break;
                case 3:
                    System.out.println("lista moich wypożyczonych książek: ");
                    for(Book book:borrowedBooks) {
                        if(book.getUserID() == user.getID()) {
                            System.out.println(book.bookInfo());
                        }
                    }
                    break;
                case 4:
                    System.out.println("podaj numer ID książki którą chcesz oddać: ");
                    bookID = dataReader.getInt();
                    bookController.returnBook(books, borrowedBooks, bookID);
                    break;
                case 5:
                    mainMenu();
                    break;
                case 0:
                    System.out.println("koniec porgramu");
                    break;
                default:
                    System.out.println("spróbuj ponownie");
            }
        }while (option != 0);
    }
}