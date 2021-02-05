package JDBCDemo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DruidDemo {
    public static void main(String[] args){
        // 1.使用封装后的DruidUtils做更新
        try {
            DruidUtils.update("UPDATE jdbc_test SET testName=? WHERE id=?", new Object[]{"modified",29});
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            System.out.println("更新成功");
        }

        // 2.使用封装后的DruidUtils做查询
        List<Object> results = null;
        try {
            results = DruidUtils.select("SELECT testName, testAge FROM jdbc_test WHERE testAge>=?", new Object[]{100}, new ResultSetHandler() {
                @Override
                public Object handle(ResultSet rs) throws SQLException {
                    JDBCTest r = new JDBCTest();
                    r.setTestName(rs.getString(1));
                    r.setTestAge(rs.getInt(2));
                    return r;
                }
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            System.out.println("获取数据");
            for(Object j:results){
                JDBCTest t = (JDBCTest) j;
                t.print();
            }
        }
    }
}
