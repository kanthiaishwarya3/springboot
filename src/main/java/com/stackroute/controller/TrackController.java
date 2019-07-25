package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.service.TrackService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "api/v1")
public class TrackController
{
    TrackService trackService;
    @Autowired
    public TrackController(TrackService userService)
    {
        this.trackService=userService;

    }
    @PostMapping("track")
    public ResponseEntity<?> saveUser(@RequestBody Track user)
    {
      ResponseEntity responseEntity;
      try
      {
          trackService.saveUser(user);
          responseEntity=new ResponseEntity<String>("successfully completed", HttpStatus.CREATED);

      }catch (Exception ex)
      {
          responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
      }

      return responseEntity;
    }

    @GetMapping("track")
    public ResponseEntity<?>getAllUsers()
    {
return new ResponseEntity<List<Track>>(trackService.getAllUsers(), HttpStatus.OK);
    }


    // Implementing PUT method
    @PutMapping("track")
    public ResponseEntity<?> updateTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try{
            trackService.updateTrack(track);
            responseEntity=new ResponseEntity("Successfully updated", HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    // Implementing DELETE method
    @DeleteMapping(value="/track/{id}")
    public ResponseEntity<?> deleteTrack(@ApiParam(value = "Data is retrieved based on track-id", required = true)@PathVariable("id") int id) {
        ResponseEntity responseEntity;
        try {
            trackService.deleteTrack(id);
            responseEntity = new ResponseEntity("Successfully deleted", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    // Retrieving Data by name field

    @GetMapping("track/{firstName}")
    public ResponseEntity<?> trackByName(@ApiParam(value = "Data is retrieved based on firstName", required = true)@PathVariable("firstName") String firstName)
    {
        return new ResponseEntity<>(trackService.trackByName(firstName),HttpStatus.OK);
    }


}