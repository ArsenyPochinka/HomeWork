package lesson2_3;

import java.util.*;

public class PhoneDirectory {
    private HashSet<Contact> phoneDirectory = phoneDirectory = new HashSet<>();;

    public HashSet<Contact> getPhoneDirectory() {
        return phoneDirectory;
    }

    public HashSet<Contact> createRandomPhoneDirectory(int quantity) {
        for (int i = 0; i < quantity; i++) {
            phoneDirectory.add(Contact.generateRandomContact());
        }
        return phoneDirectory;
    }

    public String get(String name) {
        return (name + ": " + Contact.getNumberOfContactByName(name, phoneDirectory));
    }

    public void add(String newName, String newNumber) {
        phoneDirectory.add(new Contact(newName,newNumber));
    }

    public void printPhoneDirectory() {
        for(Contact contact: phoneDirectory) {
            Contact.printContact(contact);
        }


    }

}
