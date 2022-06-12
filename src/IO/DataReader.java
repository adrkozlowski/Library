package IO;

import Model.User;
import Model.Book;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class DataReader {

    private  Scanner sc = new Scanner(System.in);
    private FileReader fr;
    private BufferedReader br;
    private FileWriter fw;
    private BufferedWriter bw;
    private String s;



    public  ArrayList<User> loadUsersFromFile() {
        ArrayList<User> users = new ArrayList<User>();

        try {
            fr = new FileReader("userList.txt");
            br = readFile(fr);

            while((s = br.readLine()) != null) {
                String str[] = s.split(";");
                User user = new User(str[1], str[2]);
                user.setAdmin(Boolean.parseBoolean(str[3]));
                users.add(user);
            }

            br.close();

        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return users;
    }

    public  void loadUsers(ArrayList<User> users) {
        for(User user: users) {
            System.out.println(user.toString());
        }
    }

    public  void saveUsers(ArrayList<User> users) {
        try {
            fw = new FileWriter("userList.txt");
            bw = saveFile(fw);

            for (int i = 0; i < users.size(); i++)
            {
                bw.write(users.get(i).toString()+ "\n");
            }
            bw.close();
        }
        catch(Exception ex) {
            return;
        }
    }

    public ArrayList<Book> loadBooksFromFile(String path) {
        ArrayList<Book> books = new ArrayList<Book>();

        try {
            fr = new FileReader(path);
            br = readFile(fr);


            while ((s = br.readLine()) != null) {
                String str[] = s.split(";");
                Book book = new Book(str[1], str[2], Integer.parseInt(str[3]));
                books.add(book);
            }

            br.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return books;

    }
    public  void loadBooks(ArrayList<Book> books) {
        for(Book book: books) {
            System.out.println(book.toString());
        }
    }

    public  void saveBooks(ArrayList<Book> books, String path) {
        try {
            fw = new FileWriter(path);
            bw = saveFile(fw);

            for (int i = 0; i < books.size(); i++)
            {
                bw.write(books.get(i).toString()+ "\n");
            }
            bw.close();
        }
        catch(Exception ex) {
            return;
        }
    }

    public String getString() {
        String text = sc.nextLine();
        return text;
    }

    public int getInt() {
        int i = sc.nextInt();
        sc.nextLine();
        return i;
    }

    public char getChar() {
        char c = sc.next().charAt(0);
        return c;

    }

    public void close() {
        sc.close();
    }

    private BufferedReader readFile(FileReader fr) {
        return new BufferedReader(fr);
    }

    private BufferedWriter saveFile(FileWriter fw) {
        return new BufferedWriter(fw);
    }
}