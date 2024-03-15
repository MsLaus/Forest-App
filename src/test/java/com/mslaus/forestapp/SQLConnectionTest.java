package com.mslaus.forestapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class SQLConnectionTest {

    private Connection conn;
    int id = 5206449;
    String username = "MsLaus";
    Statement statement;
    ResultSet rs;
    String query;


    @BeforeEach
    void setUp() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+"Forest App","postgres","projectspassword");
            if(conn!=null){
                System.out.println("Connection Established");
            }else{
                System.out.println("Connection failed.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @AfterEach
    void closeResources() throws SQLException{
        conn.close();
    }

    @Test
    void connection(){
        assertNotNull(conn);
    }

    @Test
    void getUsername() throws SQLException{

        query = "SELECT * FROM users WHERE id = " + id;
        statement = conn.createStatement();
        rs = statement.executeQuery(query);

        if(rs.next()) assertEquals(username, rs.getString("username"));
    }

    @Test
    void getId() throws SQLException{

        query = "SELECT * FROM users WHERE username = 'MsLaus'";
        statement = conn.createStatement();
        rs = statement.executeQuery(query);

        if(rs.next()) assertEquals(id, rs.getInt("id"));
    }

    @Test
    void getGold() throws SQLException{

        query = "SELECT * FROM users WHERE username = 'MsLaus'";
        statement = conn.createStatement();
        rs = statement.executeQuery(query);

        if(rs.next()) assertEquals(100, rs.getInt("gold"));
    }

    @Test
    void getTotalMinutes() throws SQLException{

        query = "SELECT * FROM users WHERE username = 'MsLaus'";
        statement = conn.createStatement();
        rs = statement.executeQuery(query);

        if(rs.next()) assertEquals(8, rs.getInt("total_minutes"));
    }

    @Test
    void getTotalTrees() throws SQLException{

        query = "SELECT * FROM users WHERE username = 'MsLaus'";
        statement = conn.createStatement();
        rs = statement.executeQuery(query);

        if(rs.next()) assertEquals(0, rs.getInt("total_trees"));
    }

}