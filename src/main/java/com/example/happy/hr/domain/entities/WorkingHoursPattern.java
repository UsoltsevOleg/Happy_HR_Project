package com.example.happy.hr.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

/*  Класс-сущность (график работы)  */

@Entity
@Table(name = "working_hours_pattern")
@Data
@NoArgsConstructor
public class WorkingHoursPattern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "whp_id")
    private Integer id;

    @Column(name = "whp_from")
    private Time whpFrom;

    @Column(name = "whp_till")
    private Time whpTill;

    private String overtime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "possible_work_schedule_id")
    private PossibleWorkSchedule possibleWorkSchedule;

    public WorkingHoursPattern(Time whpFrom, Time whpTill, String overtime,
                               PossibleWorkSchedule possibleWorkSchedule) {
        this.whpFrom = whpFrom;
        this.whpTill = whpTill;
        this.overtime = overtime;
        this.possibleWorkSchedule = possibleWorkSchedule;
    }
}
