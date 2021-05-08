package hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Candidate one = Candidate.builder().name("Alex").experience(21).salary(1).build();
            Candidate two = Candidate.builder().name("Nikolay").experience(28).salary(2).build();
            Candidate three = Candidate.builder().name("Nikita").experience(25).salary(3).build();

            session.save(one);
            session.save(two);
            session.save(three);

            // SELECT
            Query query = session.createQuery("from Candidate c");
            query.list().forEach(System.out::println);

            // SELECT Where id
            Query queryWhereId = session.createQuery("from Candidate c where c.id = :id");
            queryWhereId.setParameter("id", one.getId());
            System.out.println("Select candidate where id = " + one.getId());
            System.out.println(queryWhereId.uniqueResult());

            // SELECT Where name
            Query queryWhereName = session.createQuery("from Candidate c where c.name=:name");
            queryWhereName.setParameter("name", "Nikolay");
            System.out.println("Select candidate where name = Nikolay");
            queryWhereName.list().forEach(System.out::println);

            // UPDATE Where id = 3
            Query querySalary = session.createQuery(
                    "update Candidate c set c.salary=:salary where c.id=:id");
            querySalary.setParameter("id", one.getId());
            querySalary.setParameter("salary", 100);
            querySalary.executeUpdate();

            // DELETE Where id = 2
            Query queryDelete = session.createQuery(
                    "delete Candidate c where c.id=:id");
            queryDelete.setParameter("id", three.getId());
            queryDelete.executeUpdate();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
