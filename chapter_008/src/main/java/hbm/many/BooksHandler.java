package hbm.many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BooksHandler {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();


            Author draiser = Author.builder().name("Драйзер").build();
            Author london = Author.builder().name("Лондон").build();
            Author remark = Author.builder().name("Ремарк").build();

            Book book1 = Book.builder().name("Сестра Керри").build();
            Book book2 = Book.builder().name("Три товарища").build();
            Book book3 = Book.builder().name("Мартин Иден").build();
            Book book4 = Book.builder().name("Финансист").build();
            Book book5 = Book.builder().name("Американская трагедия").build();

            session.save(book1);
            session.save(book2);
            session.save(book3);
            session.save(book4);
            session.save(book5);

            draiser.addBook(book1);
            draiser.addBook(book2);

            session.save(draiser);

            london.addBook(book3);
            london.addBook(book4);

            session.save(london);

            remark.addBook(book1);
            remark.addBook(book3);
            remark.addBook(book5);
            session.save(remark);

            session.remove(london);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
