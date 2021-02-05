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
        // 新建一个配置文件对象
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



    /**
     * 用于一条更新的情况，由于列的类型不同所以用Object
     * @param sql
     * @param args 参数
     * @return 更新的行数
     */
    public static int update(String sql, Object[] args)  {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = getConnection();
            ps = c.prepareStatement(sql);
            for(int i=0;i<args.length;i++){
                ps.setObject(i+1, args[i]);
            }
            return ps.executeUpdate();
        } catch(SQLException e){
             e.printStackTrace();
             return 0;
        } finally{ // 释放资源，确保在发生异常时依然会释放资源
            release(c, ps, null);
        }
    }

    /**
     *
     * @param sql
     * @param args 参数
     * @param handler 处理ResultSet的逻辑，转换为一个JavaBean对象
     * @return 返回一个任意类型的数组，需要外接传入Handler将一行数据转化为一个对象
     */
    public static List<Object> select(String sql, Object[] args, ResultSetHandler handler) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Object> result = new ArrayList<>();
        try{
            c = getConnection();
            ps = c.prepareStatement(sql);
            for(int i=0;i<args.length;i++){
                ps.setObject(i+1, args[i]);
            }
            rs = ps.executeQuery();
            while(rs.next()){
                result.add(handler.handle(rs));
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally { // 确保会释放资源
            release(c, ps, rs);
        }
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
