package id.fabiworld.acaraku.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "activity_name")
    private String activityName;

    @Column(name = "start_time", nullable = false)
    @DateTimeFormat(pattern = "dd:MM:yyyy'T'HH:mm:ssss")
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    @DateTimeFormat(pattern = "dd:MM:yyyy'T'HH:mm:ssss")
    private LocalDateTime endTime;

    @Column(name = "created_date", nullable = false)
    @DateTimeFormat(pattern = "dd:MM:yyyy'T'HH:mm:ssss")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @DateTimeFormat(pattern = "dd:MM:yyyy'T'HH:mm:ssss")
    private LocalDateTime modifiedDate;

    @ManyToOne
    @JoinColumn(name = "agenda")
    private Agenda agenda;

}
