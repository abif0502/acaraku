package id.fabiworld.pestaku.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.fabiworld.pestaku.model.enumvalue.GeneralStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "style")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double discount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GeneralStatus status;

    @Column(name = "created_date", nullable = false)
    @DateTimeFormat(pattern = "dd:MM:yyyy'T'HH:mm:ssss")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @DateTimeFormat(pattern = "dd:MM:yyyy'T'HH:mm:ssss")
    private LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "style")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private Set<Transaction> transactions;

}
