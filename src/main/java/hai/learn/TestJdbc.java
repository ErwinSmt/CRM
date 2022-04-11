package hai.learn;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker";
        String user = "springstudent";
        String pass = "springstudent";

        try {

            Connection connection = DriverManager.getConnection(jdbcUrl,user,pass);
            System.out.println("Connected success !!!");


        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
