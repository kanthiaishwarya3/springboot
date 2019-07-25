package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrackServiceImpl implements TrackService {
    TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository userRepository) {
        this.trackRepository = userRepository;
    }

    @Override
    public Track saveUser(Track user)throws TrackAlreadyExistsException
    {
        if(trackRepository.existsById(user.getId()))
    {
        throw new TrackAlreadyExistsException("Track Already exist");
    }
        Track saveUser=trackRepository.save(user);

        if(saveUser==null)
        {
            throw new TrackAlreadyExistsException("Track already present");
        }
        return saveUser;


    }


    @Override
    public List<Track> getAllUsers() {
        return trackRepository.findAll();
    }


    @Override
    public Track updateTrack(Track user)throws TrackNotFoundException
    {
        if(trackRepository.existsById(user.getId()))
        {
            throw new TrackNotFoundException("Track Already exist");
        }
        Track saveUser=trackRepository.save(user);

        if(saveUser==null)
        {
            throw new TrackNotFoundException("Track already present");
        }
        return saveUser;


    }



    @Override
    public void deleteTrack(int id) {
        trackRepository.deleteById(id);
    }
    @Override
    public Track trackByName(String firstName)  {
        return trackRepository.trackByName(firstName);
    }
}



