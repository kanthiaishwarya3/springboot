package com.stackroute.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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
    public Track deleteTrack(int id) {
        Optional<Track> track =null;
        if(trackRepository.existsById(id) == true) {
            trackRepository.deleteById(id);
            track= trackRepository.findById(id);
        }
        return track.get();
    }

    @Override
    public Track trackByName(String firstName)  {
        return trackRepository.trackByName(firstName);
    }

    @Override
    public void getTopTracks() {
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks" +
                "&api_key=54a2ba5bdb864d684eb1c9853cf22488&format=json";
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper=new ObjectMapper();
        Track track=new Track();
        JsonNode jsonNode=null;
        try
        {
            jsonNode =objectMapper.readTree(result.getBody());
            ArrayNode arrayNode=(ArrayNode)(jsonNode.path("tracks").path("track"));
            for(int i=0;i<=arrayNode.size()-1;i++)
            {
                track.setFirstName(arrayNode.get(i).path("name").asText());
                track.setLastName(arrayNode.get(i).path("artist").path("name").asText());
                trackRepository.save(track);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}



