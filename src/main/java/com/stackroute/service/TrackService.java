package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistException;
import com.stackroute.exception.TrackNotFoundException;

import java.util.List;

public interface TrackService
{

    // Methods to implement CRUD.
    public Track saveTrack(Track track) throws TrackAlreadyExistException;
    public List<Track> getAllTracks();
    public boolean deleteTrack(int id);
    public Track updateTrack(Track track) throws TrackNotFoundException;

}
