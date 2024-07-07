package HSF301_Assignment_Spring_MVC.pojos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "CUSTOMERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"account"})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID",updatable = true)
    private int customerID;

    @Column(name = "CustomerName",updatable = true)
    private String customerName;

    @Column(name = "Mobile" , length = 10,updatable = true)
    private String mobile;

    @Column(name = "Birthdate",updatable = true)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "IdentityCard",updatable = true)
    private String identityCard;

    @Column(name = "LicenceNumber",updatable = true)
    private String licenceNumber;

    @Column(name = "LicenceDate",updatable = true)
    @Temporal(TemporalType.DATE)
    private Date licenceDate;

    @Column(name = "Email",updatable = true)
    private String email;

    @Column(name = "Password",updatable = true)
    private String password;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "AccountID")
    private Account account;
    
    
}
