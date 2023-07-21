import java.io.*;
import java.util.*;

class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nPhone Number: " + phoneNumber + "\nEmail Address: " + emailAddress;
    }
}

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public void saveContactsToFile(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(contacts);
            System.out.println("Contacts saved to file.");
        } catch (IOException e) {
            System.out.println("Failed to save contacts to file.");
        }
    }

    @SuppressWarnings("unchecked")
    public void loadContactsFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            contacts = (List<Contact>) ois.readObject();
            System.out.println("Contacts loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load contacts from file.");
        }
    }
}

public class AddressBookSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();

        int choice;

        do {
            displayOptions();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addContact(scanner, addressBook);
                    break;
                case 2:
                    removeContact(scanner, addressBook);
                    break;
                case 3:
                    searchContact(scanner, addressBook);
                    break;
                case 4:
                    displayAllContacts(addressBook);
                    break;
                case 5:
                    saveContactsToFile(scanner, addressBook);
                    break;
                case 6:
                    loadContactsFromFile(scanner, addressBook);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        } while (choice != 0);

        scanner.close();
    }

    private static void displayOptions() {
        System.out.println("Address Book Options:");
        System.out.println("1. Add Contact");
        System.out.println("2. Remove Contact");
        System.out.println("3. Search Contact");
        System.out.println("4. Display All Contacts");
        System.out.println("5. Save Contacts to File");
        System.out.println("6. Load Contacts from File");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addContact(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter the email address: ");
        String emailAddress = scanner.nextLine();

        Contact contact = new Contact(name, phoneNumber, emailAddress);
        addressBook.addContact(contact);
        System.out.println("Contact added successfully.");
    }

    private static void removeContact(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter the name of the contact to remove: ");
        String name = scanner.nextLine();

        Contact contact = addressBook.searchContact(name);
        if (contact != null) {
            addressBook.removeContact(contact);
            System.out.println("Contact removed successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void searchContact(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter the name of the contact to search: ");
        String name = scanner.nextLine();

        Contact contact = addressBook.searchContact(name);
        if (contact != null) {
            System.out.println("Contact found:");
            System.out.println(contact);
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void displayAllContacts(AddressBook addressBook) {
        List<Contact> contacts = addressBook.getAllContacts();
        if (!contacts.isEmpty()) {
            System.out.println("All Contacts:");
            for (Contact contact : contacts) {
                System.out.println(contact);
                System.out.println();
            }
        } else {
            System.out.println("No contacts found.");
        }
    }

    private static void saveContactsToFile(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter the file path to save contacts: ");
        String filePath = scanner.nextLine();
        addressBook.saveContactsToFile(filePath);
    }

    private static void loadContactsFromFile(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter the file path to load contacts: ");
        String filePath = scanner.nextLine();
        addressBook.loadContactsFromFile(filePath);
    }
}


