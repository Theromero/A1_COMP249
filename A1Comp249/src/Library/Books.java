//-----------------------------------------
// Assignment 1
// Part 2
// Written by: Étienne Beaumier, 40211362
//             Romero FAUSTIN,   40234898
//-----------------------------------------

/*
Books class: This class, which extends the Items class, represents a book with a specific number of pages. It has a
static counter that increments each time a new book object is created, providing a unique ID for each book. It includes
constructors, getters, setters, and methods to represent the book as a string and to compare two books.
 */

package Library;

public class Books extends Items implements LibraryItem {

    // Counter to generate unique IDs// Number of pages in the book
    private int nbOfPages;

    // Default constructor
    public Books() {
        // Call the constructor of the superclass
        super();
        // Increment the counter
        // Set the number of pages to 0
        this.nbOfPages = 0;
        // Set the ID with a prefix "B"
        super.setID("B" + getCounter());
    }

    // Constructor with parameters
    public Books(String title, String author, int publicationYear, int nbOfPages) {
        // Call the constructor of the superclass with parameters
        super(title, author, publicationYear);
        // Set the number of pages
        this.nbOfPages = nbOfPages;
        // Set the ID with a prefix "B"
        super.setID("B" + getCounter());
    }

    // Copy constructor
    public Books(Books books) {
        // Call the constructor of the superclass with parameters
        super(books.getTitle(), books.getAuthor(), books.getPublicationYear());
        // Copy the number of pages
        this.nbOfPages = books.nbOfPages;
        // Set the ID with a prefix "B"
        super.setID("B" + getCounter());
    }

    // Getter and setter for the number of pages
    public int getNbOfPages() {
        return nbOfPages;
    }

    public void setNbOfPages(int nbOfPages) {
        this.nbOfPages = nbOfPages;
    }

    // Method to represent the book as a string
    @Override
    public String toString() {
        return "Book " + "ID='" + getID() + '\'' +
                ", the title is " + getTitle() + '\'' +
                ", the author is " + getAuthor() + '\'' +
                ", the publication year is " + getPublicationYear() +
                " and has " + nbOfPages + " pages";
    }

    // Method to compare two books
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        } else if (getClass() != otherObject.getClass()) {
            return false;
        } else {
            Books otherBook = (Books) otherObject;
            return (getTitle().equals(otherBook.getTitle())
                    && getAuthor().equals(otherBook.getAuthor())
                    && getPublicationYear() == otherBook.getPublicationYear()
                    && nbOfPages == otherBook.nbOfPages);
        }
    }
}