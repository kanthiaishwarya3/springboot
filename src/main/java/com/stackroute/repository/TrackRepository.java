package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track,Integer>  // As it is mysql we use JpaRepo,mongo MongoRepo...based on databases we use different repositories
{
    // Passing query to retrieve track data
@Query(value = "select * from Track where name=?",nativeQuery = true)
List<Track> trackByName(String name);
}
