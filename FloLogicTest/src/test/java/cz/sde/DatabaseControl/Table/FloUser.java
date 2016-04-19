package cz.sde.DatabaseControl.Table;

import cz.sde.DatabaseControl.DBConnection;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.sun.corba.se.spi.orbutil.fsm.Guard;

import java.sql.*;


public class FloUser {
    int ID;
    int Active;
//    Date Created;
//    Date Modified;
//    String UUID
//    String Name;
//    String Password;
//    boolean IsAdmin;
//    String Email;


    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    private static PreparedStatement prepStatement = null;
    private static PreparedStatement preparedDeleteStatement = null;


    public static void setActivityState(int value, String name) {

        String updateString = "UPDATE FloUser SET Active = 1 WHERE Name = 'us01'";

        try {
//
            System.out.println("Connecting to FloLogicDB...");
            connection = DBConnection.getDBConnection();
            System.out.println("Connection to FloLogicDB established.");


            prepStatement = connection.prepareStatement(updateString,
                    Statement.RETURN_GENERATED_KEYS);


            try {
                System.out.println("Executing update script...");
                prepStatement.execute();
                connection.commit();

                System.out.println("Update script executed...");
            }
            catch (SQLServerException ex) {
                System.err.println("Something wrong while executing insert script");
                ex.printStackTrace();
                connection.rollback();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the connections after the data has been handled.

            if (prepStatement != null) {
                try {
                    prepStatement.close();
                }
                catch (Exception e) {

                }
            }
            if(connection != null) {
                try {
                    connection.close();
                }
                catch (Exception e){

                }
            }
        }
    }

    public static void SelectUsers() {

        try {
            connection = DBConnection.getDBConnection();
            statement = connection.createStatement();

            String sql = "SELECT ID, Active, Name, Email FROM FloUser";
            ResultSet results = statement.executeQuery(sql);

            while(results.next()) {
                int id = results.getInt("ID");
                int active = results.getInt("Active");
                String name = results.getString("Name");
                String email = results.getString("Email");

                System.out.print(" ID: " + id);
                System.out.print(", active: " + active);
                System.out.print(", Name: " + name);
                System.out.println(", Email: " + email);
            }
            results.close();

        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally {
            try{
                if(statement!=null)
                    connection.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(connection!=null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
