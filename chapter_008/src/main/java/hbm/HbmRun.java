package hbm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Model model1 = Model.builder().name("Q1").build();
            Model model2 = Model.builder().name("Q2").build();
            Model model3 = Model.builder().name("Q3").build();

            session.save(model1);
            session.save(model2);
            session.save(model3);
            Brand brand = Brand.builder().name("Audi").build();

            brand.addModel(session.load(Model.class, model1.getId()))
                    .addModel(session.load(Model.class, model2.getId()))
                    .addModel(session.load(Model.class, model3.getId()));

            session.save(brand);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
