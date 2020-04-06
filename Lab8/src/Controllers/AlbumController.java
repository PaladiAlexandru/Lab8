package Controllers;

import SingletonClass.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumController {
    //Inserts into database an album
    public void create(String name, int artistId,int releaseYear){
        String query = "INSERT INTO albums("
                + " name,"
                + " artist_id,"
                + " release_year"
                + ") VALUES ("
                + " ?, ?, ?)";
        try{
            DatabaseConnection dbconn = DatabaseConnection.getInstance();   //Creeam un obiect pentru a ne conecta la baza de date
            PreparedStatement pst = dbconn.getConnection().prepareStatement(query); //creeam un obiect PreparedStatement pentru a executa un Update
            pst.setString(1, name); // introducem parametrii lipsa
            pst.setInt(2, artistId);
            pst.setInt(3, releaseYear);
            pst.executeUpdate();// executam query-ul
            pst.close();
            dbconn.getConnection().close(); //închidem conexiunea la baza de date

        } catch (SQLException e){
            System.out.println("Couldn't connect to the database bcs: " + e);
        }
    }

    //Finds an artist by its id
    public void findByArtist(int id) {
        String query = "SELECT id FROM albums WHERE artist_id= ?";
        try {
            DatabaseConnection dbconn = DatabaseConnection.getInstance(); //Creeam un obiect pentru a ne conecta la baza de date
            PreparedStatement pst = dbconn.getConnection().prepareStatement(query); //creeam un obiect PreparedStatement pentru a executa un Query
            pst.setInt(1,id);
            pst.executeQuery();


            ResultSet rs = pst.executeQuery();      //executam query-ul si stocam rezultatul intr-un result set care are un pointer
                                                    // cu care putem parcurge toate liniile.

            boolean next= true;
            while(next = rs.next()){            //cat inca avem linii

                System.out.println("Album found: " + rs.getInt(1));

            }
            if(next){       //daca nu avem nici un album
                System.out.println("No album found!");
            }
            pst.close();
            dbconn.getConnection().close();     //inchidem conexiunea la baza de date

        } catch(SQLException e) {
            System.out.println("Couldn't connect to the database bcs: " + e);
        }
    }
}
