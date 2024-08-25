package com.example.accessingdatamysql.entity;
import java.util.ArrayList;
import java.lang.annotation.Inherited;
import com.example.accessingdatamysql.entity.Song;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
@Entity
public class Playlist{
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToOne(targetEntity=Song.class)
    private List<Song> playlist_songs = new ArrayList();

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setPlaylistName(String name1){
        this.name = name1;
    }
    public String addSong(Song song1){
        String xyz = "";
        try{
            this.playlist_songs.add(song1);
            xyz = "This song " + song1.getSongName() + " has been added";
        }catch(Exception e){
            xyz = "This song " + song1.getSongName() + " is corrupted";
        }
        return xyz;
    }
    public String deleteSong(Song song1){
        String xyz = "";
        try{
            this.playlist_songs.remove(song1);
            xyz = "This song " + song1.getSongName() + " has been deleted";
        }catch(Exception e){
            xyz = "This song " + song1.getSongName() + "has not been found";
        }
        return xyz;
    }
}