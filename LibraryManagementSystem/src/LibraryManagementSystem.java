import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

class Book {
    private String id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author +
                ", Available: " + (isAvailable ? "Yes" : "No");
    }
}

class Member {
    private String id;
    private String name;
    private List<Book> borrowedBooks;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name +
                ", Borrowed Books: " + borrowedBooks.size();
    }
}

class Transaction {
    private String transactionId;
    private Book book;
    private Member member;
    private LocalDate checkoutDate;
    private LocalDate returnDate;

    public Transaction(String transactionId, Book book, Member member) {
        this.transactionId = transactionId;
        this.book = book;
        this.member = member;
        this.checkoutDate = LocalDate.now();
        this.returnDate = null;
    }

    // Getters
    public String getTransactionId() { return transactionId; }
    public Book getBook() { return book; }
    public Member getMember() { return member; }
    public LocalDate getCheckoutDate() { return checkoutDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public void completeTransaction() {
        this.returnDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId +
                "\nBook: " + book.getTitle() +
                "\nMember: " + member.getName() +
                "\nCheckout Date: " + checkoutDate +
                "\nReturn Date: " + (returnDate != null ? returnDate : "Not returned yet");
    }
}

class Library {
    private List<Book> books;
    private List<Member> members;
    private List<Transaction> transactions;
    private static final String BOOKS_FILE = "books.dat";
    private static final String MEMBERS_FILE = "members.dat";
    private static final String TRANSACTIONS_FILE = "transactions.dat";

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.transactions = new ArrayList<>();
        loadData();
    }

    // Book operations
    public void addBook(Book book) {
        books.add(book);
        saveBooks();
    }

    public Book findBookById(String id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    // Member operations
    public void addMember(Member member) {
        members.add(member);
        saveMembers();
    }

    public Member findMemberById(String id) {
        return members.stream()
                .filter(member -> member.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(members);
    }

    // Transaction operations
    public boolean checkoutBook(String bookId, String memberId) {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);

        if (book == null || member == null || !book.isAvailable()) {
            return false;
        }

        book.setAvailable(false);
        member.borrowBook(book);

        String transactionId = "TXN-" + System.currentTimeMillis();
        Transaction transaction = new Transaction(transactionId, book, member);
        transactions.add(transaction);

        saveBooks();
        saveMembers();
        saveTransactions();

        return true;
    }

    public boolean returnBook(String bookId, String memberId) {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);

        if (book == null || member == null || book.isAvailable()) {
            return false;
        }

        book.setAvailable(true);
        member.returnBook(book);

        transactions.stream()
                .filter(t -> t.getBook().getId().equals(bookId) &&
                        t.getMember().getId().equals(memberId) &&
                        t.getReturnDate() == null)
                .findFirst()
                .ifPresent(Transaction::completeTransaction);

        saveBooks();
        saveMembers();
        saveTransactions();

        return true;
    }

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }

    // File handling methods
    private void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKS_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.err.println("Error saving books: " + e.getMessage());
        }
    }

    private void saveMembers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MEMBERS_FILE))) {
            oos.writeObject(members);
        } catch (IOException e) {
            System.err.println("Error saving members: " + e.getMessage());
        }
    }

    private void saveTransactions() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TRANSACTIONS_FILE))) {
            oos.writeObject(transactions);
        } catch (IOException e) {
            System.err.println("Error saving transactions: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOKS_FILE))) {
            books = (List<Book>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing book data found. Starting with empty library.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading books: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MEMBERS_FILE))) {
            members = (List<Member>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing member data found.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading members: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TRANSACTIONS_FILE))) {
            transactions = (List<Transaction>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing transaction data found.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading transactions: " + e.getMessage());
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Checkout Book");
            System.out.println("4. Return Book");
            System.out.println("5. View All Books");
            System.out.println("6. View All Members");
            System.out.println("7. View All Transactions");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook(library, scanner);
                    break;
                case 2:
                    addMember(library, scanner);
                    break;
                case 3:
                    checkoutBook(library, scanner);
                    break;
                case 4:
                    returnBook(library, scanner);
                    break;
                case 5:
                    viewAllBooks(library);
                    break;
                case 6:
                    viewAllMembers(library);
                    break;
                case 7:
                    viewAllTransactions(library);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBook(Library library, Scanner scanner) {
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Book Author: ");
        String author = scanner.nextLine();

        Book book = new Book(id, title, author);
        library.addBook(book);
        System.out.println("Book added successfully!");
    }

    private static void addMember(Library library, Scanner scanner) {
        System.out.print("Enter Member ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Member Name: ");
        String name = scanner.nextLine();

        Member member = new Member(id, name);
        library.addMember(member);
        System.out.println("Member added successfully!");
    }

    private static void checkoutBook(Library library, Scanner scanner) {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();

        if (library.checkoutBook(bookId, memberId)) {
            System.out.println("Book checked out successfully!");
        } else {
            System.out.println("Failed to checkout book. Please check IDs and availability.");
        }
    }

    private static void returnBook(Library library, Scanner scanner) {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();

        if (library.returnBook(bookId, memberId)) {
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Failed to return book. Please check IDs.");
        }
    }

    private static void viewAllBooks(Library library) {
        System.out.println("\nAll Books:");
        library.getAllBooks().forEach(System.out::println);
    }

    private static void viewAllMembers(Library library) {
        System.out.println("\nAll Members:");
        library.getAllMembers().forEach(System.out::println);
    }

    private static void viewAllTransactions(Library library) {
        System.out.println("\nAll Transactions:");
        library.getAllTransactions().forEach(System.out::println);
    }
}