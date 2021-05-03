package hbm.lazy;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;

import javax.persistence.*;

@Value
@Entity
@Table(name = "lazy_car")
@NoArgsConstructor
@ToString()
@Builder(toBuilder = true)
public class LazyCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id = null;

    String description = null;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "lazy_brand_id")
    LazyBrand lazyBrand = null;
}
