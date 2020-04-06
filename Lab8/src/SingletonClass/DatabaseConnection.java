package SingletonClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class DatabaseConnection {
    private static DatabaseConnection single_instance = null;  // unica instanta acestei clase
    private Connection connection;          //conexiunea catrea baza de date


    private DatabaseConnection() {
        String password = "sql";            //parola pentru baza de date
        String username = "dba";            //usernamul pentru baza de date
        String url = "jdbc:mysql://localhost:3306/musicalbums";     //url-ul pentru baza de date
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.connection = DriverManager.getConnection(url, username, password);  // ne conectam la baza de date
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    } //un getter

    public static DatabaseConnection getInstance()throws SQLException{  //asa creeam noi obiecte de tipul DatabaseConnection
        if(single_instance == null) {       // daca este null, creem un nou obiect
            single_instance = new DatabaseConnection();
        } else if(single_instance.getConnection().isClosed()){ // daca este inchis, creeam alt obiect
            single_instance = new DatabaseConnection();
        }
        return single_instance;                                 // daca nu este null si nici inchis, retureaza acelasi obiect
    }

}
