package com.example.accessingdatamysql.entity;
import java.util.ArrayList;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.example.accessingdatamysql.entity.*;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String name;

	private String email;

	private String password;

	@OneToOne(targetEntity=Song.class)
	private List<Song> library = new ArrayList();

	@OneToOne(targetEntity=Playlist.class)
	private List<Playlist> playlist = new ArrayList();

	public List<Playlist> getPlaylist(){
		return playlist;
	}
	public String addNewPlaylist(Playlist playlist1){
		String xyz = "";
		try{
			this.playlist.add(playlist1);
			xyz = "Successfully added playlist";
		}catch(Exception e){
			xyz = "Could not have created a new playlist";
		}
		return xyz;
	}
	public String add_song_playlist(Song song1, Playlist playlist1){
		String xyz = "";
		List<Playlist> playlists = getPlaylist();
		try{
			for(Integer i = 0; i < playlists.size() ; i++){
				id = playlists.get(i).getId();
				if(id == playlist1.getId()){
					xyz = playlist1.addSong(song1);
					
				}
			}
		}catch(Exception e){
			xyz = "This song could not be added to playlist";
		}
		return xyz;
	}
	public String delete_song_from_playlist(Song song1,Playlist playlist1){
		List<Playlist> playlists = getPlaylist();
		String xyz = "";
		try{
			for(Integer i = 0; i < playlists.size(); i++){
				id = playlists.get(i).getId();
				if(id == playlist1.getId()){
					xyz = playlist1.deleteSong(song1);
				}
			}
		}catch(Exception e){
			xyz = "This song could not been removed from playlist";
		}
		return xyz;
		
	}
	public String make_new_playlist(Playlist playlist1){
		this.playlist.add(playlist1);
		String xyz2 = "This playlist " + playlist1.getName() + " has been added";
		return xyz2;
	}
	public String delete_playlist(Playlist playlist1){
		String xyz = "";
		try{
			this.playlist.remove(playlist1);
			xyz = "This playlist " + playlist1.getName() + " has been removed";
		}catch(Exception e){
			xyz = "This playlist " + playlist1.getName() + " is not found";
		}
		return xyz;
		
	}
	public String addSongLibrary(Song song1){
		String xyz = "";
		try{
			this.library.add(song1);
			xyz = "This song " + song1.getSongName() + " has been added";
		}catch(Exception e){
			xyz = "This song " + song1.getSongName() + " is corrupted";
		}
		return xyz;
	}
	public String deleteSongLibrary(Song song1){
		String xyz = "";
		try{
			this.library.remove(song1);
			xyz = "This song " + song1.getSongName() + " has been removed";
		}catch(Exception e){
			xyz = "This song " + song1.getSongName() + " is not found in library";
		}
		return xyz;
	}
	public List<Song> getLibrary(){
		return library;
	}
	public String getPassword(){
		String pw_hash = BCrypt.hashpw(this.password,BCrypt.gensalt());
		return pw_hash;
	}
	public void setPassword(String password2){
		this.password = password2;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
