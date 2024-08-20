import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Operation {
    static Scanner scanner = new Scanner(System.in);

    public static void menu(int choice) {
        switch (choice) {
            case 1:
                viewCustomers(Main.connection);
                break;
            case 2:
                addCustomer(Main.connection);
                break;
            case 3:
                updateOrRemove(Main.connection);
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Please,enter a correct choice");
        }
    }

    private static ResultSet viewCustomers(Connection connection) {
        String sql = "SELECT * FROM customer";
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                System.out.println(id + ".Customer:");
                System.out.println("name:" + resultSet.getString("name"));
                System.out.println("surname:" + resultSet.getString("surname"));
                System.out.println("birthday:" + resultSet.getDate("birthday"));
                System.out.println("position:" + resultSet.getString("position"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    private static int addCustomer(Connection connection) {
        String sql = "INSERT INTO customer (name,surname,birthday,position)values(?,?,?,?)";
        fillCustomer(connection, sql);
        return 1;
    }

    private static int updateOrRemove(Connection connection) {
        System.out.println("Please,enter an id of customer who information you want to update or remove:");
        int id = scanner.nextInt();
        if (checkForExistence(connection, id)) {
            System.out.println("What you want to do?" +
                    "\nFor update enter 'u'" +
                    "\nFor delete enter 'd'");
            char answer = scanner.next().toLowerCase().charAt(0);
            switch (answer){
                case 'u':
                    String sql1 = "UPDATE customer SET name = ?,surname = ?,birthday = ?,position= ? where id =" + id;
                    fillCustomer(connection, sql1);
                    return 1;
                case 'd':
                    String sql2 = "DELETE FROM customer where id =" + id;
                    try (Statement statement = connection.createStatement()) {
                        statement.executeUpdate(sql2);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return 1;
            }

        }
        return 0;
    }

    private static int fillCustomer(Connection connection, String sql) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            System.out.println("Enter a name:");
            preparedStatement.setString(1, scanner.next());
            System.out.println("Enter a surname");
            preparedStatement.setString(2, scanner.next());
            System.out.print("Enter birthday (yyyy-MM-dd): ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(scanner.next(), formatter);
            Date sqlDate = Date.valueOf(localDate);
            preparedStatement.setDate(3, sqlDate);
            System.out.println("Enter a position:");
            scanner.nextLine();
            preparedStatement.setString(4, scanner.nextLine());//nextLine() islemir??
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    private static boolean checkForExistence(Connection connection, int id) {
        String checkSql = "SELECT id FROM customer where id=" + id;
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(checkSql)) {
                if (!resultSet.next()) {
                    System.out.println("Customer with " + id + " id does not exist");
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

}
