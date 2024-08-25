package com.example.accessingdatamysql.controller;
import com.example.accessingdatamysql.entity.*;
import com.example.accessingdatamysql.repository.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller	// This means that this class is a Controller
@RequestMapping(path="/users") // This means URL's start with /demo (after Application path)
public class UserController {
	@Autowired // This means to get the bean called userRepository
			   // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;
	private PlaylistRepository playlistRepository;
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String email,@RequestParam String password) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		User n = new User();
		n.setName(name);
		n.setEmail(email);
		n.setPassword(password);
		userRepository.save(n);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
	@DeleteMapping(path="/delete/user")
	public @ResponseBody String deleteUser(Integer pk1){
		String xyz = "";
		try{
			User user = userRepository.findById(pk1).get();
			userRepository.deleteById(pk1);
			xyz = "The user" + user.getName() + " has been deleted";

		}
		catch(Exception e){
			xyz = "The user could not be deleted";
		}
		return xyz;
	}
	
	@DeleteMapping(path="/delete")
	public @ResponseBody String deleteAllUsers(){
		userRepository.deleteAll();
		return "Deleted All Users";
	}
	@PutMapping(path="/update/name")
	public @ResponseBody String updateUsername(String name1,Integer pk1){
		String xyz = "";
		String original_name = "";
		try{
			User user = userRepository.findById(pk1).get();
			original_name = user.getName();
			user.setName(name1);
			userRepository.save(user);
			xyz = "The name " + original_name + " has been changed to" + name1;
		}catch(Exception e){
			xyz = "There was a problem updating name";
		}
		return xyz;
	} 
	@PutMapping(path="/update/email")
	public @ResponseBody String updateEmail(String name1,Integer pk1){
		String xyz = "";
		String original_email = "";
		try{
			User user = userRepository.findById(pk1).get();
			original_email = user.getName();
			user.setName(name1);
			userRepository.save(user);
			xyz = "The email " + original_email + " has been changed to" + name1;
		}catch(Exception e){
			xyz = "There was a problem updating email";
		}
		return xyz;
	}
	@PutMapping(path="/update/password")
	public @ResponseBody String updatePassword(String password1, Integer pk1){
		String xyz = "";
		try{
			User user = userRepository.findById(pk1).get();
			user.setPassword(password1);
			userRepository.save(user);
			xyz = "Password has been changed";
		}catch(Exception e){
			xyz = "There was a problem updating password";
		}

		return xyz;
	}
	@PutMapping(path="/update/add_song_to_library")
	public @ResponseBody String add_song_library(Song song1, Integer pk1){
		String xyz = "";
		try{
			User user = userRepository.findById(pk1).get();
			xyz = user.addSongLibrary(song1);
			userRepository.save(user);
		}catch(Exception e){
			xyz = "There was a problem adding song to library";
		}
		return xyz;
	} 
	@PutMapping(path="/update/remove_song_to_library")
	public @ResponseBody String remove_song_library(Song song1, Integer pk1){
		String xyz = "";
		try{
			User user = userRepository.findById(pk1).get();
			xyz = user.deleteSongLibrary(song1);
			userRepository.save(user);
		}catch(Exception e){
			xyz = "There was a problem removing song to library";
		}	
		return xyz;
	}
	// @PutMapping(path="/update/add_playlist")
	// public @ResponseBody String add_playlist(@RequestParam String name, Integer pk1){
	// 	try{

	// 	}
	// }
	@PutMapping(path="/update/add_song_to_playlist")
	public @ResponseBody String add_song_playlist(Song song1,Integer pk1, Integer pk2){
		
		String xyz = "";
		try{
			User user = userRepository.findById(pk1).get();
			Playlist playlist = playlistRepository.findById(pk2).get();
			xyz = user.add_song_playlist(song1, playlist);
			playlistRepository.save(playlist);
			userRepository.save(user);

		}catch(Exception e){
			xyz = "This song " + song1.getSongName() + " is corrupted";
		}
		return xyz;
	}
	@PutMapping(path="/update/delete_song_from_playlist")
	public @ResponseBody String delete_song_playlist(Song song1, Integer pk1, Integer pk2){
		
		String xyz = "";
		try{
			User user = userRepository.findById(pk1).get();
			Playlist playlist1 = playlistRepository.findById(pk2).get();
			xyz = user.delete_song_from_playlist(song1,playlist1);
			playlistRepository.save(playlist1);
			userRepository.save(user);

		}catch(Exception e){
			xyz = "This song " + song1.getSongName() + " is nowhere to be found"; 

		}
		return xyz;
	}
	@PutMapping(path="/update/delete_playlist")
	public @ResponseBody String delete_playlist(Integer pk1, Integer pk2){
		
		String xyz2 = "";
		try{
			User user = userRepository.findById(pk1).get();
			Playlist playlist1 = playlistRepository.findById(pk2).get();
			List<Playlist> xyz = user.getPlaylist();
			for(Integer i = 0 ; i < xyz.size() ; i++){
				Integer id = xyz.get(i).getId();
				if(id == playlist1.getId()){
					xyz.remove(playlist1);
					playlistRepository.deleteById(id);
					userRepository.save(user);
					xyz2 = "The playlist has been deleted";
					
				}
			}
		}catch(Exception e){
			xyz2 = "This playlist could not be deleted";
			
		}
		return xyz2;
	}
	
}
