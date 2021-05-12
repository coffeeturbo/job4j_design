package integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OrdersStoreTest {
    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true;");

        pool.setUsername("usr");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/update_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @After
    public void setUpAfter() throws SQLException {
        pool.getConnection().prepareStatement("DROP SCHEMA PUBLIC CASCADE").execute();
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.builder().name("name1").description("description1").build());

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenFindById() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.builder().name("name1").description("description1222").build());

        Order order = store.findById(1);

        assertThat(order.getId(), is(1));
        assertThat(order.getDescription(), is("description1222"));
    }

    @Test
    public void whenUpdate() {
        OrdersStore store = new OrdersStore(pool);

        Order newOrder = Order.builder().name("name1").description("description1222").build();
        store.save(newOrder);

        Order order = store.findById(1);
        assertThat(order.getId(), is(1));
        assertThat(order.getDescription(), is("description1222"));

        order.setDescription("updated");
        store.save(order);
        assertThat(order.getDescription(), is("updated"));

    }

    @Test
    public void whenFindByName() {
        OrdersStore store = new OrdersStore(pool);

        Order newOrder = Order.builder().name("searchName").description("search by name").build();
        store.save(newOrder);

        Order order = store.findByName("searchName");
        assertThat(order.getId(), is(1));
        assertThat(order.getName(), is("searchName"));
        assertThat(order.getDescription(), is("search by name"));
    }

}