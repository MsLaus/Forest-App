package com.mslaus.forestapp;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLConnection {


    public Connection connection(){
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+"Forest App","postgres","lenovo24");
            if(conn!=null){
                System.out.println("Connection Established");
            }else{
                System.out.println("Connection failed.");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    //everything about the "users" table

    /**creates the user table in the database, it is used only once in the code*/
    private void createUserTable(Connection conn, String table_name){
        Statement statement;
        try{
            String query ="CREATE TABLE "+ table_name+" (id INT, username VARCHAR(50), password VARCHAR(50), gold INT, total_minutes INT, total_trees INT )";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /** checks if the username is unique in the db, returns false if it is already in the db*/
    // TODO: 2/16/2024 add the function where i need to
    protected boolean uniqueUsername(Connection conn, String username){
        Statement statement;
        ResultSet rs;
        try {
            String query = String.format("SELECT * FROM users WHERE username = '%s'", username);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if(rs.next()){
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    protected void insertUser(Connection conn, int id, String username, String password){
        Statement statement;
        try{
            String query = String.format("INSERT INTO Users (id, username, password, gold, total_minutes, total_trees) VALUES (%d, '%s', '%s', 100, 0, 0);", id, username, password);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        insertAchievements(conn, id);
    }

    /**checks if the number account is in the database and returns true if the ID exists*/
    protected boolean checkID(Connection conn, int id){
        Statement statement;
        ResultSet rs;
        try {
            String query = String.format("SELECT * FROM Users WHERE id = %d", id);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if(rs.next()){
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }

    /**checks if the username and password exist in the database, return false if not*/
    protected boolean validate(Connection conn, String username, String password){

        String hashedPassword = getPassword(conn, username);
        boolean passwordMatch = BCrypt.checkpw(password, hashedPassword);
        return passwordMatch;

    }

    private String getPassword(Connection conn, String username) {
        Statement statement;
        ResultSet rs;
        String result = "";
        try{
            String query = String.format("SELECT password FROM users WHERE username = '%s'", username);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()){
                result = rs.getString("password");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    protected String getUsername(Connection conn, int id){
        Statement statement;
        ResultSet rs;
        String result = "";
        try{
            String query = String.format("SELECT username FROM Users WHERE id = %d", id);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()){
                result = rs.getString("username");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;

    }

    protected int getId(Connection conn, String username){
        Statement statement;
        ResultSet rs;
        String result = "";
        try{
            String query = String.format("SELECT id FROM users WHERE username = '%s' ", username);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()){
                result = rs.getString("id");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return Integer.parseInt(result);

    }

    protected int getGold(Connection conn, int id){
        Statement statement;
        ResultSet rs;
        String result = "";
        try{
            String query = String.format("SELECT gold FROM users WHERE id = %d", id);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()){
                result = rs.getString("gold");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return Integer.parseInt(result);

    }

    protected int getTotalMinutes(Connection conn, int id){
        Statement statement;
        ResultSet rs;
        String result = "";
        try{
            String query = String.format("SELECT total_minutes FROM users WHERE id = %d", id);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()){
                result = rs.getString("total_minutes");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return Integer.parseInt(result);

    }

    protected int getTotalTrees(Connection conn, int id){
        Statement statement;
        ResultSet rs;
        String result = "";
        try{
            String query = String.format("SELECT total_trees FROM users WHERE id = %d", id);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()){
                result = rs.getString("total_trees");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return Integer.parseInt(result);

    }
    protected void updateTotalTrees(Connection conn, int id, int newNumber){
        Statement statement;
        try{
            String query = String.format("UPDATE users SET total_trees = %d WHERE id = %d", newNumber, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void updateUsername(Connection conn, int id, String username){
        Statement statement;
        try{
            String query = String.format("UPDATE users SET username = '%s' WHERE id = %d", username, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void updatePassword(Connection conn, int id, String password){
        Statement statement;
        try{
            String query = String.format("UPDATE Users SET password = '%s' WHERE id = %d", password, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void updateGold(Connection conn, int id, int gold){
        Statement statement;
        int current_gold = getGold(conn, id);
        int newGold = current_gold + gold;
        try{
            String query = String.format("UPDATE Users SET gold = %d WHERE id = %d", newGold, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void updateTotalMinutes(Connection conn, int id, int time){
        Statement statement;
        int totalMinutes = getTotalMinutes(conn, id);
        int newMinutes = totalMinutes + time;
        try{
            String query = String.format("UPDATE Users SET total_minutes = %d WHERE id = %d", newMinutes, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void deleteUser(Connection conn, int id){
        Statement statement;
        try{
            String query = String.format("DELETE FROM Users WHERE id = %d", id);
            statement = conn.createStatement();
            statement.executeQuery(query);
            System.out.println("Row deleted.");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //everything about the "time_events" table

    /**creates the time events table in the database, it is used only once in the code*/
    private void createTimeEventsTable(Connection conn, String table_name){
        Statement statement;
        try{
            String query ="CREATE TABLE "+ table_name+" (id INT, focused_time INT, starting_time VARCHAR(50), finishing_time VARCHAR(50), tag VARCHAR(50) )";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void insertTimeEvent(Connection conn, int id, int focused_time, String startingTime, String finishingTime) {
        Statement statement;
        try {
            String query = String.format("INSERT INTO time_events (id, focused_time, starting_time, finishing_time) VALUES (%d, %d, '%s', '%s');", id, focused_time, startingTime, finishingTime);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //everything about the "tag" table

    /**creates the tags table in the database, it is used only once in the code*/
    private static void createTagsTable(Connection conn){
        Statement statement;
        try{
            String query = "CREATE TABLE tags (id INT, name VARCHAR(50), colour VARCHAR(50) )";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created.");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void insertTag(Connection conn, int id, String name, String colour){
        Statement statement;
        try{
            String query = String.format("INSERT INTO tags (id, name, colour) VALUES (%d, '%s', '%s')", id, name, colour);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Tag inserted.");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //everything about the "friendsconnection" table

    /**creates the friends connection table in the database, it is used only once in the code*/
    private void createFriendsTable(Connection conn){

        Statement statement;
        try{
            String query = "CREATE TABLE friendsConnection ( first_id INT, second_id INT)";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created.");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //everything about the "achievements" table

    /**creates the achievements table in the database, it is used only once in the code*/
    private void createAchievementsTable(Connection conn){
        Statement statement;
        try{
            String query = "CREATE TABLE achievements ( id INT, title VARCHAR(50), status VARCHAR(50), rewards VARCHAR(50), got VARCHAR(50))";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created.");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void insertAchievements(Connection conn, int id) {

        Statement statement;
        try{
            String query = String.format("INSERT INTO achievements (id, title, status, rewards, received, description) VALUES (%d, '%s', '%s', %d, '%s', '%s')", id, "Beginner", "locked", 100, "false", "Focus for five hours.");
            statement = conn.createStatement();
            statement.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            String query = String.format("INSERT INTO achievements (id, title, status, rewards, received, description) VALUES (%d, '%s', '%s', %d, '%s', '%s')", id, "Middle", "locked", 150, "false", "Focus for 10 hours.");
            statement = conn.createStatement();
            statement.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            String query = String.format("INSERT INTO achievements (id, title, status, rewards, received, description) VALUES (%d, '%s', '%s', %d, '%s', '%s')", id, "Advance", "locked", 300, "false", "Focus for 25 hours.");
            statement = conn.createStatement();
            statement.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            String query = String.format("INSERT INTO achievements (id, title, status, rewards, received, description) VALUES (%d, '%s', '%s', %d, '%s', '%s')", id, "Expert", "locked", 500, "false", "Focus for 40 hours.");
            statement = conn.createStatement();
            statement.executeUpdate(query);


        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Achievements inserted into database.");
    }

    protected void updateAchievement(Connection conn, int id, String achievement){

        Statement statement;
        try{
            String query = String.format("UPDATE achievements SET status = '%s' WHERE id = %d AND title = '%s'", "unlocked", id, achievement);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    protected int getReward(Connection conn, int id, String achievement){

        Statement statement;
        ResultSet rs;
        String result = "";
        try{
            String query = String.format("SELECT reward FROM achievements WHERE id = %d AND title = '%s'", id, achievement);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()){
                result = rs.getString("rewards");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return Integer.parseInt(result);

    }

    /**checks if the achievement is unlocked, return false if it is  locked*/
    protected boolean checkAchievement(Connection conn, int id, String achievement){

        Statement statement;
        ResultSet rs;
        String result;
        try{
            String query = String.format("SELECT status FROM achievements WHERE id = %d AND title = '%s'", id, achievement);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()){
                result = rs.getString("status");
                if(result.equals("unlocked")){
                    return true;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }

    /**checks if the reward was received. If the column's value is "false" the reward will be added to the current golds*/
    protected void giveReward(Connection conn, int id, String achievement){

        Statement statement;
        ResultSet rs;
        String result ;
        try{
            String query = String.format("SELECT received FROM achievements WHERE id = %d, title = '%s' AND status = 'unlocked'", id, achievement);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()){
                result = rs.getString("received");
                if(result.equals("false")){
                    updateGold(conn, id, getReward(conn, id, achievement));
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    protected int achievementsUnlocked(Connection conn, int id){

        Statement statement;
        ResultSet resultSet;
        int result = 0;
        try{

            String query = String.format("SELECT COUNT(*) FROM achievements WHERE id = %d AND status = 'unlocked'", id);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                result = resultSet.getInt(1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    //everything about the "shop" table

    /**creates the shop table in the database, it is used only once in the code*/
    private void createShopTable(Connection conn){
        Statement statement;
        try{
            String query = "CREATE TABLE shop ( id INT, item VARCHAR(50), status VARCHAR(50), cost INT)";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created.");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void insertFriendship(Connection conn, int id1, int id2){
        Statement statement;
        try{
            String query = String.format("INSERT INTO friendsconnection (first_id, second_id) VALUES (%d, %d)", id1, id2);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Friendship inserted.");

        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
