import java.sql.*;
import java.util.Properties;

public class SqlConnect {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/job4j";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "1234");
        props.setProperty("ssl", "false");

        try (Connection conn = DriverManager.getConnection(url, props)) {

            PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM car where name=?");
            st.setString(1, "bmw x3");
            try (ResultSet result = st.executeQuery()) {
                while (result.next()) {
                    String formated = String.format("name: %s id: %s",
                        result.getString("name"),
                        result.getString("id"));
                    System.out.println(formated);
                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
