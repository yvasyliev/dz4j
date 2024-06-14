package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import lombok.Data;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;

/**
 * An album object.
 */
@Data
public class Album implements Pageable {
    /**
     * The Deezer album id.
     */
    @Expose
    @SerializedName("id")
    private Long id;

    /**
     * The album title.
     */
    @Expose
    @SerializedName("title")
    private String title;

    /**
     * The album UPC.
     */
    @Expose
    @SerializedName("upc")
    private String upc;

    /**
     * The url of the album on Deezer.
     */
    @Expose
    @SerializedName("link")
    private URL link;

    /**
     * The share link of the album on Deezer.
     */
    @Expose
    @SerializedName("share")
    private URL share;

    /**
     * The url of the album's cover. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'.
     */
    @Expose
    @SerializedName("cover")
    private Picture cover;

    /**
     * The url of the album's cover in size small.
     */
    @Expose
    @SerializedName("cover_small")
    private URL coverSmall;

    /**
     * The url of the album's cover in size medium.
     */
    @Expose
    @SerializedName("cover_medium")
    private URL coverMedium;

    /**
     * The url of the album's cover in size big.
     */
    @Expose
    @SerializedName("cover_big")
    private URL coverBig;

    /**
     * The url of the album's cover in size xl.
     */
    @Expose
    @SerializedName("cover_xl")
    private URL coverXl;

    /**
     * Image hash.
     */
    @Expose
    @SerializedName("md5_image")
    private String md5Image;

    /**
     * The album's first genre id (You should use the genre list instead). NB : -1 for not found.
     */
    @Expose
    @SerializedName("genre_id")
    private Long genreId;

    /**
     * List of genre object.
     */
    @Expose
    @SerializedName("genres")
    private io.github.yvasyliev.deezer.v2.objects.Page<Genre, PagingMethod<Genre>> genres;

    /**
     * The album's label name.
     */
    @Expose
    @SerializedName("label")
    private String label;

    /**
     * The number of album's tracks.
     */
    @Expose
    @SerializedName("nb_tracks")
    private Integer numberOfTracks;

    /**
     * The album's duration (seconds).
     */
    @Expose
    @SerializedName("duration")
    private Duration duration;

    /**
     * The number of album's Fans.
     */
    @Expose
    @SerializedName("fans")
    private Integer fans;

    /**
     * The album's rate.
     */
    @Expose
    @SerializedName("rating")
    private Integer rating;

    /**
     * The album's release date.
     */
    @Expose
    @SerializedName("release_date")
    private LocalDate releaseDate;

    /**
     * The record type of the album (EP / ALBUM / etc..).
     */
    @Expose
    @SerializedName("record_type")
    private RecordType recordType;

    /**
     * {@code true}, if the album is available in your country.
     */
    @Expose
    @SerializedName("available")
    private Boolean isAvailable;

    /**
     * Return an alternative album object if the current album is not available.
     */
    @Expose
    @SerializedName("alternative")
    private Album alternative;

    /**
     * API Link to the tracklist of this album.
     */
    @Expose
    @SerializedName("tracklist")
    private PagingMethod<Track> trackList;

    /**
     * Whether the album contains explicit lyrics.
     */
    @Expose
    @SerializedName("explicit_lyrics")
    private Boolean containsExplicitLyrics;

    /**
     * The explicit content lyrics values (0:Not Explicit; 1:Explicit; 2:Unknown; 3:Edited; 4:Partially Explicit (Album "lyrics" only); 5:Partially Unknown (Album "lyrics" only); 6:No Advice Available; 7:Partially No Advice Available (Album "lyrics" only)).
     */
    @Expose
    @SerializedName("explicit_content_lyrics")
    private ExplicitContent explicitContentLyrics;

    /**
     * The explicit cover values (0:Not Explicit; 1:Explicit; 2:Unknown; 3:Edited; 4:Partially Explicit (Album "lyrics" only); 5:Partially Unknown (Album "lyrics" only); 6:No Advice Available; 7:Partially No Advice Available (Album "lyrics" only)).
     */
    @Expose
    @SerializedName("explicit_content_cover")
    private ExplicitContent explicitContentCover;

    /**
     * Return a list of contributors on the album.
     */
    @Expose
    @SerializedName("contributors")
    private Set<Contributor> contributors;

    /**
     * {@link Artist} object containing : id, name, picture, picture_small, picture_medium, picture_big, picture_xl.
     */
    @Expose
    @SerializedName("artist")
    private Artist artist;

    /**
     * list of {@link Track}.
     */
    @Expose
    @SerializedName("tracks")
    private io.github.yvasyliev.deezer.v2.objects.Page<Track, PagingMethod<Track>> tracks;
}
