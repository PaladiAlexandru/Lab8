package Main;

import Controllers.AlbumController;
import Controllers.ArtistController;


public class Main {
    public static void main(String[] args){
        ArtistController ac = new ArtistController();
        ac.create("Ionut","Italia");
        ac.findByName("Ionut");

        AlbumController ab = new AlbumController();
        ab.create("secondAlbum",30,2020);
        ab.findByArtist(31);
    }
}
