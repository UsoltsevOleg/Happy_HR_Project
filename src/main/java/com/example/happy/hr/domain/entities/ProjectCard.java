package com.example.happy.hr.domain.entities;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/*  Класс-сущность (карточка проекта)   */

@Entity
@Table(name = "project_card")
@NoArgsConstructor
@Data
//  Строковый массив мапится на массив varchar в бд
@TypeDef(
        name = "string-array",
        typeClass = StringArrayType.class
)
public class ProjectCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proj_card_id")
    private Integer id;

    @Length(max = 500)
    @Column(name = "project_name")
    private String projectName;

    @Length(max = 500)
    @Column(name = "proj_client_name")
    private String projClientName;

    @Column(name = "project_stage")
    private String projectStage;

    @Length(max = 1024)
    @Column(name = "functional_direction")
    private String functionalDirection;

    @Length(max = 1024)
    @Column(name = "subject_area")
    private String subjectArea;

    @Column(name = "project_description")
    private String projectDescription;

    private String objectives;

    @Type(type = "string-array")
    @Column(columnDefinition = "VARCHAR []")    //  Это определение колонки в бд
    private String[] technologies;

    private String stakeholders;

    @Column(name = "completion_date")
    private Date completionDate;

    @Column(name = "people_launch_date")
    private Date peopleLaunchDate;

    @Column(name = "has_gost")
    private Boolean gost;

    @Length(max = 150)
    @Column(name = "card_status")
    private String cardStatus;

    @Length(max = 150)
    @Column(name = "prev_card_status")
    private String prevCardStatus;

    @OneToOne(cascade = CascadeType.ALL)    //  Вместе с карточкой  сохраняются связанные сущности
    @JoinColumn(name = "project_type_id")
    private ProjectType projectType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne  // Пользователь может создать много карточек. К 1 пользователю много карточек
    @JoinColumn(name = "author_id")
    @NotNull    //  Поле не может быть пустым
    private User cardAuthor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "whp_id")
    private WorkingHoursPattern workingHoursPattern;

    public ProjectCard(String projectName, String projClientName, String projectStage,
                       String functionalDirection, String subjectArea, String projectDescription,
                       String objectives, String[] technologies, String stakeholders,
                       Date completionDate, Date peopleLaunchDate, Boolean gost,
                       String cardStatus, String prevCardStatus, ProjectType projectType,
                       Team team, Location location, User cardAuthor,
                       WorkingHoursPattern workingHoursPattern) {
        this.projectName = projectName;
        this.projClientName = projClientName;
        this.projectStage = projectStage;
        this.functionalDirection = functionalDirection;
        this.subjectArea = subjectArea;
        this.projectDescription = projectDescription;
        this.objectives = objectives;
        this.technologies = technologies;
        this.stakeholders = stakeholders;
        this.completionDate = completionDate;
        this.peopleLaunchDate = peopleLaunchDate;
        this.gost = gost;
        this.cardStatus = cardStatus;
        this.prevCardStatus = prevCardStatus;
        this.projectType = projectType;
        this.team = team;
        this.location = location;
        this.cardAuthor = cardAuthor;
        this.workingHoursPattern = workingHoursPattern;
    }
}
