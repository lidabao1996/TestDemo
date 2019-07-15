package jdbc;

import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;

public class PriceDataSave {


    public static void save(PreparedStatement pstm, Connection conn) {
        String sql = "insert into price_url(url,status) value(?,?)";
        try {
            pstm = conn.prepareStatement(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
