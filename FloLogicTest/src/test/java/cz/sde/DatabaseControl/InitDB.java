package cz.sde.DatabaseControl;

import java.sql.*;

import com.microsoft.sqlserver.jdbc.*;


public class InitDB {


//    private static String connectionString = AES.decryptMyString("eQhgqVFdSXBdNfLzHV0ltNb78LtxiuqS6Ty4Dqcq8w" +
//            "JluFqNjMUvVmdVh4ED3eptL3BWbj9kgdSlNbbn0VB0liJtVPBXw12AJP" +
//            "IADErydtm813MQ6XMGmYEfXZgXRTmu6a9ktNkO9ezKlOY6YHp2+huScVk" +
//            "49UdKfQ+6uke96RBMsZ87YJ/6Jz74ft7fE3uPLdPPcE0geYORiiFsgdbi" +
//            "+kTmB2nUVW+TUVF5QxHy+i8y436npNjudIBWFtEpw96LRDSmxeMsc73ldV9" +
//            "ERrp+2gCwAdkW4C4Xsj743LEXVUxeA5CRO1ZqkhEgSe0Rj90w");
    private static String connectionString = "jdbc:sqlserver://flologic.database.windows.net:1433;database=FloLogicTest;" +
        "user=FloLogicAdmin@flologic;password=*sdeSWdev9061700;encrypt=true;trustServerCertificate=false;hostNameInCerti" +
        "ficate=*.database.windows.net;loginTimeout=30;";


    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    private static PreparedStatement preparedInsertStatement = null;
    private static PreparedStatement preparedDeleteStatement = null;


    public static void insertDataBeforeTest() throws SQLException {


        try {
//            System.out.println(connectionString);
            System.out.println("Connecting to FloLogicDB...");
            connection = DriverManager.getConnection(connectionString);
            System.out.println("Connection to FloLogicDB established.");

            String insertSql = ScriptReader.ReadScript("SQLscripts/insert.sql");
            preparedInsertStatement = connection.prepareStatement(insertSql,
                    Statement.RETURN_GENERATED_KEYS);


            try {
                System.out.println("Executing insert script...");
                preparedInsertStatement.execute();
                connection.commit();

                System.out.println("Insert script executed...");

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

            if (preparedInsertStatement != null) {
                try {
                    preparedInsertStatement.close();
                }
                catch (Exception e) {

                }
            }
        }
    }


    // DELETE DATA AFTER TEST

    public static void deleteDataAfterTest() throws SQLException {

        try {
//            System.out.println(connectionString);
            System.out.println("Connecting to FloLogicDB...");
            connection = DriverManager.getConnection(connectionString);
            System.out.println("Connection to FloLogicDB established.");

            String deleteSql = ScriptReader.ReadScript("SQLscripts/delete.sql");
            preparedDeleteStatement = connection.prepareStatement(deleteSql,
                    Statement.RETURN_GENERATED_KEYS);


            try {
                System.out.println("Executing delete script...");
                preparedDeleteStatement.execute();
                connection.commit();

                System.out.println("Delete script executed...");

            }
            catch (SQLServerException ex) {
                System.err.println("Something wrong while executing delete script");
                ex.printStackTrace();
                connection.rollback();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the connections after the data has been handled.

            if (preparedDeleteStatement != null) {
                try {
                    preparedDeleteStatement.close();
                } catch (Exception e) {
                }
            }
        }
    }



    public static String getConnectionString() {
        return connectionString;
    }
}











