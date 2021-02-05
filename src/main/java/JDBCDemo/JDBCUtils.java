package JDBCDemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCUtils {

    private static String driver = "";
    private static String schema = "";
    private static String username = "";
    private static String password = "";

    // 静态代码块
    static {
    //获取配置文件的读入流
        Properties pro = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("src/main/resources/db.properties");
            pro.load(in);
            in.close();

            driver = pro.getProperty("driver");
            schema = pro.getProperty("schema");
            username = pro.getProperty("username");
            password = pro.getProperty("password");

            // 加载驱动
            Class.forName(driver);
        }
        catch(IOException|ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection(Boolean useTransaction) throws SQLException {
        Connection r =  DriverManager.getConnection(schema,username, password);

        // 如果使用事务，则关闭自动提交
        if (useTransaction){
            r.setAutoCommit(false);
        }
        return r ;
    }

    public static void release(Connection c, Statement s, ResultSet rs){
        try {
            if (c != null) {
                c.close();
            }
            if (s != null) {
                s.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch( SQLException e){
            e.printStackTrace();
        }
    }

    // 用于一条更新的情况，由于列的类型不同所以用Object
    public static void update(String sql, Object[] args) throws SQLException {
        Connection c = getConnection(false);
        PreparedStatement ps = c.prepareStatement(sql);
        for(int i=0;i<args.length;i++){
            ps.setObject(i+1, args[i]);
        }
        ps.executeUpdate();
        release(c, ps, null);
    }

    // 返回一个任意类型的数组，需要外接传入Handler将一行数据转化为一个对象
    public static List<Object> select(String sql, Object[] args, ResultSetHandler handler) throws SQLException {
        Connection c = getConnection(false);
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
}

interface ResultSetHandler{
    public Object handle(ResultSet rs) throws SQLException;
}
