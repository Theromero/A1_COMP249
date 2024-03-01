//-----------------------------------------
// Assignment 1
// Part 2
// Written by: Étienne Beaumier, 40211362
//             Romero FAUSTIN,   40234898
//-----------------------------------------

/*
Items class: This class represents a library item with unique identifiers, title, author,
and publication year. It has a static counter that increments each time a new item object is created, providing a
unique ID for each item. It includes constructors, getters, setters, and methods to represent the item
as a string and to compare two items.
 */

package Library;

public class Items {

    // Counter to generate unique IDs
    private static int counter;
    // Unique identifier for each item
    private String ID;
    // Title of the item
    private String title;
    // Author of the item
    private String author;
    // Year of publication
    private int publicationYear;

    // Default constructor
    public Items() {
        // Increment the counter and assign it as ID
        counter++;
        this.ID = Integer.toString(counter);
        // Default values for other fields
        this.title = "title";
        this.author = "author";
        this.publicationYear = 0;
    }

    // Constructor with parameters
    public Items(String title, String author, int publicationYear) {
        // Increment the counter and assign it as ID
        counter++;
        this.ID = Integer.toString(counter);
        // Assign values from parameters
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    // Copy constructor
    public Items(Items items) {
        // Increment the counter and assign it as ID
        counter++;
        this.ID = Integer.toString(counter);
        // Copy values from the given item
        this.title = items.title;
        this.author = items.author;
        this.publicationYear = items.publicationYear;
    }

    // Getters and setters for the fields
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public static int getCounter() {
        return counter;
    }

    // Method to represent the item as a string
    @Override
    public String toString() {
        return "Items: " +
                "ID='" + ID + '\'' +
                ", the title is '" + title + '\'' +
                ", the author is'" + author + '\'' +
                ", and the publication year is " + publicationYear;
    }

    // Method to compare two items
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        } else if (getClass() != otherObject.getClass()) {
            return false;
        } else {
            Items otherItem = (Items) otherObject;
            return (title.equals(otherItem.title)
                    && author.equals(otherItem.author)
                    && publicationYear == otherItem.publicationYear);
        }
    }
}