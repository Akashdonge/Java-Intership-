import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private boolean isIssued;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.isIssued = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issueBook() {
        this.isIssued = true;
    }

    public void returnBook() {
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return id + " - " + title + " | " + (isIssued ? "Issued" : "Available");
    }
}

class User {
    private int userId;
    private String name;
    private ArrayList<Book> issuedBooks;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.issuedBooks = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public void issueBook(Book book) {
        issuedBooks.add(book);
    }

    public void returnBook(Book book) {
        issuedBooks.remove(book);
    }

    @Override
    public String toString() {
        return userId + " - " + name + " | Books Issued: " + issuedBooks.size();
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    public void addBook(Book b) {
        books.add(b);
    }

    public void addUser(User u) {
        users.add(u);
    }

    public void issueBook(int bookId, int userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }
        if (user == null) {
            System.out.println("User not found!");
            return;
        }
        if (book.isIssued()) {
            System.out.println("Book already issued!");
            return;
        }

        book.issueBook();
        user.issueBook(book);
        System.out.println("Book issued successfully!");
    }

    public void returnBook(int bookId, int userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);

        if (book == null || user == null) {
            System.out.println("Invalid Book or User!");
            return;
        }

        book.returnBook();
        user.returnBook(book);
        System.out.println("Book returned successfully!");
    }

    private Book findBook(int id) {
        for (Book b : books) {
            if (b.getId() == id)
                return b;
        }
        return null;
    }

    private User findUser(int id) {
        for (User u : users) {
            if (u.getUserId() == id)
                return u;
        }
        return null;
    }

    public void displayBooks() {
        System.out.println("\nAll Books:");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void displayUsers() {
        System.out.println("\nAll Users:");
        for (User u : users) {
            System.out.println(u);
        }
    }
}

public class Main {
    public static void main(String[] args) {

        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View All Books");
            System.out.println("6. View All Users");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
                    library.addBook(new Book(id, title));
                    System.out.println("Book Added!");
                    break;

                case 2:
                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter User Name: ");
                    String uname = sc.nextLine();
                    library.addUser(new User(uid, uname));
                    System.out.println("User Added!");
                    break;

                case 3:
                    System.out.print("Enter Book ID: ");
                    int bid = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int usid = sc.nextInt();
                    library.issueBook(bid, usid);
                    break;

                case 4:
                    System.out.print("Enter Book ID: ");
                    int rb = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int ru = sc.nextInt();
                    library.returnBook(rb, ru);
                    break;

                case 5:
                    library.displayBooks();
                    break;

                case 6:
                    library.displayUsers();
                    break;

                case 7:
                    System.out.println("Exiting System...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
