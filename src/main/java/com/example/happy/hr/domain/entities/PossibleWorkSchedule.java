package com.example.happy.hr.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*  Класс-сущность (сдвиг по времени)   */

@Entity
@Table(name = "possible_work_schedule")
@NoArgsConstructor
@Data
public class PossibleWorkSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pws_id")
    private Integer id;

    @Column(name = "has_flextime")
    private Boolean flextime;               // Можно ли работать со сдвигом по времени

    @Column(name = "schedule_description")
    private String scheduleDescription;     // Какой график по времени возможен

    public PossibleWorkSchedule(Boolean flextime, String scheduleDescription) {
        this.flextime = flextime;
        this.scheduleDescription = scheduleDescription;
    }
}
