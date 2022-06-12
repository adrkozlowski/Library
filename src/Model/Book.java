package Model;

public class Book {
    private static int uniqueID = 0;
    private int ID;
    private String Title;
    private String Author;
    private int ISBN;
    private Integer UserID = null;

    public Book(String title, String author, int ISBN) {
        ID = Book.getUniqueID();
        Title = title;
        Author = author;
        this.ISBN = ISBN;
    }

    public Book(String title, String author, int ISBN, int userID) {
        ID = Book.getUniqueID();
        Title = title;
        Author = author;
        this.ISBN = ISBN;
        UserID = userID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    private static int getUniqueID() {
        int id = uniqueID;
        ++id;
        uniqueID = id;
        return uniqueID;
    }

    @Override
    public String toString() {
        return ID + ";" + Title + ";" + Author + ";" + ISBN;
    }

    public String bookInfo() {
        return "Book{" +
                "ID=" + ID +
                ", Title='" + Title + '\'' +
                ", Author='" + Author + '\'' +
                ", ISBN=" + ISBN +
                '}';
    }
}
