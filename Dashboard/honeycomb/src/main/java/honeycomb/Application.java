package honeycomb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
    	String dbUrl = "jdbc:mysql://localhost:3306/honeycomb";
        String dbClass = "com.mysql.jdbc.Driver";
        String query = "Select distinct(table_name) from INFORMATION_SCHEMA.TABLES";
        String username = "root";
        String password = "";
        try {
            Class.forName(dbClass);
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            String tableName = resultSet.getString(1);
            System.out.println("Table name : " + tableName);
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

