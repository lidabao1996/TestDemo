package jdbc;

import com.mysql.jdbc.Connection;
import org.junit.Test;
import pachong.UrlUtils;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * jdbc使用
 */
public class JDBCDemo {
    @Test
    public void test() throws Exception {
        //注册驱动
        //DriverManager.registerDriver(new Driver());//存在问题2个，1需要依赖jar，因为需要在registerDriver(new Driver())2：注册两次
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接，通过连接操作数据库 url：连接到mysql的地址的书写

        //String url = "jdbc:mysql://localhost:3306/drs";
        String url = "jdbc:mysql://192.168.8.3:3306/drs";
        String user = "root";
        String password = "pro";

        Connection conn = (Connection) DriverManager.getConnection(url, user, password);

        //得到sql的运行环境，在这个环境中执行sql语句
        Statement statement = conn.createStatement();//返回一个运行环境

       /* ResultSet resultSet = statement.executeQuery("select su_name from drs_sys_user");

        //得到结果
        while (resultSet.next()){
            //使用resultSet.next()判断是否有下一个值，如果有就返回true
            String userName = resultSet.getString("su_name");//用户姓名
            System.out.println("userName = " + userName);
            //逢date,java.sql.Date;
        }*/

        PreparedStatement pstm = null;


        PriceDataSave.save(pstm,conn);

        /*List<String> list = UrlUtils.urls();
        String sql = "insert into price_url(url,status) value(?,?)";

        for (String urlstr : list) {
            //ResultSet resultSet = pstm.executeQuery("select count(*) from price_url where url = " + urlstr + "");
            //String dataUrl = resultSet.getString("url");

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, urlstr);
            pstm.setInt(2, 0);
            boolean row = pstm.execute();
            if (row) {
                System.out.println("添加成功！");
            }
        }*/


        //释放资源
        conn.close();
        statement.close();
        pstm.close();
    }


}
