package JDBCDemo;

import java.sql.*;

/**
 * 演示使用PrepareStatement批量执行插入语句
 * 并且演示如何通过getGeneratedKeys获取刚刚插入的主键id（用于关联表的插入）
 */
public class PrepareStatementBatchDemo {
    public static void main(String[] args){
        try {
            Connection c = JDBCUtils.getConnection(false);

            // 需要获取主键时，需要声明
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO jdbc_test (testName, testAge) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            int[] ages = new int[]{1,2,3,4,5,66};
            String[] names = new String[]{"n1","n2","n3","n4","n5","n66"};

            // 添加
            for(int i=0;i<ages.length;i++){
                ps.setString(1, names[i]);
                ps.setInt(2, ages[i]);
                ps.addBatch();
            }
            ps.executeBatch();     // 执行
            ps.clearBatch();       // 清空

            // 获取刚刚插入的主键
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()){
                System.out.println("Get Inserted Keys "+rs.getInt(1));
            }

            // ps 继承自 statement，所以可以放入
            JDBCUtils.release(c, ps, rs);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
