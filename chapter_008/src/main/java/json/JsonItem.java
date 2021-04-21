package json;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Map;

@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class JsonItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> data;

    public JsonItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {

        String result = "JsonItem{"
                + "id=" + id
                + ", data=";


        for (Object item : data.values()) {
            if (item != null) {
                System.out.println(item);
                result = result + item.toString();
            }

        }

        result = result + '}';

        return result;
    }
}
