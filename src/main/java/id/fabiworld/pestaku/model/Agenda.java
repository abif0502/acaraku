package id.fabiworld.pestaku.model;

import id.fabiworld.pestaku.model.enumvalue.AgendaStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "agenda")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Agenda {

    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String description;

    @Column(nullable = false)
    private String subUrl;

    @Column(name = "start_date", nullable = false)
    @DateTimeFormat(pattern = "dd:MM:yyyy'T'HH:mm:ssss")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "dd:MM:yyyy'T'HH:mm:ssss")
    private LocalDateTime endDate;

    @Column(nullable = false)
    private String address;

    private String coordinate;

    @Column(nullable = false)
    private AgendaStatus status;

    @ManyToOne
    @JoinColumn(name = "type")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "bgm")
    private Bgm bgm;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private Transaction transaction;

    @OneToMany(mappedBy = "agenda")
    @ToString.Exclude
    private Set<Comment> comments;

    @OneToMany(mappedBy = "agenda")
    @ToString.Exclude
    private Set<Picture> pictures;

}
