import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This is the Junit 5 test for Song record.
 * It tests all methods, constructors, accessors found in Song.java
 *
 * @author Asfandyar Tanwer
 * @version 1.0
 */
class SongTest {

    /**
     * test the song constructor and make sure errors are caught and displayed to user
     * about null and empty parameters
     */
    @Test
    void Song() {
        /*
        create multiple different Songs
        */
        Song song = new Song("a", "b", "c", "d", "e", "f");
        Song song1 = new Song("a", "b", "c", "d", "e", "f");
        Song song2 = new Song("a", "b", "c", "d", "e", "");
        Song song3 = new Song("a", null, "c", "d", "e", "f");

        // song and song1 should be equal
        Assertions.assertEquals(song, song1);
        // song2 should have empty string parameter at totalNumberOfStreamsOnSpotify
        Assertions.assertEquals(song2.totalNumberOfStreamsOnSpotify(), "");
        // song3 artistsName should be null
        Assertions.assertNull(song3.artistsName());
    }

    /**
     * tests compareTo that it works properly
     */
    @Test
    void compareTo() {
        /*
        create multiple songs
         */
        Song song = new Song("a", "b", "c", "d", "e", "f");
        Song song2 = new Song("b", "b", "c", "d", "e", "f");
        Song song3 = new Song("a", "b", "c", "d", "e", "f");
        Song bad_song = new Song(null, "b", "c", "d", "e", "f");
        Song bad_song1 = new Song(null, "b", "c", "d", "e", "f");

        /*
        assert equals test on different combinations of song using compareTo, expecting a certain int for each listed
         */
        Assertions.assertEquals(song.compareTo(song2), -1);
        Assertions.assertEquals(song2.compareTo(song), 1);
        Assertions.assertEquals(song3.compareTo(song), 0);
        Assertions.assertEquals(song.compareTo(bad_song), 1);
        Assertions.assertEquals(bad_song.compareTo(song), -1);
        Assertions.assertEquals(bad_song.compareTo(bad_song1), 0);
    }

    /**
     * tests that toString() displays right information and is not empty
     */
    @Test
    void testToString() {
        /*
        test toString to see it works and doesn't return empty string
         */
        Song song = new Song("a", "b", "c", "d", "e", "f");
        System.out.println("toString() Results:\n" + song);
        Assertions.assertNotEquals("", song.toString());
    }

    /**
     * tests that .trackName() is called correctly with right info
     */
    @Test
    void trackName() {
        Song song = new Song("a", "b", "c", "d", "e", "f");
        Assertions.assertEquals(song.trackName(), "a");
    }

    /**
     * tests that .artistsName() is called correctly with right info
     */
    @Test
    void artistsName() {
        Song song = new Song("a", "b", "c", "d", "e", "f");
        Assertions.assertEquals(song.artistsName(), "b");
    }

    /**
     * tests that .releasedYear() is called correctly with right info
     */
    @Test
    void releasedYear() {
        Song song = new Song("a", "b", "c", "d", "e", "f");
        Assertions.assertEquals(song.releasedYear(), "c");
    }

    /**
     * tests that .releasedMonth() is called correctly with right info
     */
    @Test
    void releasedMonth() {
        Song song = new Song("a", "b", "c", "d", "e", "f");
        Assertions.assertEquals(song.releasedMonth(), "d");
    }

    /**
     * tests that .releasedDay() is called correctly with right info
     */
    @Test
    void releasedDay() {
        Song song = new Song("a", "b", "c", "d", "e", "f");
        Assertions.assertEquals(song.releasedDay(), "e");
    }

    /**
     * tests that .totalNumberOfStreamsOnSpotify() is called correctly with right info
     */
    @Test
    void totalNumberOfStreamsOnSpotify() {
        Song song = new Song("a", "b", "c", "d", "e", "f");
        Assertions.assertEquals(song.totalNumberOfStreamsOnSpotify(), "f");
    }
}