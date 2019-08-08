package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrackRepositoryTest
{

        @Autowired
        private TrackRepository trackRepository;
        private Track track;

        @Before
        public void setUp() {
            track = new Track();
            track.setId(57);
            track.setName("AR Rehman");
            track.setComment("Playback");
        }

        @After
        public void tearDown() {


        }


        @Test
        public void testSaveTrack() {
            trackRepository.save(track);
            Track result = trackRepository.findById(track.getId()).get();
            System.out.println(result);
            assertEquals(57, result.getId());

        }

        //test for insert operation
        @Test
        public void testSaveTrackFailure() {
            Track testUser = new Track();
            trackRepository.save(track);
            Track result = trackRepository.findById(track.getId()).get();
            Assert.assertNotSame(testUser, track);
        }

        @Test
        public void testGetAllTracks() {
            Track track = new Track(56, "Gantashala", "BacktoBack");
            Track track1 = new Track(58, "DSP", "Most Favourite");
            trackRepository.save(track);
            trackRepository.save(track1);

            List<Track> list = (List<Track>) trackRepository.findAll();
            assertEquals("Gantashala", list.get(0).getName());
        }

        //test for delete operation
        @Test
        public void testDeleteTrack() {
            Track track = new Track(59, "Taman", "Music Masth");
            trackRepository.delete(track);
            boolean deletedTrack = trackRepository.existsById(59);
            assertEquals(false, deletedTrack);
        }

        @Test
        public void testDeleteTrackFailure() {
            Track testUser = new Track();
            trackRepository.delete(track);
            boolean trackDelete = trackRepository.existsById(59);
            Assert.assertNotSame(true, trackDelete);
        }

        //test for update operation
        @Test
        public void testUpdateTrack() {
            Track track = new Track(56, "Gantashala", "BacktoBack");
            Track track1 = new Track(58, "DSP", "Most Favourite");
            trackRepository.save(track);
            trackRepository.save(track1);
            trackRepository.findById(track.getId()).get().setComment("changed");
            System.out.println(track);
            List<Track> list = trackRepository.findAll();
            assertEquals("BacktoBack", list.get(0).getComment());
        }

        @Test
        public void testUpdateTrackFailure() {
            Track track = new Track(56, "Gantashala", "BacktoBack");
            Track track1 = new Track(58, "DSP", "Most Favourite");
            trackRepository.save(track);
            trackRepository.save(track1);
            trackRepository.findById(track.getId()).get().setComment("not changed");
            List<Track> list = trackRepository.findAll();
            Assert.assertNotSame("BacktoBack", list.get(0).getComment());
        }
    }

