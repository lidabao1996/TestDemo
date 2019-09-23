package others;


import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 */
public class Demo19 {

    public void jdbcTest() throws Exception{
        //1:注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接，通过连接操作数据库，url:连接到mysqde地址书写
        String url = "jdbc:mysql://192.168.8.3:3306/drs";
        String username = "root";
        String password = "root";

        Connection connection = (Connection) DriverManager.getConnection(url,username,password);
        Statement statement = connection.createStatement();//返回一个运行环境
        ResultSet resultSet= statement.executeQuery("select user_name from user");
        //使用resultSet.next判断是否有下一个值，如果有就返回true
        while (resultSet.next()){


        }



    }


}
