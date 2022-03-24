package id.fabiworld.acaraku.model;

import id.fabiworld.acaraku.model.enumvalue.AgendaStatus;
import lombok.*;

import javax.persistence.*;
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

    @Column(name = "sub_url")
    private String subUrl;

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

    @OneToMany(mappedBy = "agenda")
    @ToString.Exclude
    private Set<Schedule> schedules;

}
