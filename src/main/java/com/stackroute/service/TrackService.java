package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;
// Methods to implement CRUD.
public interface TrackService
{
    public Track saveUser(Track track) throws TrackAlreadyExistsException;
    public List<Track>getAllUsers();
    public void deleteTrack(int id);
    public Track updateTrack(Track track) throws TrackNotFoundException;
    public Track trackByName(String firstName);

    //list the top tracks of last.fm
    void getTopTracks();
}




