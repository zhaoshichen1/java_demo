package JDBCDemo;

import java.io.IOException;
import java.sql.*;
import java.util.List;

/**
 * 演示JDBC的不同使用方式，基于数据表：
 * CREATE TABLE `jdbc_test` (
 *   `id` int(11) NOT NULL AUTO_INCREMENT,
 *   `testName` varchar(64) DEFAULT '',
 *   `testAge` int(11) DEFAULT '0',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1 COMMENT='jdbc测试'
 */
public class JDBCDemo {
    public static void main(String[] args){
        // 1.最简单模式,查询
        new Mysql().sqlDemo();

        // 2.使用封装后的JDBCUtils做更新
        try {
            JDBCUtils.update("UPDATE jdbc_test SET testName=? WHERE id=?", new Object[]{"modified",29});
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            System.out.println("更新成功");
        }

        // 3.使用封装后的JDBCUtils做查询
        List<Object> results = null;
        try {
            results = JDBCUtils.select("SELECT testName, testAge FROM jdbc_test WHERE testAge>=?", new Object[]{100}, new ResultSetHandler() {
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

// JaveBean样式的类，对应jdbc_test表的一行
class JDBCTest {
    private String testName;
    private int testAge;

    public JDBCTest(){

    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestAge(int testAge) {
        this.testAge = testAge;
    }

    public int getTestAge() {
        return testAge;
    }

    public void print(){
        System.out.println("Name="+this.testName+";Age="+this.testAge);
    }
}

class Mysql {
    public void sqlDemo(){
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            // 加载mysql的驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 获取链接
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest","root","j1k5p51!MRT!");

            s = c.createStatement();
            rs = s.executeQuery("SELECT * FROM tag");

            while(rs.next()){
                System.out.println(rs.getInt(1)+","+rs.getString(2));
            }

        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
}

