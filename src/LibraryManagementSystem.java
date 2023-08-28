import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Book {
	private int bookId;
	private String title;
	private String author;
	private boolean available;
	
	public Book(int bookId, String title, String author) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.available = true;
	}
	
	public int getBookId() {
		return bookId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
}

class Borrower {
	private int borrowerId;
	private String name;
	
	public Borrower(int borrowerId, String name) {
		this.borrowerId = borrowerId;
		this.name = name;
	}
	
	public int getBorrowerId() {
		return borrowerId;
	}
	
	public String getName() {
		return name;
	}
}

class Transaction {
	private int transactionId;
	private Book book;
	private Borrower borrower;
	private String date;
	
	public Transaction(int transactionId, Book book, Borrower borrower, String date) {
		this.transactionId = transactionId;
		this.book = book;
		this.borrower = borrower;
		this.date = date;
	}
	
	public int getTransactionId() {
		return transactionId;
	}
	
	public Book getBook() {
		return book;
	}
	
	public Borrower getBorrower() {
		return borrower;
	}
	
	public String getDate() {
		return date;
	}
}

class Library {
	private Map<Integer, Book> books;
	private Map<Integer, Borrower> borrowers;
	private List<Transaction> transactions;
	private int nextTransactionId;
	
	public Library() {
		books = new HashMap<>();
		borrowers = new HashMap<>();
		transactions = new ArrayList<>();
		nextTransactionId = 1;
	}
	
	public void addBook(Book book) {
		books.put(book.getBookId(), book);
	}
	
	public void addBorrower(Borrower borrower) {
		borrowers.put(borrower.getBorrowerId(), borrower);
	}
	
	public boolean borrowBook(int bookId, int borrowerId, String date) {
		Book book = books.get(bookId);
		Borrower borrower = borrowers.get(borrowerId);
		
		if (book == null || borrower == null || !book.isAvailable()) {
			return false;
		}
		
		book.setAvailable(false);
		transactions.add(new Transaction(nextTransactionId, book, borrower, date));
		nextTransactionId++;
		
		return true;
	}
	
	public void printTransactions() {
		for (Transaction transaction : transactions) {
			System.out.println("Transaction ID: " + transaction.getTransactionId());
			System.out.println("Book Title: " + transaction.getBook().getTitle());
			System.out.println("Borrower: " + transaction.getBorrower().getName());
			System.out.println("Date: " + transaction.getDate());
			System.out.println();
		}
	}
}

public class LibraryManagementSystem {
	public static void main(String[] args) {
		Library library = new Library();
		
		Book book1 = new Book(1, "The Greate Gatsby", "F. Scott Fitzgerald");
		Book book2 = new Book(2, "To Kill a Mockingbird", "Harper Lee");
		Borrower borrower1 = new Borrower(1, "John David");
		Borrower borrower2 = new Borrower(2, "Matthew");
		
		library.addBook(book1);
		library.addBook(book2);
		library.addBorrower(borrower1);
		library.addBorrower(borrower2);
		
		library.borrowBook(1, 1, "2023-08-28");
		library.borrowBook(2, 2, "2023-08-29");
		
		library.printTransactions();
	}
}
