package com.stackroute.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Service
public class TrackServiceImpl implements TrackService
{
    HttpURLConnection httpURLConnection;

   static String GET_URL="http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=spain&api_key=570552d5d79081a6120c14a93d74d147&format=json";

    TrackRepository trackRepository;

    // Providing implementation for all methods of track
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository)
    {
        this.trackRepository = trackRepository;
    }

    // Implemented API and the data from URL
    @Override
    public void getUrlData() throws Exception
    {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

       //get a json object as a String
        String json = restTemplate.getForObject(GET_URL, String.class);

        try {
       //converting string as a json node
            JsonNode rootNode = mapper.readTree(json);
            ArrayNode arrayNode =  (ArrayNode)rootNode.path("tracks").path("track");
            //iterate the JSON array
            for (int i = 0; i < arrayNode.size(); i++) {
                //get a new Track object and fill it with data using setters
                Track track1 = new Track();
                track1.setId(arrayNode.get(i).path("id").asInt());
                track1.setName(arrayNode.get(i).path("name").asText());
                track1.setComment(arrayNode.get(i).path("artist").path("name").asText());
                //save the track to database
                trackRepository.save(track1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Track saveTrack(Track track) throws Exception
    {
        if(trackRepository.existsById(track.getId()))
        {
            throw new Exception("Track Already exist");
        }
        Track savetrack=trackRepository.save(track);

        if(savetrack==null)
        {
            throw new Exception("Track already present");
        }
        return savetrack;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track updateTrack(Track track)  throws Exception
    {
        if (trackRepository.existsById(track.getId())) {
            Track trackobj = trackRepository.findById(track.getId()).get();
            trackobj.setComment(track.getComment());
            trackRepository.save(trackobj);
            return trackobj;
        } else {
            throw new Exception("Track not found");
        }
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
    public List<Track> trackByName(String name) {
        return trackRepository.trackByName(name);
    }


}
