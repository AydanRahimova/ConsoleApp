import java.sql.*;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Connection connection;

    static {
//        String url = System.getenv("DB_URL");
//        String user = System.getenv("DB_USERNAME");
//        String password = System.getenv("DB_[ASSWORD");
        String url = "jdbc:postgresql://localhost:5432/matrix161";
        String user = "postgres";
        String password = "2810";
        try {
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS customer" +
                    "(id SERIAL PRIMARY KEY,name VARCHAR(30),surname VARCHAR(30),birthday DATE,position VARCHAR(30))";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("Please enter an operation:" +
                    "\n1-for viewing customers" +
                    "\n2-for adding customer" +
                    "\n3-for removing a customer" +
                    "\n4-for exit");
            int choice = scanner.nextInt();
            Operation.menu(choice);
        }
    }
}
