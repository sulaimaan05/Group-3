package Repository;

import java.sql.Connection;

public class DBConfig {
    //Instance variable:
    private Connection con;

    //Constructor:
    public DBConfig(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Loads driver onto the heap.
            String url = "jdbc:mysql://localhost:3306/joke_server";

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
