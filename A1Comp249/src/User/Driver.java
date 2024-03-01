//-----------------------------------------
// Assignment 1
// Part 2
// Written by: Étienne Beaumier, 40211362
//             Romero FAUSTIN,   40234898
//-----------------------------------------

/*
We ask the user if they want to get the menu or if they want to run
a predefined/hard-coded scenario. If the user selects the menu, the program
will take them through the menu. If the user selects the pre-defined scenario, the
program will:
 create at least 3 objects from each type of items and 3 users, and display their information.
 test the equality of some of the created objects using the equals() method.
We test the equality of two objects from different classes, two objects from the same class
with different values, and two objects of the same class with similar values.
 */
package User;


// Import statements to include necessary Java classes and packages
import Client.Client;
import Library.*;

import java.util.Scanner;
// Main class definition
public class Driver {
    public static void main(String[] args) {
        // Scanner object for reading input from the console
        Scanner scn = new Scanner(System.in);
        // Variable to store user choices
        int choice;

        // Initialize arrays
        Items[] itemsMenu = new Items[1000];
        Client[] clientMenu = new Client[100];
        Books[] bookMenu = new Books[100];
        Media[] mediaMenu = new Media[100];
        Journals[] journalsMenu = new Journals[100];

        // Main loop to display the menu and handle user input
        while (true) {
            System.out.println("------------------------\n" +
                    "Welcome to FunReadings Library's program!" +
                    "\nWould you prefer to: \n" +
                    "\t 1. Enter the menu page \n" +
                    "\t 2. Run a predefined scenario \n" +
                    "\t 3. Quit");

            // Reading the user's choice
            choice = scn.nextInt();
            scn.nextLine();// Consume newline left-over

            // Input validation for the main menu choice
            while (choice < 1 || choice > 3) {
                System.out.println("Enter a valid output, either 1, 2 or 3");
                choice = scn.nextInt();
            }


            // Handling the user's choice using a switch statement
            switch (choice) {
                case 1:
                    // If user chooses to enter the menu, display the menu and handle choices
                    displayMenu();
                    choice = scn.nextInt();
                    handleMenuChoice(choice, scn, itemsMenu, clientMenu, bookMenu, mediaMenu, journalsMenu);
                    break;
                case 2:
                    // If user chooses to run a predefined scenario, execute the scenario
                    runPredefinedScenario();
                    break;
                case 3:
                    // If user chooses to quit, display a goodbye message and exit the program
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
            }

        }
    }

    // Method to display the main menu and handle user choices for managing library items and clients
    private static void handleMenuChoice(int choice, Scanner scn, Items[] itemsMenu, Client[] clientMenu, Books[] bookMenu, Media[] mediaMenu, Journals[] journalsMenu) {
        switch (choice) {
            case 1:
                System.out.println("You chose to manage items. Please select an option:");
                System.out.println("\t1. Add item");
                System.out.println("\t2. Delete item");
                System.out.println("\t3. Modify item");
                System.out.println("\t4. List items");
                System.out.println("\t5. Print all items from all categories");

                int itemChoice = scn.nextInt();
                scn.nextLine();

                while (itemChoice < 1 || itemChoice > 5) {
                    System.out.println("Enter a valid output between 1 and 5");
                    itemChoice = scn.nextInt();
                }

                switch (itemChoice) {
                    case 1:
                        choice = returnChoice();
                        addItem(itemsMenu, bookMenu, mediaMenu, journalsMenu, choice);
                        break;
                    case 2:
                        choice = returnChoice();
                        System.out.println("Enter the ID of the item you want to delete:");
                        String itemID = scn.nextLine();
                        switch (choice) {
                            case 1:
                                bookMenu = deleteItem(bookMenu, itemID);
                                itemsMenu = deleteItem(itemsMenu, itemID);
                                break;
                            case 2:
                                journalsMenu = deleteItem(journalsMenu, itemID);
                                itemsMenu = deleteItem(itemsMenu, itemID);
                                break;
                            case 3:
                                mediaMenu = deleteItem(mediaMenu, itemID);
                                itemsMenu = deleteItem(itemsMenu, itemID);
                                break;
                        }
                        break;
                    case 3:
                        choice = returnChoice();
                        System.out.println("Enter the ID of the item you want to change:");
                        itemID = scn.nextLine();
                        if(itemID.startsWith("B")) {
                            change(bookMenu, itemID);
                            change(itemsMenu, itemID, bookMenu);
                        }
                        else if(itemID.startsWith("J")) {
                            change(journalsMenu, itemID);
                            change(itemsMenu, itemID, journalsMenu);
                        }
                        else if(itemID.startsWith("M")) {
                            change(mediaMenu, itemID);
                            change(itemsMenu, itemID, mediaMenu);
                        } else{
                            System.out.println("Invalid ID");
                        }
                        break;
                    case 4:
                        choice = returnChoice();
                        if(choice == 1) {
                            boolean existingBook = false;
                            for (Books book : bookMenu) {
                                if (book != null) {
                                    System.out.println(book.toString());
                                    existingBook = true;
                                }
                            }
                            if (!existingBook) {
                                System.out.println("There exist no book.");
                            }
                        } else if (choice == 2) {
                            boolean existingJournal = false;
                            for (Journals journal : journalsMenu) {
                                if (journal != null) {
                                    System.out.println(journal.toString());
                                    existingJournal = true;

                                }
                            }
                            if (!existingJournal) {
                                System.out.println("There exist no journal.");
                            } else if (choice == 3) {
                                boolean existingMedia= false;

                                for (Media medium : mediaMenu) {
                                    if (medium != null) {
                                        System.out.println(medium.toString());
                                        existingMedia= true;
                                    }
                                }
                                if (!existingMedia) {
                                    System.out.println("There exist no journal.");
                                }
                            }
                        }
                        break;
                    case 5:

                        System.out.println("You chose to print all items from all categories.");
                        boolean existingItem= false;
                        for(Items item : itemsMenu) {
                            if (item != null) {
                                System.out.println(item.toString());
                                existingItem= true;
                            }
                        }
                        if (!existingItem) {
                            System.out.println("There exist no item in any category.");
                        }
                        break;
                }
                break;
            case 2:
                System.out.println("You chose to manage clients. Please select an option:");
                System.out.println("\t1. Add client");
                System.out.println("\t2. Edit client");
                System.out.println("\t3. Delete client");

                int clientChoice = scn.nextInt();

                while (clientChoice < 1 || clientChoice > 3) {
                    System.out.println("Enter a valid output between 1 and 3");
                    clientChoice = scn.nextInt();
                }

                switch (clientChoice) {
                    case 1:
                        addClient(clientMenu);
                        break;
                    case 2:
                        System.out.println("You chose to edit a client. Please enter the client's ID:");
                        String clientID = scn.nextLine();
                        editClient(clientMenu, clientID);
                        break;
                    case 3:
                        System.out.println("You chose to delete a client. Please enter the client's ID:");
                        clientID = scn.nextLine();
                        scn.nextLine();
                        clientMenu = deleteClient(clientMenu, clientID);
                        break;
                }
                break;
            case 3:
                System.out.println("You chose to lease or return an item. Please select an option:");
                System.out.println("\t1. Lease an item");
                System.out.println("\t2. Return an item");

                int leaseReturnChoice = scn.nextInt();
                scn.nextLine();

                while (leaseReturnChoice < 1 || leaseReturnChoice > 2) {
                    System.out.println("Enter a valid output, either 1 or 2");
                    leaseReturnChoice = scn.nextInt();
                }

                switch (leaseReturnChoice) {
                    case 1:
                        System.out.println("You chose to lease an item. Please enter the client's ID:");
                        int clientID = scn.nextInt();
                        scn.nextLine();
                        System.out.println("Please enter the item's ID:");
                        String itemID = scn.nextLine();
                        leaseItem(clientID, itemID, clientMenu, itemsMenu);
                        break;
                    case 2:
                        System.out.println("You chose to return an item. Please enter the client's ID:");
                        clientID = scn.nextInt();
                        scn.nextLine();
                        System.out.println("Please enter the item's ID:");
                        itemID = scn.nextLine();
                        returnItem(clientID, itemID, clientMenu, itemsMenu);
                        break;
                }
                break;
            case 4:
                System.out.println("You chose to show all items leased by a client. Please enter the client's ID:");
                int clientId = scn.nextInt();
                // Call a method to get all items leased by the client
                // This method should return an array of items which you can then print
                // For example:
                Items[] itemsLeasedByClient = getItemsLeasedByClient(clientId, clientMenu, itemsMenu);
                if (itemsLeasedByClient == null) {
                    System.out.println("This client does not exist.");
                }
                else {
                    boolean clientHasLeasedItems = false;
                    for (Items item : itemsLeasedByClient) {
                        if (item != null) {
                            System.out.println(item.toString());
                            clientHasLeasedItems = true;
                        }
                    }
                    if (!clientHasLeasedItems) {
                        System.out.println("This client has not leased any items.");
                    }
                }
                break;
            case 5:
                System.out.println("You chose to show all items leased by all clients.");
                // Call a method to get all items leased by all clients
                // This method should return a 2D array where the first dimension is client IDs and the second dimension is items
                // For example:
                Items[][] itemsLeasedByAllClients = getItemsLeasedByAllClients(clientMenu, itemsMenu);
                boolean itemsHaveBeenLeased = false;
                for (int i = 0; i < itemsLeasedByAllClients.length; i++) {
                    for (Items item : itemsLeasedByAllClients[i]) {
                        if (item != null) {
                            System.out.println("Client " + i + ": " + item.toString());
                            itemsHaveBeenLeased = true;
                        }
                    }
                }
                if (!itemsHaveBeenLeased) {
                    System.out.println("No items have been leased.");
                }
                break;
            case 6:
                // Logic for displaying the biggest book
                System.out.println("The biggest book in the library is: " + getBiggestBook(bookMenu));
                break;

            case 7:
                // Logic for making a copy of the books array
                Books[] copiedBooksMenu = copyBooks(bookMenu);
                System.out.println("Copied books array:");
                for (Books book : copiedBooksMenu) {
                    if (book != null) {
                        System.out.println(book.toString());
                    }
                }
                break;

            case 8:
                // Quitting the program
                System.out.println("Goodbye!");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
                displayMenu();
                choice = scn.nextInt();
                handleMenuChoice(choice, scn, itemsMenu, clientMenu, bookMenu, mediaMenu, journalsMenu);
        }
    }


    // Executes a predefined scenario for demonstration purposes, creating multiple objects of items and users and displaying their information. Also tests object equality.
    private static void runPredefinedScenario() {
        // Detailed implementation, including object creation and equality checks.
        // Initialize variables and arrays for the scenario
        int index = 0;
        Items[] arrayAllItems = new Items[100];

        // Create some clients
        Client client1 = new Client();
        Client client2 = new Client("Alpha", 6005, "alpha@email.com");
        Client client3 = new Client(client2); // Assuming Client has a copy constructor
        Client client4 = new Client("Beta", 43880, "beta@email.com");
        Client[] arrayClient = {client1, client2, client3, client4};

        // Create some books
        Books[] arrayBooks = new Books[10];
        Books book1 = new Books(); // Assuming default constructor sets some properties
        arrayBooks[index++] = book1;
        Books book2 = new Books("13 reasons why", "John Doe", 1998, 350);
        arrayBooks[index++] = book2;
        Books book3 = new Books(book2); // Assuming Books has a copy constructor
        arrayBooks[index++] = book3;
        Books book4 = new Books("Geronimo Stilton", "Jane Doe", 2005, 150);
        arrayBooks[index++] = book4;
        index = 0; // Reset index for reuse

        // Create some media
        Media media1 = new Media();
        Media media2 = new Media("Ocean's eight", "Jonny Depp", 1979, "movie");
        Media media3 = new Media(media2); // Assuming Media has a copy constructor
        Media media4 = new Media("Almond", "tree", 1200, "plant");
        Media[] arrayMedia = {media1, media2, media3, media4};

        // Create some journals
        Journals journals1 = new Journals();
        Journals journals2 = new Journals("Spirou", "Robert Velter", 1938, 30);
        Journals journals3 = new Journals(journals2); // Assuming Journals has a copy constructor
        Journals journals4 = new Journals("Tintin", "Hergé", 1940, 20);
        Journals[] arrayJournals = {journals1, journals2, journals3, journals4};

        // Print all clients
        for (Client client : arrayClient) {
            if (client != null) {
                System.out.println(client.toString());
            }
        }
        System.out.println();

        // Print all books
        for (Books book : arrayBooks) {
            if (book != null) {
                System.out.println(book.toString());
            }
        }
        System.out.println();

        // Loop through each book in the arrayBooks array
        for (int i = 0; i < arrayBooks.length; i++) {
            // Compare the current book with every other book in the array starting from the next book to avoid redundant comparisons
            for (int j = i + 1; j < arrayBooks.length; j++) {
                // Ensure neither of the books being compared is null to avoid NullPointerException
                if (arrayBooks[i] != null && arrayBooks[j] != null) {
                    // Check if the two books are considered equal based on the .equals() method implementation
                    if (arrayBooks[i].equals(arrayBooks[j])) {
                        System.out.println(arrayBooks[i].getID() + " and "+ arrayBooks[j].getID() + " are copies of the same book");
                    }
                    if (!arrayBooks[i].equals(arrayBooks[j])) {
                        System.out.println(arrayBooks[i].getID() + " and "+ arrayBooks[j].getID() + " are two different books");
                    }
                }
            }
        }
        // Print a newline for better readability in output
        System.out.println();


        // Print all media
        for (Media media : arrayMedia) {
            if (media != null) {
                System.out.println(media.toString());
            }
        }
        System.out.println();

        // Iterate over all medias in the arrayMedia array to compare each media with every other media
        for (int i = 0; i < arrayMedia.length; i++) {
            for (int j = i + 1; j < arrayMedia.length; j++) {
                if (arrayMedia[i] != null && arrayMedia[j] != null) {
                    if (arrayMedia[i].equals(arrayMedia[j])) {
                        System.out.println(arrayMedia[i].getID() + " and "+ arrayMedia[j].getID() + " are copies of the same media");
                    }
                    if (!arrayMedia[i].equals(arrayMedia[j])) {
                        System.out.println(arrayMedia[i].getID() + " and "+ arrayMedia[j].getID() + " are two different medias");
                    }
                }
            }
        }
        System.out.println();

        // Print all journals
        for (Journals journal : arrayJournals) {
            if (journal != null) {
                System.out.println(journal.toString());
            }
        }
        System.out.println();

        // Iterate over all journals in the arrayJournals array to compare each journal with every other journal
        for (int i = 0; i < arrayJournals.length; i++) {
            for (int j = i + 1; j < arrayJournals.length; j++) {
                if (arrayJournals[i] != null && arrayJournals[j] != null) {
                    if (arrayJournals[i].equals(arrayJournals[j])) {
                        System.out.println(arrayJournals[i].getID() + " and "+ arrayJournals[j].getID() + " are copies of the same journal");
                    }
                    if (!arrayJournals[i].equals(arrayJournals[j])) {
                        System.out.println(arrayJournals[i].getID() + " and "+ arrayJournals[j].getID() + " are two different journals");
                    }
                }
            }
        }
        System.out.println();


        // Add books, media, and journals to arrayAllItems
        for (Books book : arrayBooks) {
            if (book != null) {
                arrayAllItems[index++] = book;
            }
        }
        for (Media media : arrayMedia) {
            if (media != null) {
                arrayAllItems[index++] = media;
            }
        }
        for (Journals journal : arrayJournals) {
            if (journal != null) {
                arrayAllItems[index++] = journal;
            }
        }

        for (int i = 0; i < 3; i++)//we stop at 3, just to look at a few items instead of all of them
        {
            for (int j = i + 1; j < 3; j++) {
                if (arrayAllItems[i] != null && arrayAllItems[j] != null) {
                    if (arrayAllItems[i].equals(arrayAllItems[j])) {
                        System.out.println(arrayAllItems[i].getID() + " and "+ arrayAllItems[j].getID() + " are copies of the same Item");
                    }
                    if (!arrayAllItems[i].equals(arrayAllItems[j])) {
                        System.out.println(arrayAllItems[i].getID() + " and "+ arrayAllItems[j].getID() + " are two different items");
                    }
                }
            }
        }
        System.out.println();

        // Assuming getBiggestBook can also handle Items[] as input
        System.out.println("\nThe book with the most pages is " + getBiggestBook(arrayBooks) + "\n");

        // Make a deep copy of the array of media and print the copied array
        Items[] copiedArray = copyBooks(arrayMedia);
        System.out.println("The array printed under is a copied array of media Items: \n");
        for (Items items : copiedArray) {
            if (items != null) {
                System.out.println(items.toString());
            }
        }
    }


    // This method finds the book with the most pages in an array of Books.
    public static Books getBiggestBook(Books[] books) {
        Books biggestBook = null; // Start with no biggest book found.
        // Iterate through each book in the provided array.
        for (Books book : books) {
            // If biggestBook is not yet set or current book has more pages than biggestBook, update biggestBook.
            if (biggestBook == null || (book != null && book.getNbOfPages() > biggestBook.getNbOfPages())) {
                biggestBook = book;
            }
        }
        // Return the book with the most pages.
        return biggestBook;
    }


    // This method creates a deep copy of an array of Items. It assumes that the Items class has a copy constructor.
    public static Items[] copyBooks(Items[] it) {
        Items[] copiedArray = new Items[it.length]; // Initialize a new array of the same length as the input.
        // Iterate over the input array.
        for (int i = 0; i < it.length; i++) {
            // If the current item is not null, use its copy constructor to create a new instance in the copiedArray.
            if (it[i] != null) {
                copiedArray[i] = new Items(it[i]); // Note: The Items class must have a copy constructor for this to work.
            }
        }
        // Return the deep copied array.
        return copiedArray;
    }


    // This method creates a deep copy of an array of Books. It assumes that the Books class has a copy constructor.
    public static Books[] copyBooks(Books[] books) {
        Books[] copiedBooks = new Books[books.length]; // Initialize a new array of the same length as the input.
        // Iterate over the input array.
        for (int i = 0; i < books.length; i++) {
            // If the current book is not null, use its copy constructor to create a new instance in the copiedBooks.
            if (books[i] != null) {
                copiedBooks[i] = new Books(books[i]);
            }
        }
        // Return the deep copied array.
        return copiedBooks;
    }

    // Displays the main menu for the library management system to the console.
    private static void displayMenu() {
        System.out.println("""
                FunReadings's Library Menu
                \t 1. Manage items (Add, Delete, Modify, List, Print)
                \t 2. Manage clients (Add, Edit, Delete)
                \t 3. Lease or return an item
                \t 4. Show all items leased by a client
                \t 5. Show all items leased by all clients
                \t 6. Display the biggest book in the library
                \t 7. Make a copy of the books array
                \t 8. Exit the program.""");
    }

    // Method to prompt the user for their choice regarding the type of item they wish to manage.
    public static int returnChoice() {
        // Create a Scanner instance to read user input from the console.
        Scanner scn = new Scanner(System.in);

        // Display the options to the user, asking them to choose an action for a specific type of item.
        System.out.println("Would you like to \n" +
                "\t 1. Do the action on a book \n" + // Option 1 for actions related to books.
                "\t 2. Do the action on a journal \n" + // Option 2 for actions related to journals.
                "\t 3. Do the action on a media"); // Option 3 for actions related to media items.

        // Read the user's choice from the console input.
        int choice = scn.nextInt();

        // Validate the user's choice to ensure it is within the expected range (1-3).
        while (choice < 1 || choice > 3) {
            // Prompt the user to enter a valid choice if the initial input is outside the valid range.
            System.out.println("Enter a valid output between 1 and 3");
            // Re-read the choice from the user.
            choice = scn.nextInt();
        }
        // Return the validated choice to the caller.
        return choice;
    }


    // Adds an item to the library, categorizing it based on user choice.
    public static void addItem(Items[] itemsMenu, Books[] books, Media[] medias, Journals[] journals,
                               int choice) {
        // Create a new Scanner object for reading user input
        Scanner scn = new Scanner(System.in);

        // Find the first available (null) index in each array to add a new item
        int index = getFirstNullIndex(itemsMenu); // For the general items array
        int indexBooks = getFirstNullIndex(books); // Specifically for books
        int indexJournals = getFirstNullIndex(journals); // Specifically for journals
        int indexMedia = getFirstNullIndex(medias); // Specifically for media items

        // Check if there's space to add new items in each category. If not, print a message and return.
        if (index == -1 || indexBooks == -1 || indexJournals == -1 || indexMedia == -1) {
            System.out.println("No space left to add more items.");
            return;
        }

        // Switch statement to handle different item additions based on the user's choice
        switch (choice) {
            case 1:
                // Prompt the user for book details

                System.out.println("You chose to add a book. Please enter the title, author, publication year, and number of pages:");
                // Read the book details from the user input
                String title = scn.nextLine();
                String author = scn.nextLine();
                int publicationYear = scn.nextInt();
                scn.nextLine();
                int nbOfPages = scn.nextInt();
                scn.nextLine();
                // Create a new Books object with the provided details
                Books book = new Books(title, author, publicationYear, nbOfPages);
                itemsMenu[index] = book;
                books[indexBooks] = book;
                // Print the details of the added book
                System.out.println(book.toString());
                break;

            // Case 2: Adding a new journal to the library
            case 2:
                // Prompt the user for journal details
                System.out.println("You chose to add a journal. Please enter the title, author, publication year, and volume number:");
                // Read the journal details from the user input
                title = scn.nextLine(); // Read the journal title
                author = scn.nextLine(); // Read the author's name
                publicationYear = scn.nextInt(); // Read the publication year
                scn.nextLine(); // Consume the remaining newline character
                int volumeNumber = scn.nextInt(); // Read the volume number
                scn.nextLine(); // Consume the remaining newline character

                // Create a new Journals object with the provided details
                Journals journal = new Journals(title, author, publicationYear, volumeNumber);
                // Add the journal to the general items array and the journals array
                itemsMenu[index] = journal;
                journals[indexJournals] = journal;
                // Print the details of the added journal
                System.out.println(journal.toString());
                break;
            // Case 3: Adding a new media item to the library
            case 3:
                // Prompt the user for media details
                System.out.println("You chose to add a media. Please enter the title, author, publication year, and type:");
                // Read the media details from the user input
                title = scn.nextLine(); // Read the media title
                author = scn.nextLine(); // Read the author's/director's name
                publicationYear = scn.nextInt(); // Read the publication/release year
                scn.nextLine(); // Consume the remaining newline character
                String type = scn.nextLine(); // Read the type of media (e.g., DVD, Blu-Ray, digital)
                // Create a new Media object with the provided details
                Media media = new Media(title, author, publicationYear, type);
                // Add the media item to the general items array and the media array
                itemsMenu[index] = media;
                medias[indexMedia] = media;
                // Print the details of the added media item
                System.out.println(media.toString());
                break;
        }
    }

    // Method to delete a book from the books array based on its ID.
    public static Books[] deleteItem(Books[] books, String itemID) {
        // Initialize nullIndex to -1, indicating that no match has been found yet.
        int nullIndex = -1;
        // Loop through the books array to find the book with the matching ID.
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null && books[i].getID().equals(itemID)) {
                // If found, set the book's position in the array to null.
                books[i] = null;
                // Record the index of the nullified book.
                nullIndex = i;
                // Exit the loop after finding and nullifying the book.
                break;
            }
        }
        // If a book was nullified, create a new array without the null entry.
        if (nullIndex != -1) {
            Books[] newBooks = new Books[books.length - 1];
            // Copy all non-null books to the new array.
            for (int i = 0, j = 0; i < books.length; i++) {
                if (i != nullIndex) {
                    newBooks[j++] = books[i];
                }
            }
            // Return the new array without the deleted book.
            return newBooks;
        }
        // If no book was deleted, return the original array.
        return books;
    }


    // Method to delete a journal from the journals array based on its ID.
    public static Journals[] deleteItem(Journals[] journals, String itemID) {
        // Initialize nullIndex to -1, indicating that no match has been found yet.
        int nullIndex = -1;
        // Loop through the journals array to find the journal with the matching ID.
        for (int i = 0; i < journals.length; i++) {
            if (journals[i] != null && journals[i].getID().equals(itemID)) {
                // If found, set the journal's position in the array to null.
                journals[i] = null;
                // Record the index of the nullified journal.
                nullIndex = i;
                // Exit the loop after finding and nullifying the journal.
                break;

            }
        }
        // Check if a journal was successfully marked for deletion by verifying if nullIndex is not -1.
        if (nullIndex != -1) {
            // Create a new array of Journals with a length one less than the original,
            // as we're removing the journal that was deleted.
            Journals[] newJournals = new Journals[journals.length - 1];
            // Iterate over the original journals array to copy all journals except the deleted one into the new array.
            for (int i = 0, j = 0; i < journals.length; i++) {
                // If the current index does not match the index of the deleted journal (nullIndex), copy the journal.
                if (i != nullIndex) {
                    newJournals[j++] = journals[i];
                }
            }
            // Return the new array without the deleted journal.
            return newJournals;
        }
// If no journal was deleted (i.e., nullIndex remains -1), return the original array unchanged.
        return journals;

    }

    // Method to delete a media item from the media array based on its ID.
    public static Media[] deleteItem(Media[] media, String itemID) {
        // Initialize nullIndex to -1, indicating that no match has been found yet.
        int nullIndex = -1;
        // Loop through the media array to find the media item with the matching ID.
        for (int i = 0; i < media.length; i++) {
            if (media[i] != null && media[i].getID().equals(itemID)) {
                // If found, set the media item's position in the array to null.
                media[i] = null;
                // Record the index of the nullified media item.
                nullIndex = i;
                // Exit the loop after finding and nullifying the media item.
                break;
            }
        }
        // If a media item was nullified, create a new array without the null entry.
        if (nullIndex != -1) {
            Media[] newMedia = new Media[media.length - 1];
            // Copy all non-null media items to the new array.
            for (int i = 0, j = 0; i < media.length; i++) {
                if (i != nullIndex) {
                    newMedia[j++] = media[i];
                }
            }
            // Return the new array without the deleted media item.
            return newMedia;
        }
        // If no media item was deleted, return the original array.
        return media;
    }

    // Method to delete an item from the items array based on its ID.
    public static Items[] deleteItem(Items[] items, String itemID) {
        // Initialize nullIndex to -1 to indicate that no matching item has been found.
        int nullIndex = -1;
        // Iterate through the items array to find the item with the given ID.
        for (int i = 0; i < items.length; i++) {
            // Check if the current item is not null and if its ID matches the provided itemID.
            if (items[i] != null && items[i].getID().equals(itemID)) {
                // If a match is found, set the item's position in the array to null.
                items[i] = null;
                // Record the index of the nullified item to use in the creation of a new array.
                nullIndex = i;
                // Break out of the loop since the matching item has been found and nullified.
                break;
            }
        }
        // If an item was found and nullified (indicated by nullIndex being updated),
        // create a new array to hold the items minus the deleted one.
        if (nullIndex != -1) {
            Items[] newItems = new Items[items.length - 1]; // New array with a size reduced by one.
            // Copy all items except the nullified one into the new array.
            for (int i = 0, j = 0; i < items.length; i++) {
                if (i != nullIndex) { // Skip the nullified item's index.
                    newItems[j++] = items[i]; // Add item to the new array and increment the position.
                }
            }
            // Return the new array, excluding the deleted item.
            return newItems;
        }
        // If no matching item was found and nullified, return the original array unchanged.
        return items;
    }

    // Method to change the details of a specific book in the books array, identified by itemID.
    private static Books change(Books[] books, String itemID) {
        // Create a new Scanner object for reading user input from the console.
        Scanner scn = new Scanner(System.in);

        // Iterate through the array of books.
        for (Books book : books) {
            // Check if the current book is not null and if its ID matches the provided itemID.
            if (book != null && book.getID().equals(itemID)) {
                // Prompt the user to enter the new details for the book: title, author, publication year, and number of pages.
                System.out.println("Enter the new title, author, publication year, and number of pages:");
                String title = scn.nextLine(); // Read the new title from user input.
                String author = scn.nextLine(); // Read the new author from user input.
                int publicationYear = scn.nextInt(); // Read the new publication year from user input.
                int nbOfPages = scn.nextInt(); // Read the new number of pages from user input.
                scn.nextLine(); // Consume any leftover newline character.

                // Update the book's details with the new values.
                book.setTitle(title);
                book.setAuthor(author);
                book.setPublicationYear(publicationYear);
                book.setNbOfPages(nbOfPages);

                // Return the updated book object.
                return book;
            }
        }
        // If no book with the matching ID is found, return null.
        return null;
    }

    // Method to change the details of a specific journal in the journals array, identified by itemID.
    private static Journals change(Journals[] journals, String itemID) {
        // Create a new Scanner object to read user input from the console.
        Scanner scn = new Scanner(System.in);

        // Iterate through the array of journals.
        for (Journals journal : journals) {
            // Check if the current journal is not null and if its ID matches the provided itemID.
            if (journal != null && journal.getID().equals(itemID)) {
                // Prompt the user to enter new details for the journal: title, author, publication year, and volume number.
                System.out.println("Enter the new title, author, publication year, and volume number:");
                String title = scn.nextLine(); // Read the new title from user input.
                String author = scn.nextLine(); // Read the new author from user input.
                int publicationYear = scn.nextInt(); // Read the new publication year from user input.
                int volumeNumber = scn.nextInt(); // Read the new volume number from user input.
                scn.nextLine(); // Consume any leftover newline character to prepare for the next input.

                // Update the journal's details with the new values provided by the user.
                journal.setTitle(title);
                journal.setAuthor(author);
                journal.setPublicationYear(publicationYear);
                journal.setVolumeNumber(volumeNumber);

                // Return the updated journal object, indicating successful modification.
                return journal;
            }
        }
        // If no journal with the matching ID is found, return null to indicate failure to update.
        return null;
    }

    // Method to change the details of a specific media item in the media array, identified by itemID.
    private static Media change(Media[] media, String itemID) {
        // Create a new Scanner object for reading user inputs from the console.
        Scanner scn = new Scanner(System.in);

        // Iterate through the array of media items.
        for (Media medium : media) {
            // Check if the current media item is not null and if its ID matches the provided itemID.
            if (medium != null && medium.getID().equals(itemID)) {
                // Prompt the user to enter new details for the media item: title, author, publication year, and type.
                System.out.println("Enter the new title, author, publication year, and type:");
                String title = scn.nextLine(); // Read the new title from user input.
                String author = scn.nextLine(); // Read the new author from user input.
                int publicationYear = scn.nextInt(); // Read the new publication year from user input.
                scn.nextLine(); // Consume the leftover newline character from the integer input above.
                String type = scn.nextLine(); // Read the new type from user input.

                // Update the media item's details with the new values provided by the user.
                medium.setTitle(title);
                medium.setAuthor(author);
                medium.setPublicationYear(publicationYear);
                medium.setType(type);

                // Return the updated media object, indicating successful modification.
                return medium;
            }
        }
        // If no media item with the matching ID is found, return null to indicate failure to update.
        return null;
    }

    // Method to identify a book to be changed based on the provided itemID.
    private static void change(Items[] items, String itemID, Books[] books) {
        // Initialize a variable to hold the reference to the book to be changed.
        Books bookToChange = null;

        // Iterate through the array of books to find the one with the matching itemID.
        for (Books book : books) {
            // Check each book to see if it is not null and if its ID matches the given itemID.
            if (book != null && book.getID().equals(itemID)) {
                // If a match is found, store the reference to this book in bookToChange.
                bookToChange = book;
                // Exit the loop since the book has been found.
                break;
            }
        }

        // After the loop, check if the bookToChange variable has been assigned a book object.
        if (bookToChange == null) {
            // If bookToChange is still null, it means no matching book was found in the array.
            // In this case, simply return from the method without making any changes.
            return;
        }
        // After identifying the book to change, this loop iterates through the general items array to update the item with matching ID.
        for (Items item : items) {
            // Check if the current item is not null and if its ID matches the given itemID.
            if (item != null && item.getID().equals(itemID)) {
                // Update the general item's title, author, and publication year to match the bookToChange's details.
                item.setTitle(bookToChange.getTitle());
                item.setAuthor(bookToChange.getAuthor());
                item.setPublicationYear(bookToChange.getPublicationYear());

                // Check if the current item is specifically an instance of Books.
                if (item instanceof Books) {
                    // If it is, cast the item to Books and update its number of pages to match bookToChange.
                    ((Books) item).setNbOfPages(bookToChange.getNbOfPages());
                }
                // Once the matching item is found and updated, exit the loop to avoid unnecessary iterations.
                break;
            }
        }
    }
    // Method to update the details of a specific item in the items array based on information from a Journals object.
    private static void change(Items[] items, String itemID, Journals[] journals) {
        // Variable to hold the reference to the journal to be changed.
        Journals journalToChange = null;

        // Loop through the journals array to find the journal with the matching itemID.
        for (Journals journal : journals) {
            // Check if the current journal is not null and if its ID matches the provided itemID.
            if (journal != null && journal.getID().equals(itemID)) {
                // If a match is found, store the reference to this journal in journalToChange.
                journalToChange = journal;
                // Break the loop as the target journal has been found.
                break;
            }
        }

        // If the journal with the specified ID is not found, exit the method.
        if (journalToChange == null) {
            return;
        }

        // Loop through the items array to find the item with the same ID as the journal.
        for (Items item : items) {
            // Check if the current item is not null and if its ID matches the itemID.
            if (item != null && item.getID().equals(itemID)) {
                // Update the item's title, author, and publication year to match the journal's details.
                item.setTitle(journalToChange.getTitle());
                item.setAuthor(journalToChange.getAuthor());
                item.setPublicationYear(journalToChange.getPublicationYear());

                // Check if the current item is specifically an instance of Journals.
                if (item instanceof Journals) {
                    // If it is, cast the item to Journals and update its volume number to match journalToChange.
                    ((Journals) item).setVolumeNumber(journalToChange.getVolumeNumber());
                }
                // Once the matching item is found and updated, break the loop to avoid unnecessary iterations.
                break;
            }
        }
    }


    // Method to update the details of an item in the items array based on information from a Media object.
    private static void change(Items[] items, String itemID, Media[] media) {
        // Variable to hold the reference to the media item to be updated.
        Media mediaToChange = null;

        // Loop through the media array to find the media item with the matching itemID.
        for (Media medium : media) {
            // Check if the current media item is not null and its ID matches the provided itemID.
            if (medium != null && medium.getID().equals(itemID)) {
                // If a match is found, store the reference to this media item in mediaToChange.
                mediaToChange = medium;
                // Exit the loop since the target media item has been found.
                break;
            }
        }

        // If no media item with the specified ID is found, exit the method.
        if (mediaToChange == null) {
            return;
        }

        // Loop through the items array to find the item with the same ID as the media item.
        for (Items item : items) {
            // Check if the current item is not null and if its ID matches the itemID.
            if (item != null && item.getID().equals(itemID)) {
                // Update the item's title, author, and publication year to match the media item's details.
                item.setTitle(mediaToChange.getTitle());
                item.setAuthor(mediaToChange.getAuthor());
                item.setPublicationYear(mediaToChange.getPublicationYear());

                // Further check if the current item is specifically an instance of Media.
                if (item instanceof Media) {
                    // If it is, cast the item to Media and update its type to match mediaToChange.
                    ((Media) item).setType(mediaToChange.getType());
                }
                // Once the matching item is found and updated, break the loop to avoid further unnecessary iterations.
                break;
            }
        }
    }


    // Adds a new client to the clients array.
    public static void addClient(Client[] clientM) {
        // Find the first null index in the clients array to determine where to add the new client.
        int index = getFirstNullIndex(clientM);
        // If no null index is found, the array is full, and no more clients can be added.
        if (index == -1) {
            System.out.println("No space left to add more clients.");
            return;
        }
        // Create a Scanner object to read user input.
        Scanner scn = new Scanner(System.in);
        // Prompt the user to enter the new client's name, phone number, and email.
        System.out.println("Enter the client's name, phone number, and email:");
        String name = scn.nextLine();
        long phoneNumber = scn.nextLong();
        scn.nextLine(); // Consume the newline character left after reading the phone number.
        String email = scn.nextLine();
        // Create a new Client object with the provided details.
        Client client = new Client(name, phoneNumber, email);
        // Add the new client to the clients array at the found null index.
        clientM[index] = client;

        // Iterate over the clients array and print the details of each client.
        for(Client clientC: clientM){
            if(clientC != null){
                System.out.println(clientC.toString());
            }
        }
    }
    // Edits the details of an existing client in the client array based on the client's ID.
    public static void editClient(Client[] clientArray, String clientID){
        // Check if the client array is not null.
        if(clientArray == null){
            System.out.println("No clients to edit");
            return;
        }

        // Iterate over the client array to find the client with the matching ID.
        for(Client clientB : clientArray){
            if (clientB != null && clientB.getID().equals(clientID)){
                // Once the client is found, prompt the user to enter new details for the client.
                Scanner scn = new Scanner(System.in);
                System.out.println("Enter the new name, phone number, and email:");
                String name = scn.nextLine();
                long phoneNumber = scn.nextLong();
                scn.nextLine(); // Consume the newline character left after reading the phone number.
                String email = scn.nextLine();
                // Update the client's details with the new information provided by the user.
                clientB.setEmail(email);
                clientB.setName(name);
                clientB.setPhoneNumber(phoneNumber);
                // Exit the method after updating the client's details.
                return;
            }
        }
    }

    // Deletes a client from the client array based on the client's ID.
    private static Client[] deleteClient(Client[] clientMenu, String clientID) {
        // Initialize an index to track if and where a null value is found.
        int nullIndex = -1;
        // Iterate through the client array to find the matching client ID.
        for (int i = 0; i < clientMenu.length; i++) {
            if (clientMenu[i] != null && clientMenu[i].getID().equals(clientID)) {
                // Set the found client slot to null, effectively removing the client.
                clientMenu[i] = null;
                // Record the index of the removed client.
                nullIndex = i;
                // Break the loop as the target client has been found and removed.
                break;
            }
        }
        // If a client was removed, create a new array without the null entry.
        if (nullIndex != -1) {
            Client[] newClientMenu = new Client[clientMenu.length - 1];
            // Copy all clients except the removed one into the new array.
            for (int i = 0, j = 0; i < clientMenu.length; i++) {
                if (i != nullIndex) {
                    newClientMenu[j++] = clientMenu[i];
                }
            }
            // Return the new client array without the deleted client.
            return newClientMenu;
        }
        // If no client was deleted (no match found), return the original array.
        return clientMenu;
    }

    // Leases an item to a client based on their respective IDs.
    private static void leaseItem(int clientID, String itemID, Client[] clientMenu, Items[] itemsMenu){
        // Convert the client ID from int to String for comparison.
        String clientIdString = Integer.toString(clientID);

        // Iterate through the client array to find the matching client.
        for (Client client : clientMenu) {
            if (client != null && client.getID().equals(clientIdString)) {
                // Once the client is found, search for the item in the items array.
                for (Items item : itemsMenu) {
                    if (item != null && item.getID().equals(itemID)) {
                        // Find the first null index in the client's item array to add the new item.
                        int index = getFirstNullIndex(client.getItems());
                        if (index != -1) {
                            // If there is space, add the item to the client's items array.
                            client.getItems()[index] = item;
                            System.out.println("The item has been leased to the client.");
                        } else {
                            // If there is no space left, notify that the client has reached their lease limit.
                            System.out.println("The client has leased the maximum number of items.");
                        }
                        // Exit the method as the leasing process is complete.
                        return;
                    }
                }
                // If the item is not found, inform the user and exit the method.
                System.out.println("The item does not exist.");
                return;
            }
        }
        // If the client is not found, inform the user and exit the method.
        System.out.println("The client does not exist.");
    }

    // Method to process the return of an item by a client.
    private static void returnItem(int clientID, String itemID, Client[] clientMenu, Items[] itemsMenu) {
        // Convert the client ID from integer to String for comparison purposes.
        String clientIdString = Integer.toString(clientID);

        // Iterate through the client array to find the matching client by ID.
        for (Client client : clientMenu) {
            if (client != null && client.getID().equals(clientIdString)) {
                // Once the client is found, search through their list of leased items.
                for (int i = 0; i < client.getItems().length; i++) {
                    // Check if the current item matches the itemID and is not null.
                    if (client.getItems()[i] != null && client.getItems()[i].getID().equals(itemID)) {
                        // Create a new array for the client's items, excluding the returned item.
                        Items[] newItems = new Items[client.getItems().length - 1];
                        for (int j = 0, k = 0; j < client.getItems().length; j++) {
                            if (j != i) { // Skip the item being returned.
                                newItems[k++] = client.getItems()[j]; // Add other items to the new array.
                            }
                        }
                        // Update the client's items array to the new array without the returned item.
                        client.setItems(newItems);
                        // Notify that the item has been successfully returned.
                        System.out.println("The item has been returned.");
                        return; // Exit the method as the item return process is complete.
                    }
                }
                // If the item was not found in the client's list, indicate that the client did not lease this item.
                System.out.println("The client has not leased the item.");
                return; // Exit the method as the specified item was not found.
            }
        }
        // If the client was not found in the array, notify that the client does not exist.
        System.out.println("The client does not exist.");
    }

    // Method to compile a two-dimensional array of items leased by all clients.
    private static Items[][] getItemsLeasedByAllClients(Client[] clientMenu, Items[] itemsMenu) {
        // Initialize a 2D array to store items leased by each client.
        Items[][] itemsLeasedByAllClients = new Items[clientMenu.length][itemsMenu.length];
        // Iterate through the clientMenu to access each client's leased items.
        for (int i = 0; i < clientMenu.length; i++) {
            if (clientMenu[i] != null) {
                // Assign the client's leased items to the corresponding index in the 2D array.
                itemsLeasedByAllClients[i] = clientMenu[i].getItems();
            }
        }
        // Return the compiled 2D array of leased items.
        return itemsLeasedByAllClients;
    }

    // Method to retrieve the array of items leased by a specific client, identified by clientId.
    private static Items[] getItemsLeasedByClient(int clientId, Client[] clientMenu, Items[] itemsMenu) {
        // Iterate through the clientMenu to find the specified client by their ID.
        for (Client client : clientMenu) {
            if (client != null && client.getID().equals(String.valueOf(clientId))) {
                // Check if the client has leased items; if not, return null.
                if (client.getItems() == null || client.getItems().length == 0) {
                    return null;
                }
                // Return the array of items leased by the found client.
                return client.getItems();
            }
        }
        // If the client is not found, return null.
        return null;
    }

    // Searches through an array of Items to find the first null position.
    public static int getFirstNullIndex(Items[] items) {
        // Iterate over the entire length of the items array.
        for (int i = 0; i < items.length; i++) {
            // Check if the current index holds a null value.
            if (items[i] == null) {
                // If a null value is found, return the current index as the first null index.
                return i;
            }
        }
        // If the loop completes without finding a null value, return -1 indicating the array is full.
        return -1;
    }

    // Searches through an array of Books to find the first null position.
    public static int getFirstNullIndex(Books[] books) {
        // Iterate over the entire length of the books array.
        for (int i = 0; i < books.length; i++) {
            // Check if the current index holds a null value.
            if (books[i] == null) {
                // If a null value is found, return the current index as the first null index.
                return i;
            }
        }
        // If the loop completes without finding a null value, return -1 indicating the array is full.
        return -1;
    }

    // Method to find the first null index in an array of Journals.
// This is useful for determining where a new journal can be added.
    public static int getFirstNullIndex(Journals[] journals) {
        // Iterate through the journals array.
        for (int i = 0; i < journals.length; i++) {
            // Check if the current index is null.
            if (journals[i] == null) {
                // Return the index of the first null element found.
                return i;
            }
        }
        // If no null index is found, return -1 to indicate the array is full.
        return -1;
    }


    // Method to find the first null index in an array of Media.
// Helps in identifying an available slot for adding a new media item.
    public static int getFirstNullIndex(Media[] media) {
        // Loop through the media array.
        for (int i = 0; i < media.length; i++) {
            // Check for a null entry.
            if (media[i] == null) {
                // Return the position of the first null entry found.
                return i;
            }
        }
        // Return -1 if the array has no null entries, indicating it's full.
        return -1;
    }
    // Method to locate the first null position in an array of Client objects.
// Essential for adding a new client to the system without displacing others.
    public static int getFirstNullIndex(Client[] client) {
        // Iterate over the client array.
        for (int i = 0; i < client.length; i++) {
            // Search for the first instance of a null entry.
            if (client[i] == null) {
                // If found, return the index of that null entry.
                return i;
            }
        }
        // If the method iterates through the entire array without finding a null entry, return -1.
        return -1;
    }
}