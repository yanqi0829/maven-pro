package getconnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GetConnettion {
    public static void main(String[] args) throws Exception {
        getConnection1();
        getConnection2();
        getConnection3();
        getConnection4();
        getConnection5();
    }

    private static void getConnection1() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();    //mysql驱动 implements java.sql.Driver
        //jdbc:mysql  协议
        // localhost:3306 ip 端口
        //test   数据库
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root123");
        Connection connect = driver.connect(url, info);
        System.out.println(connect); //com.mysql.jdbc.JDBC4Connection@4b85612c
    }

    private static void getConnection2() throws Exception {
        //获取Driver实现类对象，使用反射
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root123");
        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }

    /**
     * 使用 DriverManager替换Driver
     *
     * @throws Exception
     */
    private static void getConnection3() throws Exception {
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "root123";
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();
        //注册驱动
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    //不用显示注册驱动
    private static void getConnection4() throws Exception {
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "root123";
        Class.forName("com.mysql.jdbc.Driver");
        //com.mysql.jdbc.Driver 中代码如下，省略注册操作
       /* static {
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            } catch (SQLException var1) {
                throw new RuntimeException("Can't register driver!");
            }
        }*/
//        Driver driver =(Driver) aClass.newInstance();
//        //注册驱动
//        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    //最终版
    private static void getConnection5() throws Exception {
        InputStream is = Object.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driverClass = properties.getProperty("driverClass");

        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
}
