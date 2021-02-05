package JDBCDemo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 演示 使用statement在batch中插入不同的sql
 */
public class StatementBatchDemo {
    public static void main(String[] args){
        try {
             Connection c = JDBCUtils.getConnection(false);
             Statement s = c.createStatement();

            String sql1 = "INSERT INTO jdbc_test (testName, testAge) VALUES ('n1', 22)";
            String sql2 = "UPDATE jdbc_test SET testAge=33 WHERE id=1";

            // 添加
            s.addBatch(sql1);
            s.addBatch(sql2);
            // 执行
            s.executeBatch();
            // 清空
            s.clearBatch();

            JDBCUtils.release(c, s, null);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
