import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox?UseTimezone=true&serverTimezone=UTC";
        String user = "";
        String password = "";

        try {

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select sum(tab.num)/count(*) as avaragePurchase, course_name\n" +
                            "FROM (SELECT course_name, count(course_name) as num, date_format(subscription_date, '%Y-%m') as date_purchase " +
                            "FROM purchaselist group by course_name, date_format(subscription_date, '%Y-%m')) as tab group by course_name;");

            while (resultSet.next()){
                String avaragePurchase = resultSet.getString("avaragePurchase");
                String courseName = resultSet.getString("course_name");
                System.out.println(courseName + "   " + avaragePurchase);
            }

        }
        catch (Exception e){
            e.getMessage();
        }
    }
}

