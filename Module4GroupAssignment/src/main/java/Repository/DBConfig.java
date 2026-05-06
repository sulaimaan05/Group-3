package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    //Instance variables:
    private static DBConfig instance;
    private Connection con;

    //Constructor:
    public DBConfig(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Loads driver onto the heap.
            String url = "jdbc:mysql://localhost:3306/joke_server"; //Define where the database is.
            this.con = DriverManager.getConnection(url, "root", "root"); //Create the actual connection to the database.
            System.out.println("Connected to database successfully");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Getter:
    public Connection getConnection(){
        return this.con;
    }

    //Global access point:
    public static DBConfig getInstance(){
        if(instance == null){
            instance = new DBConfig();
        }
        return instance;
    }

    //Close method:
    public void close() throws SQLException{
        if(this.con != null){
            this.con.close();
        }

    }
}
