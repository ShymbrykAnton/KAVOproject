package blogic.model;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {
    private long id;
    private String fName;
    private String lName;
    private int age;
    private String city;

    public Person() {
    }

    public Person(long id, String fName, String lname, int age, String city) {
        this.id = id;
        this.fName = fName;
        this.lName = lname;
        this.age = age;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                age == person.age &&
                fName.equals(person.fName) &&
                lName.equals(person.lName) &&
                city.equals(person.city);
    }

    @Override
    public String toString() {
        return "Id: " + id + ". First Name: " + fName + ". Last Name: " + lName + ". Age: " + age + ". City: " + city;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fName, lName, age, city);
    }

}
