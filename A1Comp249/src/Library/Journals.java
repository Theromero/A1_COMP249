//-----------------------------------------
// Assignment 1
// Part 2
// Written by: Étienne Beaumier, 40211362
//             Romero FAUSTIN,   40234898
//-----------------------------------------

/*
Journals class: This class, which extends the Items class, represents a journal with a specific volume number. It has a
static counter that increments each time a new journal object is created, providing a unique ID for each journal. It
includes constructors, getters, setters, and methods to represent the journal as a string and to compare two journals.
 */

package Library;

public class Journals extends Items implements LibraryItem{


    // Volume number of the journal
    private int volumeNumber;

    // Default constructor
    public Journals() {
        // Call the constructor of the superclass
        super();
        // Set the volume number to 0
        this.volumeNumber = 0;
        // Set the ID with a prefix "J"
        super.setID("J" + getCounter());
    }

    // Constructor with parameters
    public Journals(String title, String author, int publicationYear, int volumeNumber) {
        // Call the constructor of the superclass with parameters
        super(title, author, publicationYear);
        // Set the volume number
        this.volumeNumber = volumeNumber;
        // Set the ID with a prefix "J"
        super.setID("J" + getCounter());
    }

    // Copy constructor
    public Journals(Journals journals) {
        // Call the constructor of the superclass with parameters
        super(journals.getTitle(), journals.getAuthor(), journals.getPublicationYear());
        // Copy the volume number
        this.volumeNumber = journals.volumeNumber;
        // Set the ID with a prefix "J"
        super.setID("J" + getCounter());
    }

    // Getter and setter for the volume number
    public int getVolumeNumber() {
        return volumeNumber;
    }

    public void setVolumeNumber(int volumeNumber) {
        this.volumeNumber = volumeNumber;
    }

    // Method to represent the journal as a string
    @Override
    public String toString() {
        return "Journal " + "ID='" + getID() + '\'' +
                ", the title is '" + getTitle() + '\'' +
                ", the author is '" + getAuthor() + '\'' +
                ", the publication year is " + getPublicationYear() +
                " and the volume number is " + volumeNumber;
    }

    // Method to compare two journals
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        } else if (getClass() != otherObject.getClass()) {
            return false;
        } else {
            Journals otherJournal = (Journals) otherObject;
            return (getTitle().equals(otherJournal.getTitle())
                    && getAuthor().equals(otherJournal.getAuthor())
                    && getPublicationYear() == otherJournal.getPublicationYear()
                    && volumeNumber == otherJournal.volumeNumber);
        }
    }
}