import java.io.*;
import java.util.*;

class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    String title;
    String author;
    boolean isAvailable = true;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String toString() {
        return id + ". " + title + " by " + author + (isAvailable ? " [Available]" : " [Checked Out]");
    }
}

class Member implements Serializable {
    private static final long serialVersionUID = 1L;
    int memberId;
    String name;

    Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String toString() {
        return memberId + ". " + name;
    }
}

class Library {
    List<Book> books = new ArrayList<>();
    List<Member> members = new ArrayList<>();

    // Add a new book
    void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book);
    }

    // Register a new member
    void addMember(Member member) {
        members.add(member);
        System.out.println("Member registered: " + member);
    }

    // Check out a book
    void checkOutBook(int bookId) {
        for (Book b : books) {
            if (b.id == bookId && b.isAvailable) {
                b.isAvailable = false;
                System.out.println("Checked out: " + b);
                return;
            }
        }
        System.out.println("Book not available or not found.");
    }

    // Return a book
    void returnBook(int bookId) {
        for (Book b : books) {
            if (b.id == bookId && !b.isAvailable) {
                b.isAvailable = true;
                System.out.println("Returned: " + b);
                return;
            }
        }
        System.out.println("Book not found or already returned.");
    }

    // Display all books
    void listBooks() {
        for (Book b : books) {
            System.out.println(b);
        }
    }

    // Save library data
    void saveData() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("library.dat"));
        out.writeObject(books);
        out.writeObject(members);
        out.close();
    }

    // Load library data
    void loadData() throws IOException, ClassNotFoundException {
        File file = new File("library.dat");
        if (file.exists()) {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            books = (List<Book>) in.readObject();
            members = (List<Member>) in.readObject();
            in.close();
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        try {
            library.loadData();
        } catch (Exception e) {
            System.out.println("Starting fresh library database.");
        }

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. List Books");
            System.out.println("4. Check Out Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter book ID, title, and author: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    String title = sc.nextLine();
                    String author = sc.nextLine();
                    library.addBook(new Book(id, title, author));
                    break;
                case 2:
                    System.out.print("Enter member ID and name: ");
                    int mid = sc.nextInt();
                    sc.nextLine();
                    String name = sc.nextLine();
                    library.addMember(new Member(mid, name));
                    break;
                case 3:
                    library.listBooks();
                    break;
                case 4:
                    System.out.print("Enter book ID to check out: ");
                    int checkOutId = sc.nextInt();
                    library.checkOutBook(checkOutId);
                    break;
                case 5:
                    System.out.print("Enter book ID to return: ");
                    int returnId = sc.nextInt();
                    library.returnBook(returnId);
                    break;
                case 6:
                    try {
                        library.saveData();
                        System.out.println("Library data saved. Exiting...");
                    } catch (IOException e) {
                        System.out.println("Error saving data.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);

        sc.close();
    }
}
