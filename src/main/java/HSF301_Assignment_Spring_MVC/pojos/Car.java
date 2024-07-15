package HSF301_Assignment_Spring_MVC.pojos;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "Car name must be filled in")
    @Size(max = 100, message = "Car name should not exceed 100 characters")
    private String carName;

    @Column(name = "CarModelYear", nullable = false)
    @NotNull(message = "Car model year must be filled in")
    @Min(value = 1886, message = "Car model year must be no earlier than 1886")
    @Max(value = 2024, message = "Car model year must be no later than the current year")
    private Integer carModelYear;

    @Column(name = "Color", nullable = false)
    @NotBlank(message = "Color must be filled in")
    @Size(max = 50, message = "Color should not exceed 50 characters")
    private String color;

    @Column(name = "Capacity", nullable = false)
    @NotNull(message = "Capacity must be filled in")
    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;

    @Column(name = "Description")
    @Size(max = 1000, message = "Description should not exceed 1000 characters")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Import date must be filled in")
    @Column(name = "ImportDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date importDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ProducerID")
    private CarProducer producer;

    @Column(name = "RentPrice", nullable = false)
    @NotNull(message = "Rent price must be filled in")
    @Min(value = 0, message = "Rent price must be a positive value")
    private double rentPrice;

    
    @Column(name = "Status", nullable = false)
    @NotBlank(message = "Status must be filled in")
    @Pattern(regexp = "^(available|unavailable|Available|Unavailable)$", message = "Status must be 'available' or 'unavailable'")
    private String status;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CategoryID")
    private Category categoryID;

    @Column(name = "ImageLink")
    @Size(max = 255, message = "Image link should not exceed 255 characters")
    private String imgLink;

    @Column(name = "IsAvailable")
    private boolean isAvailable;

    @Column(name = "Transmission")
    private boolean transmission;

    @Column(name = "RatedStar")
    @Min(value = 0, message = "Rated star must be at least 0")
    @Max(value = 5, message = "Rated star must be no more than 5")
    private Integer ratedStar;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "car", orphanRemoval = true)
    private Set<CarRental> carRentalList = new HashSet<CarRental>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "car", orphanRemoval = true)
    private Set<Review> carReviewList = new HashSet<Review>();
}
