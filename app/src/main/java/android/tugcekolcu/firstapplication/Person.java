package android.tugcekolcu.firstapplication;

/**
 * Created by tugcekolcu on 28.01.2018.
 */

public class Person {

    private String name;
    private String surname;
    private String age;
    private int id;

    public Person(String name, String surname, String age) {
        this.name = name;
        this.surname = surname;
        this.age = age;

    }

    public Person(int id, String name, String surname, String age) {
        this.id=id;
        this.name = name;
        this.surname = surname;
        this.age = age;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
