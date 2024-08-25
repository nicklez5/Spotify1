package com.example.accessingdatamysql;

import com.example.accessingdatamysql.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String name;

	private String email;

	private String password;

	private List<Song> library = new ArrayList();

	private List<Playlist> playlist = new ArrayList();

	public List<Playlist> getPlaylist(){
		return playlist;
	}
	public void make_new_playlist(Playlist playlist1){
		this.playlist.add(playlist1);
	}
	public void delete_playlist(Playlist playlist1){
		this.playlist.remove(playlist1);
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
