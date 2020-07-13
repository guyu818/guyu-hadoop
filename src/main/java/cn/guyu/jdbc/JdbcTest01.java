package cn.guyu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Des JDBC举例一 insert update delete
 * @Author guyu
 * @Date 2020/7/13 22:43
 * @Param
 * @Return
 */
public class JdbcTest01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 编写JDBC代码
        Class.forName("org.apache.hive.jdbc.HiveDriver");

        String url = "jdbc:hive2://192.168.244.201:10000/default"; //换成自己的
        String username = "root";
        String userpwd = "guyu"; //换成自己的
        Connection connection = DriverManager.getConnection(url, username, userpwd);

        String sql = "select * from t_orders";
        //sql 注入问题
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //提交数据库 执行sql
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("order_id"));
        }

        preparedStatement.close();
        connection.close();

    }
}
