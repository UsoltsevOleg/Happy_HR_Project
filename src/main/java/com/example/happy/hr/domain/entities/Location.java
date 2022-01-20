package com.example.happy.hr.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/*  Класс-сущность (локация)    */

@Entity
@Table(name = "location")
@NoArgsConstructor  // Хибернейт сам будет генерировать id для сущностей
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Integer id;

    @Length(max = 1024)
    private String address;

    private Boolean office;

    private Boolean outsource;

    public Location(String address, Boolean office, Boolean outsource) {
        this.address = address;
        this.office = office;
        this.outsource = outsource;
    }
}
