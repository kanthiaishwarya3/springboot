package com.stackroute.controller;
import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value="api/v1")   // used to map all the request endpoints
public class TrackController
{

@Autowired
private TrackRepository trackRepository;

    // Declaration
   private TrackService trackService;
   private ResponseEntity responseEntity;


public TrackController(TrackService trackService)
{
    this.trackService = trackService;
}


// Implementing POST method
@PostMapping("track")
public ResponseEntity<?> saveTrack(@RequestBody Track track)
{
    try{
        trackService.saveTrack(track);
        responseEntity=new ResponseEntity("Successfully created", HttpStatus.CREATED);
    }
    catch (Exception e)
    {
        responseEntity=new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }
    return responseEntity;

}

// Implementing GET method
@GetMapping("track")
    public ResponseEntity<?> getAllTracks()
{
    return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
}

// Implementing PUT method
@PutMapping("track")
public ResponseEntity<?> updateTrack(@RequestBody Track track)
{
    try{
        trackService.updateTrack(track);
        responseEntity=new ResponseEntity("Successfully updated", HttpStatus.CREATED);
    }
    catch (Exception e)
    {
        responseEntity=new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }
    return responseEntity;
}

@GetMapping("trackapi")
public ResponseEntity<?> sendGet() throws Exception
{
    List<Track> tracks;
    try{
        trackService.getUrlData();
        responseEntity = new ResponseEntity<String>("success", HttpStatus.OK);
    } catch (Exception e){
        System.out.println("Exception started");
        e.printStackTrace();
        responseEntity = new ResponseEntity<String>("Exception", HttpStatus.CONFLICT);
    }
    return responseEntity;
}

// Implementing DELETE method
@DeleteMapping(value="/track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id)
{
        try {
            trackService.deleteTrack(id);
            responseEntity = new ResponseEntity("Successfully deleted", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

// Retrieving Data by name field
    @GetMapping(value="/track/{name}")
    public ResponseEntity<?> trackByName(@PathVariable("name") String name)
    {
        return new ResponseEntity<List<Track>>(trackService.trackByName(name),HttpStatus.OK);
    }

}
