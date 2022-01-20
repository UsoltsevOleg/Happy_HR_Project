package com.example.happy.hr.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

/*  Класс-сущность (пользователь)   */

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Length(max = 150)
    private String surname;

    @Length(max = 150)
    private String name;

    @Length(max = 150)
    private String patronymic;

    @Length(min = 6, max = 150)
    private String phone;

    @Email
    @Length(max = 150)
    private String email;

    @Length(max = 1024)
    private String login;

    @Length(max = 1024)
    private String password;

    /*  Пользователь может создать много карточек. Поэтому к 1 пользователю много карточек
        CascadeType.REMOVE нет, тк если пользователь удалится, то карточки можно оставить */
    @OneToMany(mappedBy = "cardAuthor", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    List<ProjectCard> cards;

    public User(String surname, String name,
                String patronymic, String phone,
                String email, String login, String password) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phone = phone;
        this.email = email;
        this.login = login;
        this.password = password;
    }
}
