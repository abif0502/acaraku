package id.fabiworld.acaraku.model;

import id.fabiworld.acaraku.model.enumvalue.GeneralStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "picture")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private byte[] file;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GeneralStatus status;

    @ManyToOne
    @JoinColumn(name = "agenda")
    private Agenda agenda;
}
