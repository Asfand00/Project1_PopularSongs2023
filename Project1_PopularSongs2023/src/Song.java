/**
 * This Record takes in song data from "spotify-2023.csv" file and creates a
 * Song Record with the following attributes below
 *
 * @param trackName                     the track name
 * @param artistsName                   the artists name
 * @param releasedYear                  the released year
 * @param releasedMonth                 the released month
 * @param releasedDay                   the released day
 * @param totalNumberOfStreamsOnSpotify the total streams on spotify
 * @author Asfandyar Tanwer
 * @version 1.0
 */
public record Song(
        String trackName, /* The name of the song. */

        String artistsName,  /* artist(s) name of song. */

        String releasedYear, /* release year of song. */

        String releasedMonth, /*  release month of song */

        String releasedDay, /* release day of song */

        String totalNumberOfStreamsOnSpotify /* total streams on spotify of song */

) implements Comparable<Song> {

    /**
     * This is the constructor for the Song Record its main feature is to test
     * for null data and empty parameters
     *
     * @param trackName                     the track name
     * @param artistsName                   the artist name
     * @param releasedYear                  year of release
     * @param releasedMonth                 month the release
     * @param releasedDay                   day of release
     * @param totalNumberOfStreamsOnSpotify total streams on spotify
     */
    public Song {
        try {
            if (trackName == null || artistsName == null || releasedYear == null || releasedMonth == null ||
                    releasedDay == null || totalNumberOfStreamsOnSpotify == null) {
                throw new NullPointerException("Contents of Song cannot be null");
            }

            if (trackName.isEmpty() || artistsName.isEmpty() || releasedYear.isEmpty() || releasedMonth.isEmpty()
                    || releasedDay.isEmpty() || totalNumberOfStreamsOnSpotify.isEmpty()) {

                throw new IllegalArgumentException("Contents of Song cannot be empty");
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("Error Message: " + e.getMessage());
        }
    }

    /**
     * This is an equals method and a way to sort the song array by track-name by comparing two song records
     *
     * @param song the object to be compared.
     * @return a positive(after), negative(before), or 0(equal) number based on comparing two track names
     */
    @Override
    public int compareTo(Song song) {
        if (song == null) {
            return 1; // Consider this record greater than null
        }

        // Compare the names while handling potential null values
        if (this.trackName == null && song.trackName() == null) {
            return 0; // Both names are null
        } else if (this.trackName == null) {
            return -1; // This name is considered smaller
        } else if (song.trackName() == null) {
            return 1; // Other name is considered smaller
        } else {
            return this.trackName.compareTo(song.trackName());
        }
    }

    /**
     * This method returns a Song information for this Song
     *
     * @return Song information of this Song
     */
    public String toString() {
        return "Track Name: " + this.trackName + "\n" +
                "Artist(s): " + this.artistsName + "\n" +
                "Released Year: " + this.releasedYear + "\n" +
                "Released Month: " + this.releasedMonth + "\n" +
                "Released Day: " + this.releasedDay + "\n" +
                "Total Streams on Spotify: " + this.totalNumberOfStreamsOnSpotify;
    }
}