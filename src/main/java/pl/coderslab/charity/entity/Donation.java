package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "donation")
@Getter
@Setter
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private String street;
    private String city;
    private String phone;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "pick_up_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;
    @Column(name = "pick_up_time")
    private LocalTime pickUpTime;
    @Column(name = "pick_up_comment")
    private String pickUpComment;
    private boolean received = false;
    private LocalDateTime receivedTime;
    @Column(updatable = false)
    private LocalDateTime created;
    private LocalDateTime updated;

    @ManyToMany
    private List<Category> categories;
    @ManyToOne
    private Institution institution;
    @ManyToOne
    private User user;

    @PrePersist
    public void prePersis() {
        created = LocalDateTime.now();
        updated = created;
    }

    @PreUpdate
    public void preUpdate() {
        updated = LocalDateTime.now();
    }

}
