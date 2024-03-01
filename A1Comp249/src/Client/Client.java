//-----------------------------------------
// Assignment 1
// Part 2
// Written by: Étienne Beaumier, 40211362
//             Romero FAUSTIN,   40234898
//-----------------------------------------

/*
Client class: This class represents a client with unique identifiers, name, phone number, and email. It has a static
counter that increments each time a new client object is created, providing a unique ID for each client. It includes
constructors, getters, setters, and methods to represent the client as a string and to compare two clients.
 */

package Client;

import Library.*;

public class Client {

    // Counter to generate unique IDs
    private static int counter = 0;
    // Unique identifier for each client
    private String ID;
    // Name of the client
    private String name;
    // Phone number of the client
    private long phoneNumber;
    // Email of the client
    private String email;

    private Items[] items = new Items[100];

    // Default constructor
    public Client() {
        // Increment the counter and assign it as ID
        counter++;
        this.ID = Integer.toString(counter);
        // Default values for other fields
        this.name = "name";
        this.phoneNumber = 0L;
        this.email = "default@email.com";
    }

    // Constructor with parameters
    public Client(String name, long phoneNumber, String email) {
        // Increment the counter and assign it as ID
        counter++;
        this.ID = Integer.toString(counter);
        // Assign values from parameters
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Copy constructor
    public Client(Client client) {
        // Increment the counter and assign it as ID
        counter++;
        this.ID = Integer.toString(counter);
        // Copy values from the given client
        this.name = client.name;
        this.phoneNumber = client.phoneNumber;
        this.email = client.email;
    }

    // Getters and setters for the fields
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Items[] getItems() {
        return items;
    }

    public void setItems(Items[] items) {
        this.items = items;
    }

    // Method to represent the item as a string
    @Override
    public String toString() {
        return "Client " +
                "ID='" + ID + '\'' +
                ", their name is '" + name + '\'' +
                ", their phone number is " + phoneNumber +
                ", and their email is '" + email;
    }

    // Method to compare two items
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        } else if (getClass() != otherObject.getClass()) {
            return false;
        } else {
            Client otherClient = (Client) otherObject;
            return (name.equals(otherClient.name)
                    && email.equals(otherClient.email)
                    && phoneNumber == otherClient.phoneNumber);
        }
    }


}