package Controller;

import IO.DataReader;
import Model.User;
import java.util.ArrayList;

public class UserController {

    private  DataReader dataReader = new DataReader();

    public  User findUser(String name, String password, ArrayList<User> users) {
        try {
            for (User user : users) {
                if (user.getName().equals(name) && user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Uzytkownik nie istnieje");
        }
        return null;
    }

    public  void addUser(ArrayList<User> users) {
        if(!users.isEmpty()) {
            dataReader.loadUsers(users);
        }
        else {
            users = dataReader.loadUsersFromFile();
            dataReader.loadUsers(users);
        }

        dataReader.getString();
        System.out.println("podaj imie:");
        String name = dataReader.getString();
        System.out.println("podaj haslo: ");
        String pwd = dataReader.getString();

        users.add(new User(name,pwd));

        dataReader.saveUsers(users);
    }

    public  void addAdmin(ArrayList<User> users) {
        if(!users.isEmpty()) {
            dataReader.loadUsers(users);
        }
        else {
            users = dataReader.loadUsersFromFile();
        }

        dataReader.getString();
        System.out.println("podaj imie:");
        String name = dataReader.getString();
        System.out.println("podaj haslo: ");
        String pwd = dataReader.getString();

        users.add(new User(name,pwd,true));

        dataReader.saveUsers(users);
    }

    public void removeUser(ArrayList<User> users, User user) {
        System.out.println("podaj ID uzytkownika ktorego chcesz usunac: ");
        int x = dataReader.getInt()-1;
        if(!(user.getID() == x)) {

            System.out.println("Uzytkownik " + users.get(x).getName() +
                    " o numerze ID: " + users.get(x).getID() + " zostanie usuniety.");
            System.out.println("Jestes pewien? y/n");
            char c = dataReader.getChar();
            if (c == 'y') {
                users.remove(x);
            } else {
                System.out.println("Anulowano");
            }
        } else {
            System.out.println("nie mozesz usunac samego siebie");
        }
        dataReader.saveUsers(users);
    }

}
