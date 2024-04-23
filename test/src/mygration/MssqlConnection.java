package mygration;

import java.sql.*;

public class MssqlConnection {
    public static void main(String[] args) {

        String oldDbUrl = "jdbc";
        String newDbUrl = "jdbc_new";
        String username = "park";
        String password = "park";

        String selectQuery = "SELECT * FROM old_table";
        String insertQuery = "INSERT INTO new_table SELECT * FROM old_table";
        String dropOldTableQuery = "DROP TABLE old_tables";

        try (
            Connection oldConnection = DriverManager.getConnection(oldDbUrl , username , password);
            Connection newConnection = DriverManager.getConnection(newDbUrl , username, password);
            Statement oldStatement = oldConnection.createStatement();
            Statement newStatement = newConnection.createStatement();
        ) {

            ResultSet resultSet = oldStatement.executeQuery(selectQuery);
            while (resultSet.next()) {
                newStatement.execute(insertQuery);
            }

            newStatement.execute(dropOldTableQuery);

            System.out.println("database success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}