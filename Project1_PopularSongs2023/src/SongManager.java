import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class reads "count-by-release-year.csv" and "spotify-2023.csv" and creates
 * respective arrays for Songs (and sorts them) and Release years. It also manages
 * the data created with the methods below implemented from SongManagerInterface
 *
 * @author Asfandyar Tanwer
 * @version 1.0
 */
public class SongManager implements SongManagerInterface {

    private Song[][] songData; // the main Song array from data in "spotify-2023.csv"

    private String[] releaseYears; // the Release year array created from "count-by-release-year.csv"

    private String[] countSong; // the count from each release year found in "count-by-release-year.csv"

    /**
     * this is the main constructor that reads in files, creates arrays, fills arrays with data from files, and
     * sorts the songData array by track name
     */
    public SongManager() {
        // Specify the path to your CSV files
        String csvFile1 = "src/count-by-release-year.csv";
        String csvFile2 = "src/spotify-2023.csv";

        try {
            // Create a FileReader object to read the CSV files
            FileReader fileReader1 = new FileReader(csvFile1);
            FileReader fileReader2 = new FileReader(csvFile2);

            // Create a CSVReader object for each file
            CSVReader csvReader1 = new CSVReader(fileReader1);
            CSVReader csvReader2 = new CSVReader(fileReader2);

            // Read all lines and store all release years from the CSV file into an array
            String[] nextLine;
            int idx_year = 0;
            int idx_count = 0;

            csvReader1.readNext(); // Skip the first line of "count-by-release-year.csv"

            // initialize arrays
            this.releaseYears = new String[50];
            this.countSong = new String[50];

            // fill releaseYears and countSong arrays with data  
            while ((nextLine = csvReader1.readNext()) != null) {
                // Process the data as needed
                if (nextLine.length > 0 && nextLine[0].matches("\\d{4}")) {
                    this.releaseYears[idx_year] = nextLine[0];
                    idx_year++;
                }
                if (nextLine.length > 0 && nextLine[1].matches("-?\\d+")) {
                    this.countSong[idx_count] = nextLine[1];
                    idx_count++;

                }
            }

            csvReader2.readNext(); // ignore first line of spotify-2023.csv file

            // initialize songData array with empty columns 
            this.songData = new Song[50][];

            // fill each column with set lengths taken from countSong array  
            for (int songIdx = 0; songIdx < this.songData.length; songIdx++) {
                this.songData[songIdx] = new Song[Integer.parseInt(this.countSong[songIdx])];
            }

            // fill songData wil song data captured from "spotify-2023.csv"
            while ((nextLine = csvReader2.readNext()) != null) {

                // get certain data from nextLine from call to private method getSong
                Song cur_song = getSong(nextLine);

                // add current record to songData replacing the first null
                for (int i = 0; i < this.songData.length; i++) {
                    for (int j = 0; j < this.songData[i].length; j++) {
                        if (Objects.equals(cur_song.releasedYear(), this.releaseYears[i]) && this.songData[i][j] == null) {
                            this.songData[i][j] = cur_song;
                            j = this.songData[i].length;
                        }
                    }
                }
            }

            // sort the songData array
            for (Song[] row : this.songData) {
                if (row.length > 1) {
                    Arrays.sort(row);
                }
            }

            // Close the CSVReader for both files
            csvReader1.close();
            csvReader2.close();

        } catch (CsvValidationException | IOException e) {
            System.out.println("Error In CSV file handling!");
        }
    }

    /**
     * this private method is a helper method for getting data from nextLine,
     * creating new record, and returning the record
     *
     * @param nextLine the line from file in array format
     * @return new Song record
     */
    private Song getSong(String[] nextLine) {
        String trackName = nextLine[0];
        String artistsName = nextLine[1];
        String releasedYear = nextLine[3];
        String releasedMonth = nextLine[4];
        String releasedDay = nextLine[5];
        String totalStreams = nextLine[8];

        return new Song(trackName, artistsName, releasedYear, releasedMonth, releasedDay, totalStreams);
    }

    /**
     * Retrieves the count of release years
     *
     * @return count of release years
     */
    public int getYearCount() {
        return 50;
    }

    /**
     * Retrieves the number of songs in the specified release year (by index)
     *
     * @param yearIndex the index of the release year
     * @return song count in that release year
     */
    public int getSongCount(int yearIndex) {
        return Integer.parseInt(countSong[yearIndex]);
    }

    /**
     * Retrieves the number of songs in all release years
     *
     * @return song count in all release years
     */
    public int getSongCount() {
        int totalCount = 0;
        for (String s : countSong) {
            totalCount = totalCount + Integer.parseInt(s);
        }
        return totalCount;
    }

    /**
     * Retrieves the release year at the specified index
     *
     * @param yearIndex index of the desired release year
     * @return release year
     */
    public String getYearName(int yearIndex) {
        return releaseYears[yearIndex];
    }

    /**
     * Retrieves the index of the specified year name
     *
     * @param year the year name in String
     * @return the      index of the year
     */
    public int getYearIndex(String year) {
        int yearIdx = 0;
        for (int i = 0; i < releaseYears.length; i++) {
            if (Objects.equals(year, releaseYears[i])) {
                yearIdx = i;
                return yearIdx;
            }
        }
        return yearIdx;
    }

    /**
     * Retrieves the number of songs in the specified release year (by name)
     *
     * @param year the release year
     * @return song count in that release year
     */
    public int getSongCount(String year) {
        int yearIdx = 0;
        for (int i = 0; i < releaseYears.length; i++) {
            if (Objects.equals(releaseYears[i], year)) {
                yearIdx = i;
            }
        }
        return Integer.parseInt(countSong[yearIdx]);
    }

    /**
     * Retrieves the song at the specific release year and song index
     *
     * @param yearIndex release year index
     * @param songIndex song index
     * @return song at that array position
     */
    public Song getSong(int yearIndex, int songIndex) {
        return songData[yearIndex][songIndex];
    }

    /**
     * Retrieves a copy of the song array for the release year at the specified index
     *
     * @param yearIndex release year index
     * @return copy of song array (not a reference to the internal one)
     */
    public Song[] getSongs(int yearIndex) {
        Song[] songs;
        songs = songData[yearIndex];
        return songs;
    }

    /**
     * Retrieves the first release year index associated with the specified song's track name
     *
     * @param trackName the track name to search for
     * @return the first release year index containing the specified song, or -1 if not found
     */
    public int findSongYear(String trackName) {
        for (int i = 0; i < songData.length; i++) {
            for (int j = 0; j < songData[i].length; j++) {
                Song cur_song = songData[i][j];
                if (Objects.equals(cur_song.trackName(), trackName)) {
                    return i;
                }
            }
        }
        return -1;
    }
}