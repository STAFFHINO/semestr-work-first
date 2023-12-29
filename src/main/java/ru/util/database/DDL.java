package ru.util.database;

import java.sql.*;

public class DDL {
    private static final Connection connection = PostgresConnectionProvider.getConnection();

    //    public static final String URL = "jdbc:postgresql://localhost:5432/";
//    public static final String DRIVER = "org.postgresql.Driver";
//    public static final String USERNAME = "postgres";
//    public static final String PASSWORD = "admin";
//    private static void initializeDB() {
//        Connection conn = null;
//        Statement stmt = null;
//        try {
//            Class.forName(DRIVER);
//            System.out.println("Connecting to util.database...");
//            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            System.out.println("Creating util.database...");
//            stmt = conn.createStatement();
//            String sql = "CREATE DATABASE STUDENTS";
//            stmt.executeUpdate(sql);
//            System.out.println("Database created successfully...");
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try{
//                if(stmt!=null)
//                    stmt.close();
//            } catch (SQLException se2){
//            }// nothing we can do
//            try{
//                if(conn!=null)
//                    conn.close();
//            }catch(SQLException se){
//                se.printStackTrace();
//            }
//        }
//    }
    private static void addTablesToDB() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS product (id bigint generated always as identity constraint \"key_product\" primary key, name varchar, description varchar, price real, city varchar, category varchar, date_of_add date, id_of_author bigint)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            query = "create table if not exists product_image (id bigint generated always as identity constraint \"key_product_image\" primary key, product varchar, image varchar)";
            statement = connection.prepareStatement(query);
            statement.execute();
            query = "create table if not exists \"user\" (id bigint generated always as identity constraint \"key_user\" primary key, login varchar, password varchar, role varchar, nickname varchar)";
            statement = connection.prepareStatement(query);
            statement.execute();
            query = "create table if not exists user_basket (id bigint generated always as identity constraint \"key_user_basket\" primary key, id_of_user bigint, id_of_product bigint)";
            statement = connection.prepareStatement(query);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addTables() {
        addTablesToDB();
    }
}
