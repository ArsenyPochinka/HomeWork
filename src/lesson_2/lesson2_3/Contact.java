package lesson_2.lesson2_3;

import java.util.*;

public class Contact {
    private final String name;
    private final String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return name.equals(contact.name) && number.equals(contact.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number);
    }

    public static Contact generateRandomContact() {
        Random random = new Random();
        List<String> names = Arrays.asList("Ivanov", "Petrov", "Sidorov", "Pochinka", "Firer", "Kirsenko", "Khafizov", "Terentiev");
        String randomName = names.get(random.nextInt(names.size()));
        String randomNumber = "+7(999)" + Integer.toString(random.nextInt(1000_000_0));
        return new Contact(randomName, randomNumber);
    }

    public static ArrayList<String> getNumberOfContactByName(String someName, HashSet<Contact> phoneDirectory) {
        ArrayList<String> numbers = new ArrayList<>();
        Iterator<Contact> iter = phoneDirectory.iterator();
        while (iter.hasNext()) {
            Contact someContact = iter.next();
            if (someContact.getName() == someName) {
                numbers.add(someContact.getNumber());
            }
        }
        return numbers;
    }

    public static void printContact(Contact contact) {
        System.out.println(contact.getName() + ": " + contact.getNumber());
    }

}
