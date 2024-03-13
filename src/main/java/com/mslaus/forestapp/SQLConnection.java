package com.mslaus.forestapp;

import com.mslaus.forestapp.entities.ShopItem;
import com.mslaus.forestapp.entities.Tag;
import com.mslaus.forestapp.entities.User;
import com.mslaus.forestapp.enums.Achievements;
import com.mslaus.forestapp.enums.ShopItems;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLConnection {

    Statement statement;
    ResultSet rs;
    String query;
    String result = "";
    Connection conn = connection();

    /*public static void main(String[] args) {
        createUserTable();
        createTimeEventsTable();
        createAchievementsTable();
        createTagsTable();
        createShopTable();
        createFriendsTable();
    }*/


    public static Connection connection(){
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+"Forest App","postgres","projectspassword");
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
    private static void createUserTable(){

        try{

            String query;
            Statement statement;
            Connection conn = connection();

            query ="CREATE TABLE users (id INT NOT NULL PRIMARY KEY , username VARCHAR(50) NOT NULL, password VARCHAR(200) NOT NULL, gold INT NOT NULL, total_minutes INT NOT NULL, total_trees INT NOT NULL)";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    protected User getUser(int id){

        User user = null;

        try {
            query = "SELECT * FROM users WHERE id = " + id;
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if(rs.next()){
                user = new User(id,
                        rs.getString("username"),
                        rs.getInt("total_minutes"),
                        rs.getInt("total_trees"),
                        rs.getInt("gold")
                );
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return user;

    }

    /** checks if the username is unique in the db, returns false if it is already in the db*/
    protected boolean uniqueUsername(String username){

        try {
            query = String.format("SELECT * FROM users WHERE username = '%s'", username);
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

    protected void insertUser(int id, String username, String password){

        try{
            query = String.format("INSERT INTO users (id, username, password, gold, total_minutes, total_trees) VALUES (%d, '%s', '%s', 100, 0, 0);", id, username, password);
            statement = conn.createStatement();
            statement.executeUpdate(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        insertAchievements(id);
        insertShopItem(id);

        System.out.println("User inserted");
        System.out.println("Achievements inserted");
        System.out.println("ShopItems inserted");
    }

    /**checks if the number account is in the database and returns true if the ID exists*/
    protected boolean checkID(int id){

        try {
            query = String.format("SELECT * FROM Users WHERE id = %d", id);
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
    protected boolean validate(String username, String password){

        String hashedPassword = getPassword(username);
        return BCrypt.checkpw(password, hashedPassword);

    }

    private String getPassword(String username) {

        try{
            query = String.format("SELECT password FROM users WHERE username = '%s'", username);
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

    protected String getUsername(int id){

        try{
            query = String.format("SELECT username FROM users WHERE id = %d", id);
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

    protected int getId(String username){

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

    protected int getGold(int id){

        try{
            query = String.format("SELECT gold FROM users WHERE id = %d", id);
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

    protected int getTotalMinutes(int id){

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

    protected int getTotalTrees(int id){

        try{
            query = String.format("SELECT total_trees FROM users WHERE id = %d", id);
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
    protected void updateTotalTrees(int id, int newNumber){

        try{
            query = String.format("UPDATE users SET total_trees = %d WHERE id = %d", newNumber, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("TotalTrees updated");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void updateUsername(int id, String username){

        try{
            query = String.format("UPDATE users SET username = '%s' WHERE id = %d", username, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Username updated");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void updatePassword(int id, String password){

        try{
            query = String.format("UPDATE Users SET password = '%s' WHERE id = %d", password, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Password updated");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void updateGold(int id, int gold){

        int current_gold = getGold(id);
        int newGold = current_gold + gold;
        try{
            query = String.format("UPDATE Users SET gold = %d WHERE id = %d", newGold, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Gold updated");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void updateTotalMinutes(int id, int time){

        int totalMinutes = getTotalMinutes(id);
        int newMinutes = totalMinutes + time;
        try{
            query = String.format("UPDATE Users SET total_minutes = %d WHERE id = %d", newMinutes, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("TotalMinutes updated");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void deleteUser(int id){

        try{
            query = String.format("DELETE FROM Users WHERE id = %d", id);
            statement = conn.createStatement();
            statement.executeQuery(query);
            System.out.println("User deleted.");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //everything about the "time_events" table

    /**creates the time events table in the database, it is used only once in the code*/
    private static void createTimeEventsTable(){

        String query;
        Statement statement;
        Connection conn = connection();

        try{
            query ="CREATE TABLE time_events (user_id INT NOT NULL, focused_time INT NOT NULL, starting_time VARCHAR(50) NOT NULL, finishing_time VARCHAR(50) NOT NULL, tag VARCHAR(50) NOT NULL, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id))";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void insertTimeEvent(int id, int focused_time, String startingTime, String finishingTime, String tag) {

        try {
            query = String.format("INSERT INTO time_events (user_id, focused_time, starting_time, finishing_time, tag) VALUES (%d, %d, '%s', '%s', '%s');", id, focused_time, startingTime, finishingTime, tag);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("TimeEvent inserted");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //everything about the "tag" table

    /**creates the tags table in the database, it is used only once in the code*/
    private static void createTagsTable(){

        String query;
        Statement statement;
        Connection conn = connection();
        try{
            query = "CREATE TABLE tags (user_id INT, name VARCHAR(50), color VARCHAR(50), FOREIGN KEY (user_id) REFERENCES users(id) )";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created.");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void insertTag(int id, String name, String color){

        try{
            query = String.format("INSERT INTO tags (user_id, name, color) VALUES (%d, '%s', '%s')", id, name, color);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Tag inserted.");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    //everything about the "achievements" table

    /**creates the achievements table in the database, it is used only once in the code*/
    private static void createAchievementsTable(){

        String query;
        Statement statement;
        Connection conn = connection();

        try{
            query = "CREATE TABLE achievements ( user_id INT, title VARCHAR(50), status VARCHAR(50), rewards VARCHAR(50), received VARCHAR(50), description VARCHAR(150), FOREIGN KEY (user_id) REFERENCES users(id))";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created.");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void insertAchievements(int id) {

        try{

            for(Achievements achievements: Achievements.values()){

                query = String.format("INSERT INTO achievements (user_id, title, status, rewards, received, description) VALUES (%d, '%s', '%s', %d, '%s', '%s')", id, String.valueOf(achievements).toUpperCase(), "locked", achievements.getReward(), "false", achievements.getDescription());
                statement = conn.createStatement();
                statement.executeUpdate(query);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void updateAchievement(int id, String achievement){

        try{
            query = String.format("UPDATE achievements SET status = '%s' WHERE user_id = %d AND title = '%s'", "unlocked", id, achievement);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Achievement updated");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    protected int getReward(int id, String achievement){

        try{
            query = String.format("SELECT rewards FROM achievements WHERE user_id = %d AND title = '%s'", id, achievement);
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
    protected boolean checkStatusAchievement(int id, String achievement){

        try{
            query = String.format("SELECT status FROM achievements WHERE user_id = %d AND title = '%s'", id, achievement);
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
    protected void giveReward(int id, String achievement){

        try{
            query = String.format("SELECT received FROM achievements WHERE user_id = %d, title = '%s' AND status = 'unlocked'", id, achievement);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()){
                result = rs.getString("received");
                if(result.equals("false")){
                    updateGold(id, getReward(id, achievement));
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Reward given.");
    }

    protected int achievementsUnlocked(int id){

        int result = 0;
        try{

            query = String.format("SELECT COUNT(*) FROM achievements WHERE user_id = %d AND status = 'unlocked'", id);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if(rs.next()){
                result = rs.getInt(1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    //everything about the "shop" table

    /**creates the shop table in the database, it is used only once in the code*/
    private static void createShopTable(){

        String query;
        Statement statement;
        Connection conn = connection();

        try{
            query = "CREATE TABLE shop ( user_id INT, item VARCHAR(50), status VARCHAR(50), cost INT, FOREIGN KEY (user_id) REFERENCES users(id))";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created.");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void insertShopItem(int id){

        try{
            for(ShopItems shopItems: ShopItems.values()){

                query = String.format("INSERT INTO shop (user_id, item, cost, status) VALUES (%d, '%s', %d, '%s')", id, shopItems, shopItems.getPrice(), "locked");
                statement = conn.createStatement();
                statement.executeUpdate(query);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void buyItem(int id, String item){

        try{
            query = String.format("UPDATE shop SET status = 'unlocked' WHERE user_id = %d AND item = '%s'", id, item);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated");

            try{
                String st = String.format("SELECT cost FROM shop WHERE user_id = %d AND item = '%s'", id, item);
                statement = conn.createStatement();
                rs = statement.executeQuery(st);
                if (rs.next()){
                    int result = rs.getInt("cost");
                    updateGold(id, -result);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Item bought.");

    }

    //everything about the "friendsconnection" table

    /**creates the friends connection table in the database, it is used only once in the code*/
    private static void createFriendsTable(){

        String query;
        Statement statement;
        Connection conn = connection();

        try{
            query = "CREATE TABLE friendsConnection ( first_id INT, second_id INT, FOREIGN KEY (first_id) REFERENCES users(id), FOREIGN KEY (second_id) REFERENCES users(id))";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created.");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void insertFriendship(int id1, int id2){

        try{
            query = String.format("INSERT INTO friendsconnection (first_id, second_id) VALUES (%d, %d)", id1, id2);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Friendship inserted.");

        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            query = String.format("INSERT INTO friendsconnection (first_id, second_id) VALUES (%d, %d)", id2, id1);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Friendship inserted.");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void checkAchievements(int id){

        int hours  = getTotalMinutes(id) / 60 ;

        if( hours > 5) {
            updateAchievement(id, "BEGINNER");
            giveReward(id, "BEGINNER");
        }
        if( hours > 10) {
            updateAchievement(id, "MIDDLE");
            giveReward(id, "MIDDLE");
        }
        if( hours > 25) {
            updateAchievement(id, "ADVANCED");
            giveReward(id, "ADVANCED");
        }
        if( hours > 40) {
            updateAchievement(id, "EXPERT");
            giveReward(id, "EXPERT");
        }

    }

    public List<Tag> listOfTags()throws SQLException {

        List list = new ArrayList<>();
        User user = new User();
        String query = "SELECT * FROM tags WHERE user_id = " + user.getId() ;
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            list.add(new Tag(
                    resultSet.getInt("user_id"),
                    resultSet.getString("name"),
                    resultSet.getString("color")
            ));
        }

        return list;
    }


    protected List<ShopItem> getPurchasedItems() throws SQLException{

        List list = new ArrayList<>();
        User user = new User();
        String query = "SELECT * FROM tags WHERE user_id = " + user.getId() ;
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            list.add(new Tag(
                    resultSet.getInt("user_id"),
                    resultSet.getString("name"),
                    resultSet.getString("color")
            ));
        }

        return list;

    }

    protected void removeTag(int id, String name){

        try{
            query = String.format("DELETE FROM tags WHERE user_id = %d AND name = '%s'", id, name);
            statement = conn.createStatement();
            statement.executeQuery(query);

        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Tag deleted.");
    }

}
