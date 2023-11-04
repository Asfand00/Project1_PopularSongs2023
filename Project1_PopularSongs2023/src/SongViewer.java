import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * This class creates the GUI using JFrame for using data in SongManager.
 * User loads data first then choose a year and can navigate through
 * all songs in that specific year, displays additional data about song count
 * and total songs.
 * Run the app in the "Main" class
 *
 * @author Asfandyar Tanwer
 * @version 1.0
 */
public class SongViewer extends JFrame {

    /**
     * current song index
     */
    private int curSongIdx = 0;

    /**
     * current song in year (used in songInfo JLabel)
     */
    private int songCounter = 1;

    /**
     * This is the main constructor for SongViewer GUI, creates new JFrame
     */
    public SongViewer() {

        // set size for frame
        setSize(400, 350);

        // add new song manager
        SongManager songManager = new SongManager();

        // add button load data
        JButton load_data = new JButton("Load Data");
        load_data.setBounds(30, 30, 100, 30);
        add(load_data);

        // add button prev
        JButton prev = new JButton("Prev");
        prev.setBounds(140, 30, 100, 30);
        prev.setEnabled(false);
        add(prev);

        // add button next
        JButton next = new JButton("Next");
        next.setBounds(250, 30, 100, 30);
        next.setEnabled(false);
        add(next);

        // add year combo box, populate list with years first then add to frame
        JComboBox<String> years = new JComboBox<>();
        years.setBounds(30, 70, 100, 30);
        for (int i = 0; i < songManager.getYearCount(); i++) {
            years.addItem(songManager.getYearName(i));
        }
        add(years);
        years.setSelectedItem(null);
        years.setEnabled(false);

        // add song info label, empty for now
        JLabel songInfo = new JLabel("");
        songInfo.setBounds(140, 70, 300, 30);
        add(songInfo);

        // add track name label
        JLabel trackName = new JLabel("Track Name:");
        trackName.setBounds(30, 110, 100, 30);
        add(trackName);

        // add artist(s) label
        JLabel artistsName = new JLabel("Artist(s):");
        artistsName.setBounds(30, 150, 100, 30);
        add(artistsName);

        // add release date label
        JLabel releaseDate = new JLabel("Release Date:");
        releaseDate.setBounds(30, 190, 100, 30);
        add(releaseDate);

        // add streams label
        JLabel totalSteams = new JLabel("Total Streams:");
        totalSteams.setBounds(30, 230, 100, 30);
        add(totalSteams);

        // add track name text field
        JTextField track = new JTextField();
        track.setBounds(140, 110, 210, 30);
        add(track);

        // add artist(s) text field
        JTextField artists = new JTextField();
        artists.setBounds(140, 150, 210, 30);
        add(artists);

        // add song release text field
        JTextField release = new JTextField();
        release.setBounds(140, 190, 210, 30);
        add(release);

        // add streams text field
        JTextField streams = new JTextField();
        streams.setBounds(140, 230, 210, 30);
        add(streams);

        load_data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // enable years and disable load data
                years.setEnabled(true);
                load_data.setEnabled(false);
            }
        });

        years.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // enable prev and next buttons
                prev.setEnabled(true);
                next.setEnabled(true);

                // get year
                String selectedOption = (String) years.getSelectedItem();

                /*
                based on year get song data and populate all songs in array for that year
                 */
                int yearIndex = songManager.getYearIndex(selectedOption);
                Song[] songArr = songManager.getSongs(yearIndex);

                // get total song count of all songs
                int songCountYear = songManager.getSongCount(selectedOption);

                // display first song in text fields
                track.setText(songArr[0].trackName());
                artists.setText(songArr[0].artistsName());
                release.setText(songArr[0].releasedMonth() + "/" + songArr[0].releasedDay() + "/" + songArr[0].releasedYear());
                streams.setText(songArr[0].totalNumberOfStreamsOnSpotify());

                // if song counter is not 1 or song index is not 0, reset them
                if (songCounter != 1) {
                    songCounter = 1;
                }
                if (curSongIdx != 0) {
                    curSongIdx = 0;
                }

                // display current song info in label
                songInfo.setText("Song " + songCounter + " of " + songCountYear + " | " + songCountYear + " out of " + songManager.getSongCount() + " songs");
            }
        });

        prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                copy actions from years action listener
                 */
                String selectedOption = Objects.requireNonNull(years.getSelectedItem()).toString();
                int yearIndex = songManager.getYearIndex(selectedOption);
                Song[] songArr = songManager.getSongs(yearIndex);
                int songCountYear = songManager.getSongCount(selectedOption);

                // -1 from each, song index and song counter
                curSongIdx -= 1;
                songCounter -= 1;

                // if prev is hit on first song in list, stay there
                if (curSongIdx < 0) {
                    curSongIdx = 0;
                }
                if (songCounter < 1) {
                    songCounter = 1;
                }

                // display current song info
                songInfo.setText("Song " + songCounter + " of " + songCountYear + " | " + songCountYear + " out of " + songManager.getSongCount() + " songs");

                // display current song in text fields
                track.setText(songArr[curSongIdx].trackName());
                artists.setText(songArr[curSongIdx].artistsName());
                release.setText(songArr[curSongIdx].releasedMonth() + "/" + songArr[curSongIdx].releasedDay() + "/" + songArr[curSongIdx].releasedYear());
                streams.setText(songArr[curSongIdx].totalNumberOfStreamsOnSpotify());
            }
        });

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                copy actions from years action listener
                 */
                String selectedOption = Objects.requireNonNull(years.getSelectedItem()).toString();
                int yearIndex = songManager.getYearIndex(selectedOption);
                Song[] songArr = songManager.getSongs(yearIndex);
                int songCountYear = songManager.getSongCount(selectedOption);

                // + 1 to each, song index and song counter
                curSongIdx += 1;
                songCounter += 1;

                // if next is hit while on last song in list, stay there
                if (curSongIdx >= songArr.length) {
                    curSongIdx = songArr.length - 1;
                }
                if (songCounter >= songCountYear) {
                    songCounter = songCountYear;
                }

                // display current song info
                songInfo.setText("Song " + songCounter + " of " + songCountYear + " | " + songCountYear + " out of " + songManager.getSongCount() + " songs");

                // display current song in text fields
                track.setText(songArr[curSongIdx].trackName());
                artists.setText(songArr[curSongIdx].artistsName());
                release.setText(songArr[curSongIdx].releasedMonth() + "/" + songArr[curSongIdx].releasedDay() + "/" + songArr[curSongIdx].releasedYear());
                streams.setText(songArr[curSongIdx].totalNumberOfStreamsOnSpotify());
            }
        });

        // finish frame settings and exit on x
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
