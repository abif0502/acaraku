package id.fabiworld.acaraku.model;

import id.fabiworld.acaraku.model.enumvalue.GeneralStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Column(length = 30)
    private String email;

    @Column(length = 30)
    private String instagram;

    @Column(length = 30)
    private String facebook;

    @Column(length = 30)
    private String youtube;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GeneralStatus status;

    @Column(name = "created_date", nullable = false)
    @DateTimeFormat(pattern = "dd:MM:yyyy'T'HH:mm:ssss")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @DateTimeFormat(pattern = "dd:MM:yyyy'T'HH:mm:ssss")
    private LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "client")
    @ToString.Exclude
    private Set<Transaction> transactions;
}
