package sql.tracker;

import tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private Connection con;

    @Override
    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            con = DriverManager.getConnection(
                config.getProperty("url"),
                config.getProperty("username"),
                config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (con != null) {
            con.close();
        }
    }

    @Override
    public Item add(Item item) {
        this.init();
        try (PreparedStatement statement = con.prepareStatement("INSERT INTO item(name) VALUES(?)",
            Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.executeUpdate();
            this.close();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        this.init();
        try (PreparedStatement statement = con.prepareStatement("UPDATE item SET name = ? WHERE id = ?")) {
            statement.setString(1, item.getName());
            statement.setInt(2, Integer.parseInt(id));
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (!generatedKeys.next()) {
                    return false;
                }
            }
            this.close();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean delete(String id) {
        this.init();
        try (PreparedStatement statement = con.prepareStatement("DELETE FROM item WHERE id = ?")) {
            statement.setInt(1, Integer.parseInt(id));
            statement.executeQuery();
            this.close();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return true;
    }

    @Override
    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        this.init();
        try (PreparedStatement statement = con.prepareStatement("SELECT id, name FROM item")) {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                list.add(new Item(result.getString(1), result.getString(2)));
            }

            this.close();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        this.init();
        try (PreparedStatement statement = con.prepareStatement("SELECT id, name FROM item WHERE name = ?")) {
            statement.setString(1, key);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                list.add(new Item(result.getString(1), result.getString(2)));
            }

            this.close();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        this.init();
        try (PreparedStatement statement = con.prepareStatement("SELECT id, name FROM item WHERE id = ?")) {
            statement.setInt(1, Integer.parseInt(id));
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                item = new Item(result.getString(1), result.getString(2));
            }

            this.close();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return item;
    }

}
