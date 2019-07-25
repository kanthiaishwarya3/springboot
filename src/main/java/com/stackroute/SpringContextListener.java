package com.stackroute;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class SpringContextListener implements ApplicationListener<ContextRefreshedEvent>
{
    @Autowired
    TrackService trackservice;

    public SpringContextListener(TrackService trackservice) {
        this.trackservice = trackservice;

    }

    // Invoking the method at the start up of the spring application.

    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        try {
            trackservice.saveUser(new Track(52,"ooha","Reddy",23));
            trackservice.saveUser(new Track(56,"Bhuna", "Reddy", 24));
            trackservice.saveUser(new Track(60,"Hari","Reddy", 24));
        } catch (TrackAlreadyExistsException e)
        {
            e.printStackTrace();
        }

    }

}




