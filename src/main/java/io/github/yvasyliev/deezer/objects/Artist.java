package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.yvasyliev.deezer.v2.methods.PagingMethod;
import lombok.Data;

import java.net.URL;

/**
 * An artist object.
 */
@Data
public class Artist implements Pageable {
    /**
     * The artist's Deezer id.
     */
    @Expose
    @SerializedName("id")
    private Long id;

    /**
     * The artist's name.
     */
    @SerializedName("name")
    private String name;

    /**
     * The url of the artist on Deezer.
     */
    @Expose
    @SerializedName("link")
    private URL link;

    /**
     * The share link of the artist on Deezer.
     */
    @Expose
    @SerializedName("share")
    private URL share;

    /**
     * The url of the artist picture. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'.
     */
    @Expose
    @SerializedName("picture")
    private Picture picture;

    /**
     * The url of the artist picture in size small.
     */
    @Expose
    @SerializedName("picture_small")
    private URL pictureSmall;

    /**
     * The url of the artist picture in size medium.
     */
    @Expose
    @SerializedName("picture_medium")
    private URL pictureMedium;

    /**
     * The url of the artist picture in size big.
     */
    @Expose
    @SerializedName("picture_big")
    private URL pictureBig;

    /**
     * The url of the artist picture in size xl.
     */
    @Expose
    @SerializedName("picture_xl")
    private URL pictureXl;

    /**
     * The number of artist's albums.
     */
    @Expose
    @SerializedName("nb_album")
    private Integer numberOfAlbums;

    /**
     * The number of artist's fans.
     */
    @Expose
    @SerializedName("nb_fan")
    private Integer numberOfFans;

    /**
     * true if the artist has a smartradio.
     */
    @Expose
    @SerializedName("radio")
    private Boolean radio;

    /**
     * API Link to the top of this artist.
     */
    @Expose
    @SerializedName("tracklist")
    private PagingMethod<Track> trackList;
}
