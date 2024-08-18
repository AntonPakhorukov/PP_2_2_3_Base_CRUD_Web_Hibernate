package web.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 30 characters")
    private String name;
    @Min(value = 0, message = "Age should be greater than 0")
    private int age;
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email should not be empty")
    private String email;

    public User() {

    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User: id = " + id + ", name = '" + name + "', age = " + age + ", email = '" + email + "'";
    }
}