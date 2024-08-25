package com.example.accessingdatamysql.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatamysql.entity.*;

public interface PlaylistRepository extends CrudRepository<Playlist, Integer> {

}
