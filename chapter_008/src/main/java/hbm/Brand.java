package hbm;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@Value
@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Model> models = new ArrayList<>();

    public Brand addModel(Model model) {
        this.models.add(model);
        return this;
    }
}
