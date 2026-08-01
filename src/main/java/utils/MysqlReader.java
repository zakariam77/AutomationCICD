package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlReader {

private static final Logger logger = LogManager.getLogger(MysqlReader.class);

    public static List<Object[]> getDataSql(){

        String DB_password = System.getenv("DB_PASSWORD");

        //!= null ? System.getenv("DB_PASSWORD") : ConfigReader.getProperty("db_pass");


        String DB_user = System.getenv("DB_USERNAME");
        String DB_url = System.getenv("DB_URL");
        String DB_name = "testdb";

        List<Object[]> dataList = new ArrayList<>();

        try{
            logger.info("Fetching test credentials from DB testdb");
            Connection connection = DriverManager.getConnection(DB_url+ "/" +DB_name, DB_user, DB_password);
            Statement statement = connection.createStatement();
            ResultSet rs =  statement.executeQuery("select username, userpass from testingdata");
            while(rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("userpass");
                dataList.add(new Object[] {username, password});
            }
        }catch (SQLException e){
            logger.fatal( "Mysql Error: {}", e.getMessage() );
            throw new RuntimeException(e.getMessage());
        }

        return dataList;

    }
}
