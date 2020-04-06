package Controllers;

import SingletonClass.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ArtistController {
    //Inserts into database an artist
    public void create(String name, String country){
        String query = "INSERT INTO artists ("
                + " name,"
                + " country"
                + ") VALUES ("
                + " ?, ?)";
        try{
            DatabaseConnection dbconn = DatabaseConnection.getInstance(); //Creeam un obiect pentru a ne conecta la baza de date
            PreparedStatement pst = dbconn.getConnection().prepareStatement(query); //creeam un obiect PreparedStatement pentru a executa un Update
            pst.setString(1, name);// introducem parametrii lipsa
            pst.setString(2, country);

            pst.executeUpdate();// executam query-ul
            pst.close();
            dbconn.getConnection().close();//închidem conexiunea la baza de date

        } catch (SQLException e){
            System.out.println("Couldn't connect to the database bcs: " + e);
        }
    }

        // Finds a artist by name
    public void findByName(String name) {
        String query = "SELECT id FROM artists WHERE name = ?";
        try {
            DatabaseConnection dbconn = DatabaseConnection.getInstance(); //Creeam un obiect pentru a ne conecta la baza de date
            PreparedStatement pst = dbconn.getConnection().prepareStatement(query); //creeam un obiect PreparedStatement pentru a executa un Query
            pst.setString(1, name);
            pst.executeQuery();


            ResultSet rs = pst.executeQuery();   //executam query-ul si stocam rezultatul intr-un result set care are un pointer
                                                // cu care putem parcurge toate liniile.
            boolean next = true;

            while(next = rs.next()){             //cat inca avem linii
                System.out.println("Artist found: " + rs.getInt(1));
            }
            if(next){           //daca nu avem nici un artist
                System.out.println("No artist found!");
            }
            pst.close();
            dbconn.getConnection().close();  //inchidem conexiunea la baza de date


        } catch(SQLException e) {
            System.out.println(e);
        }
    }
}
