package HSF301_Assignment_Spring_MVC.pojos;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CARS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"producer","carRentalList","carReviewList"})
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarID")
    private int carId;

    @Column(name = "CarName", nullable = false)
    private String carName;

    @Column(name = "CarModelYear", nullable = false)
    private Integer carModelYear;

    @Column(name = "Color", nullable = false)
    private String color;

    @Column(name = "Capacity", nullable = false)
    private Integer capacity;

    @Column(name = "Description")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ImportDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date importDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ProducerID")
    private CarProducer producer;

    @Column(name = "RentPrice", nullable = false)
    private double rentPrice;

    
    @Column(name = "Status", nullable = false)
    private String status;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CategoryID")
    private Category categoryID;

    @Column(name = "ImageLink")
    private String imgLink;

    @Column(name = "IsAvailable")
    private boolean isAvailable;

    @Column(name = "Transmission")
    private boolean transmission;

    @Column(name = "RatedStar")
    private Integer ratedStar;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "car", orphanRemoval = true)
    private Set<CarRental> carRentalList = new HashSet<CarRental>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "car", orphanRemoval = true)
    private Set<Review> carReviewList = new HashSet<Review>();
}
