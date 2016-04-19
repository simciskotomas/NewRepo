package cz.sde.DatabaseControl.Table;

import cz.sde.DatabaseControl.DBConnection;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.sun.corba.se.spi.orbutil.fsm.Guard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by tomas on 03/04/16.
 */
public class Valve {

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    private static PreparedStatement prepStatement = null;
    private static PreparedStatement preparedDeleteStatement = null;

    public static void deleteValve(String valveName) {

        String deleteString = "DELETE FROM Valve WHERE Name = '" + valveName + "'";

        try {
//
            System.out.println("Connecting to FloLogicDB...");
            connection = DBConnection.getDBConnection();
            System.out.println("Connection to FloLogicDB established.");


            prepStatement = connection.prepareStatement(deleteString,
                    Statement.RETURN_GENERATED_KEYS);


            try {
                System.out.println("Executing delete script...");
                prepStatement.executeUpdate();
                connection.commit();

                System.out.println("Delete script executed...");
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




}
