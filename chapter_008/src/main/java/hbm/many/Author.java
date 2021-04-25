package hbm.many;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Builder(toBuilder = true)
@Value
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<Book> books = new ArrayList<>();

    public Author addBook(Book book) {
        books.add(book);
        return this;
    }
}
