package com.example.airline.model;

import com.example.airline.model.enumerations.PassengerLevelType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Data
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true)
    private String passport;

    private String surname;
    private String name;
    private String patronymic;
    private BigDecimal age;

    @Enumerated(EnumType.STRING)
    private PassengerLevelType level;

    String login;
    String password;

    //ToDo или делать EAGER, таща все тикеты со всеми реквизитами, или убирать
//    @OneToMany(mappedBy = "passenger")
//    private List<Ticket> tickets;


    public Long getId() {
        return id;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public PassengerLevelType getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = PassengerLevelType.valueOf(level);
    }

//    public List<Ticket> getTickets() {
//        return tickets;
//    }
//
//    public void setTickets(List<Ticket> tickets) {
//        this.tickets = tickets;
//    }

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }
}
