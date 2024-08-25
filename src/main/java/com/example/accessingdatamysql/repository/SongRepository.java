package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatamysql.entity.*;

public interface SongRepository extends CrudRepository<Song, Integer>{
    
}
