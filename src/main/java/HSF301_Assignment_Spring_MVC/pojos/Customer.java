package HSF301_Assignment_Spring_MVC.pojos;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CUSTOMERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"account","carRentalList","carReviewList"})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID",updatable = true)
    private int customerID;

    @Column(name = "CustomerName",updatable = true)
    @NotBlank(message = "Your name have to be filled in")
    private String customerName;

    @Column(name = "Mobile" , length = 10,updatable = true)
    @Pattern(regexp = "^\\+84\\d{9}$|^0\\d{9}$", message = "Invalid phone number.")
    private String mobile;

    @Column(name = "Birthdate",updatable = true)
    @Temporal(TemporalType.DATE)
    @Past(message = "Birthday must be in the past")
    private Date birthday;

    @Column(name = "IdentityCard",updatable = true)
//    @Size(min = 9, max = 12, message = "Identity card number must be between 9 and 12 characters")
    private String identityCard;

    @Column(name = "LicenceNumber",updatable = true)
//    @NotBlank(message = "Licence number must be filled in")
    private String licenceNumber;

    @Column(name = "LicenceDate",updatable = true)
//    @PastOrPresent(message = "Licence date must be in the past or present")
    @Temporal(TemporalType.DATE)
    private Date licenceDate;

    @Column(name = "Email",updatable = true)
    @NotBlank(message = "Email must be filled in")
    private String email;

    @Column(name = "Password",updatable = true)
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "AccountID")
    private Account account;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", orphanRemoval = true)
    private Set<CarRental> carRentalList = new HashSet<CarRental>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", orphanRemoval = true)
    private Set<Review> carReviewList = new HashSet<Review>();
}
