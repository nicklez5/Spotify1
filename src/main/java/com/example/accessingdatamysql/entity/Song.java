package com.example.accessingdatamysql.entity;

import java.io.File;
import java.lang.annotation.Inherited;
import jakarta.persistence.Entity;
import java.time.Duration;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    public String setFile(File file1){
        String xyz = "";
        try{
            this.file = file1;
            xyz = "This file " + file1.getName() + " has been set";
        }catch(Exception e){
            xyz = "This file " + file1.getName() + " is corrupted";
        }
        return xyz;
    }
}