package edu.hw3;

public class Person {

    private final String name;
    private final String surname;

    public Person(String input) {
        var contact = input.split(" ");
        this.name = contact[0];
        if (contact.length > 1) {
            this.surname = contact[1];
        } else {
            this.surname = "";
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + surname.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) {
            return false;
        }

        Person p = (Person) obj;
        var fullName1 = this.name + this.surname;
        var fullName2 = p.name + p.surname;
        return fullName1.equals(fullName2);
    }
}
