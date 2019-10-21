package others;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo27 {


    public void test() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String userName = "root";
            String password = "root123";
            String url = "jdbc:mysql://localhost:3306/mytest";


            Connection connection = DriverManager.getConnection(userName,password,url);
            Statement statement = connection.createStatement();//返回一个运行环境
            ResultSet resultSet = statement.executeQuery("");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
