package Model;

public class User {
    private static int uniqueID = 0;
    private int ID;
    private String name;
    private String password;
    private boolean admin = false;

    // kostruktor do dodania konta użytkownika
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        ID = User.getUniqueID();
    }

    // konstruktor do dodania konta admina
    public User(String name, String password, boolean admin) {
        this.name = name;
        this.password = password;
        ID = User.getUniqueID();
        this.admin = admin;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }


    //
    public String userInfo() {

        String checkIfAdmin = admin ? "tak" : "nie";
        return "User{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + checkIfAdmin +
                '}';
    }

    @Override
    public String toString() {
        return ID + ";" + name + ";" + password + ";" + admin;
    }


    public static int getUniqueID() {
        int id = uniqueID;
        ++id;
        uniqueID = id;
        return uniqueID;
    }
}
