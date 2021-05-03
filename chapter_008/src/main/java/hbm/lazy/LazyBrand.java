package hbm.lazy;


import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Value
@Entity
@Table(name = "lazy_brand")
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString(doNotUseGetters = true)
public class LazyBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id = null;

    String name = null;

    @OneToMany(mappedBy = "lazyBrand")
    List<LazyCar> lazyCars = new ArrayList<>();
}
