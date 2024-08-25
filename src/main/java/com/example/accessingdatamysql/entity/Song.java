package com.example.accessingdatamysql;

import java.lang.annotation.Inherited;
import java.time.Duration;

@Entity
public class Song{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private Duration length;

    private String artist;

    private File file;

    public Integer getId(){
        return id;
    }
    public String getSongName(){
        return name;
    }
    public Duration getSongLength(){
        return length;
    }
    public String getSongArtist(){
        return artist;
    }
    public File getFile(){
        return file;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public void setSongName(String name){
        this.name = name;
    }
    public void setSongArtist(String name){
        this.artist = name;
    }
    public void setFile(File file1){
        this.file = file;
    }
}