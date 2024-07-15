package HSF301_Assignment_Spring_MVC.pojos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CAR_PRODUCERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"car"})
public class CarProducer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProducerID")
    private int producerId;

    @Column(name = "ProducerName", nullable = false)
    @NotBlank(message = "Producer name must be filled in")
    @Size(max = 100, message = "Producer name should not exceed 100 characters")
    private String producerName;

    @Column(name = "Address", nullable = false)
    @NotBlank(message = "Address must be filled in")
    @Size(max = 200, message = "Address should not exceed 200 characters")
    private String address;

    @Column(name = "Country", nullable = false)
    @NotBlank(message = "Country must be filled in")
    @Size(max = 100, message = "Country should not exceed 100 characters")
    private String country;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ProducerID")
    private Set<Car> car = new HashSet<Car>();

}
