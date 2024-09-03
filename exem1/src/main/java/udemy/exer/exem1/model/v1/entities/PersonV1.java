package udemy.exer.exem1.model.v1.entities;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "tb_person")
public class PersonV1 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long key;
    @Column(name = "tb_person_first_name", nullable = false)
    private String firstName;
    @Column(name = "tb_person_last_name", nullable = false)
    private String lastName;
    @Column(name = "tb_person_address")
    private String address;
    @Column(name = "tb_person_gender")
    private String gender;

    public PersonV1() {
    }
    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
