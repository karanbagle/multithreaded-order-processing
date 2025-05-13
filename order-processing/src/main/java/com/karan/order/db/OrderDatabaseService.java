package com.karan.order.db;
import com.karan.order.config.AppConfig;
import com.karan.order.model.Order;

import  java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;

public class OrderDatabaseService {
    private static Connection connection;

    static{
        try{
            String url = AppConfig.get("db.url");
            String user = AppConfig.get("db.username");
            String password = AppConfig.get("db.password");

            connection = DriverManager.getConnection(url, user, password);
            createTableIfNotExists();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void createTableIfNotExists() throws SQLException{
        String createSQL = "CREATE TABLE IF NOT EXISTS `orders` ("+
                "id INT PRIMARY KEY, "+
                "item VARCHAR(255))";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(createSQL);
    }
    public synchronized void saveOrder(Order order) {
        String insertSQL = "INSERT INTO orders (id, item) VALUES (?,?)";
        try(PreparedStatement pstmt = connection.prepareStatement(insertSQL)){
            pstmt.setInt(1,order.getOrderId());
            pstmt.setString(2,order.getItem());
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void printAllOrders() {
        String query = "SELECT * FROM orders";
        try(Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query)){
            System.out.println("Stored Orders: ");
            while(rs.next()){
                System.out.println("Order Id: "+rs.getInt("id")+", Item: "+rs.getString("item"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void closeConnection(){
        try{
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
