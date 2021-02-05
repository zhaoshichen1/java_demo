package JDBCDemo;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * DruidUtils封装
 * 改为由Druid维护数据库连接池，其他操作和JDBC无区别
 */
public class DruidUtils {
    //    创建数据源对象
    private static DataSource dataSource;

    static {

//        新建一个配置文件对象
        Properties pro = new Properties();
        try {
            InputStream in = new FileInputStream("src/main/resources/db.properties");
            pro.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 从连接池中获取连接
     * */
    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

    // 用于一条更新的情况，由于列的类型不同所以用Object
    public static void update(String sql, Object[] args) throws SQLException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement(sql);
        for(int i=0;i<args.length;i++){
            ps.setObject(i+1, args[i]);
        }
        ps.executeUpdate();
        release(c, ps, null);
    }

    // 返回一个任意类型的数组，需要外接传入Handler将一行数据转化为一个对象
    public static List<Object> select(String sql, Object[] args, ResultSetHandler handler) throws SQLException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement(sql);
        for(int i=0;i<args.length;i++){
            ps.setObject(i+1, args[i]);
        }
        ResultSet rs = ps.executeQuery();
        List<Object> result = new ArrayList<>();
        while(rs.next()){
            result.add(handler.handle(rs));
        }
        release(c, ps, rs);
        return result;
    }

    /*
     * 关闭资源
     * */
    public static void release(Connection connection, Statement statement, ResultSet resultSet){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
