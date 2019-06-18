package jdbc;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * jdbc使用
 */
public class JDBCDemo {
    public void test() throws Exception{
        //注册驱动
        //DriverManager.registerDriver(new Driver());//存在问题2个，1需要依赖jar，因为需要在registerDriver(new Driver())2：注册两次
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接，通过连接操作数据库 url：连接到mysql的地址的书写

        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "root123";

        Connection conn = (Connection) DriverManager.getConnection(url,user,password);

        //得到sql的运行环境，在这个环境中执行sql语句
        Statement statement = conn.createStatement();//返回一个运行环境

        ResultSet resultSet = statement.executeQuery("select * from user");

        //得到结果
        while (resultSet.next()){
            //使用resultSet.next()判断是否有下一个值，如果有就返回true
            String userCode = resultSet.getString("userCode");//用户编号
            String userName = resultSet.getString("userName");//用户姓名
            System.out.println("userName = " + userName);
            //逢date,java.sql.Date;
        }
        //释放资源
        conn.close();
        statement.close();
    }
}
