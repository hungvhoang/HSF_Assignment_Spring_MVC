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
    private String producerName;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "Country", nullable = false)
    private String country;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ProducerID")
    private Set<Car> car = new HashSet<Car>();

}
