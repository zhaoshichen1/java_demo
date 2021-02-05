package JDBCDemo;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDemo {
    public static void main(String[] args){
        Connection c = null;
        try {
            c = JDBCUtils.getConnection(true);
            PreparedStatement ps = c.prepareStatement("INSERT INTO jdbc_test (testName, testAge) VALUES (?, ?)");

            ps.setString(1, "T1");
            ps.setInt(2, 111);
            ps.executeUpdate();

            ps.setString(1, "T2");
            ps.setInt(2,222);
            ps.executeUpdate();
            c.commit();
        } catch (SQLException e){
            e.printStackTrace();
            try {
                c.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
