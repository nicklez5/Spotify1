package com.example.accessingdatamysql;

import java.lang.annotation.Inherited;

@Entity
public class Playlist{
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

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
    public void addSong(Song song1){
        this.playlist_songs.add(song1);
    }
    public void deleteSong(Song song1){
        this.playlist_songs.remove(song1);
    }
}