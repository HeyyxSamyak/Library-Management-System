import java.util.Scanner;

class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    // Constructor
    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false; // default: book is available
    }

    // Show book details
    void displayBook() {
        System.out.println(id + " - " + title + " by " + author + 
                           (isIssued ? " (Issued)" : " (Available)"));
    }
}

class Library {
    Book[] books;
    int bookCount;

    Library(int size) {
        books = new Book[size]; // fixed size array
        bookCount = 0;
    }

    // Add a new book
    void addBook(int id, String title, String author) {
        if (bookCount < books.length) {
            books[bookCount] = new Book(id, title, author);
            bookCount++;
            System.out.println("Book added successfully!");
        } else {
            System.out.println("Library is full! Cannot add more books.");
        }
    }

    // Show all books
    void showAllBooks() {
        if (bookCount == 0) {
            System.out.println("No books in library.");
            return;
        }
        for (int i = 0; i < bookCount; i++) {
            books[i].displayBook();
        }
    }

    // Search book by title
    void searchBook(String title) {
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].title.equalsIgnoreCase(title)) {
                books[i].displayBook();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found!");
        }
    }

    // Issue a book
    void issueBook(int id) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].id == id) {
                if (!books[i].isIssued) {
                    books[i].isIssued = true;
                    System.out.println("Book issued successfully!");
                } else {
                    System.out.println("Book is already issued!");
                }
                return;
            }
        }
        System.out.println("Book ID not found!");
    }

    // Return a book
    void returnBook(int id) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].id == id) {
                if (books[i].isIssued) {
                    books[i].isIssued = false;
                    System.out.println("Book returned successfully!");
                } else {
                    System.out.println("This book was not issued.");
                }
                return;
            }
        }
        System.out.println("Book ID not found!");
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library(100); // library with max 100 books

        while (true) {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. Add Book");
            System.out.println("2. Show All Books");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    lib.addBook(id, title, author);
                    break;

                case 2:
                    lib.showAllBooks();
                    break;

                case 3:
                    System.out.print("Enter Title to Search: ");
                    String searchTitle = sc.nextLine();
                    lib.searchBook(searchTitle);
                    break;

                case 4:
                    System.out.print("Enter Book ID to Issue: ");
                    int issueId = sc.nextInt();
                    lib.issueBook(issueId);
                    break;

                case 5:
                    System.out.print("Enter Book ID to Return: ");
                    int returnId = sc.nextInt();
                    lib.returnBook(returnId);
                    break;

                case 6:
                    System.out.println("Thank you for using the Library System!");
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
